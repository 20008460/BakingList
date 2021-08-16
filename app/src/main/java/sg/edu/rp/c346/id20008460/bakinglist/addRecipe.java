package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class addRecipe extends AppCompatActivity {

    EditText etRTitle , etRSummary , etRIngredient , etRDetail;
    Button btnAdd;
    TextView tv;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        tv = findViewById(R.id.hi);
        etRTitle = findViewById(R.id.addRTitle);
        etRSummary = findViewById(R.id.addRSummary);
        etRIngredient = findViewById(R.id.addRIngredients);
        etRDetail = findViewById(R.id.addRDetails);

        btnAdd = findViewById(R.id.buttonAddRecipe);

        Intent intentReceived = getIntent();
        username = intentReceived.getStringExtra("username");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBRecipes dbh = new DBRecipes(addRecipe.this);
                String newRTitle = etRTitle.getText().toString();
                String newRSummary = etRSummary.getText().toString();
                String newRIngredient = etRIngredient.getText().toString();
                String newRDetail = etRDetail.getText().toString();

                Calendar rightNow = Calendar.getInstance();
                int currentMonth = rightNow.get(Calendar.MONTH);
                int currentYear = rightNow.get(Calendar.YEAR) ;
                int currentDay = rightNow.get(Calendar.DATE);

                currentMonth = currentMonth+1;
                String monthAlpha = "";

                String month = currentMonth + "";

                if (month.equals("1")) {
                    monthAlpha = "Jan";
                } else if (month.equals("2")) {
                    monthAlpha = "Feb";
                } else if (month.equals("3")) {
                    monthAlpha = "Mar";
                } else if (month.equals("4")) {
                    monthAlpha = "Apr";
                } else if (month.equals("5")) {
                    monthAlpha = "May";
                } else if (month.equals("6")) {
                    monthAlpha = "June";
                } else if (month.equals("7")) {
                    monthAlpha = "Jul";
                } else if (month.equals("8")) {
                    monthAlpha = "Aug";
                } else if (month.equals("9")) {
                    monthAlpha = "Sep";
                } else if (month.equals("10")) {
                    monthAlpha = "Oct";
                } else if (month.equals("11")) {
                    monthAlpha = "Nov";
                } else if (month.equals("12")) {
                    monthAlpha = "Dec";
                }

                String year = currentDay + " " + monthAlpha + " " + currentYear;

                long inserted_id = dbh.insertRecipe(newRTitle,newRSummary, newRIngredient ,newRDetail , year , username);
                Toast.makeText(addRecipe.this, "Recipe added", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }


}