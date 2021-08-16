package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class viewIndiviRecipe extends AppCompatActivity {

    TextView etTitle , etSummary , etIngre , etDetail;
    Recipe data;
    Button btnUpdate , btnBack , btnDelete;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_indivi_recipe);

        Intent i = getIntent();
        data = (Recipe) i.getSerializableExtra("data");

        etTitle = findViewById(R.id.inTitle);
        etSummary = findViewById(R.id.inSum);
        etIngre = findViewById(R.id.inIngre);
        etDetail = findViewById(R.id.inSteps);

        btnUpdate = findViewById(R.id.buttonINUpdate);
        btnBack = findViewById(R.id.buttonINCancel);
        btnDelete = findViewById(R.id.buttonINDelete);

        String title = data.getTitle();
        String summary = data.getSummary();
        String ingre = data.getIngredients();
        String detail = data.getDetails();

        etTitle.setText(title);
        etSummary.setText(summary);
        etIngre.setText(ingre);
        etDetail.setText(detail);

        Intent intentReceived = getIntent();
        username = intentReceived.getStringExtra("username");

        etTitle.setText(username);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(viewIndiviRecipe.this, modifyRecipe.class);
                show.putExtra("data",data);
                show.putExtra("username", username);
                startActivity(show);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBRecipes dbh = new DBRecipes(viewIndiviRecipe.this);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(viewIndiviRecipe.this);

                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this recipe ");
                myBuilder.setCancelable(false);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbh.deleteReceipe(data.getId());
                        finish();
                    }
                });

                myBuilder.setPositiveButton("Cancel",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }

   
}