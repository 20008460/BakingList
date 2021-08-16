package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class modifyRecipe extends AppCompatActivity {

    Button btnMRecipe;
    EditText etMTitle , etMSummary , etMIngredients, etMDetails;

    Recipe data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_recipe);

        etMTitle = findViewById(R.id.etMTitle);
        etMSummary = findViewById(R.id.etMSummary);
        etMIngredients = findViewById(R.id.etMIngredients);
        etMDetails = findViewById(R.id.etMDetails);

        btnMRecipe = findViewById(R.id.btnMRecipe);

        Intent i = getIntent();
        data = (Recipe) i.getSerializableExtra("data");

        String username = i.getStringExtra("username");

        etMTitle.setText(data.getTitle());
        etMDetails.setText(data.getDetails());
        etMIngredients.setText(data.getIngredients());
        etMSummary.setText(data.getSummary());

        btnMRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBRecipes dbh = new DBRecipes(modifyRecipe.this);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(modifyRecipe.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to modify the recipe ?");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Modify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        data.setTitle(etMTitle.getText().toString());
                        data.setDetails(etMDetails.getText().toString());
                        data.setSummary(etMSummary.getText().toString());
                        data.setIngredients(etMIngredients.getText().toString());
                        data.setUsername(username);

                        dbh.updateRecipe(data);
                        dbh.close();

                      finish();
                    }
                });

                myBuilder.setPositiveButton("Cancel" , null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });

    }
}