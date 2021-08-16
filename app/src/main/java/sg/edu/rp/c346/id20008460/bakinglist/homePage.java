package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {

    TextView tvName;
    Users data ,receiveUNS;

    ListView lvRecipe;
    CustomAdapterRecipe aRecipe;
    ArrayList<Recipe> alRecipe;
    Button btnAddRecipe;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // linking
        tvName = findViewById(R.id.tvHName);
        btnAddRecipe = findViewById(R.id.buttonAddR);
        lvRecipe = findViewById(R.id.listRecipe);

        //obtaining data of the logged in user
        Intent i = getIntent();
        data = (Users) i.getSerializableExtra("data");

        username = "";
        username = data.getUsername();
        tvName.setText(username.toUpperCase());

        // Setting of adapter
        alRecipe = new ArrayList<Recipe>();

        DBRecipes dbh = new DBRecipes(homePage.this);
        alRecipe.addAll(dbh.filterPersonal(data.getUsername()));
        aRecipe = new CustomAdapterRecipe(this, R.layout.row2, alRecipe);

        lvRecipe.setAdapter(aRecipe);

        btnAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();

                data = (Users) i.getSerializableExtra("data");

                String UN = data.getUsername();


                Intent show = new Intent(homePage.this, addRecipe.class);
                show.putExtra("username" , UN);
                startActivity(show);
            }
        });

        lvRecipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe data = alRecipe.get(position);

                Intent i = new Intent(homePage.this,
                        viewIndiviRecipe.class);

                Intent receiveUN = getIntent();
                receiveUNS = (Users) receiveUN.getSerializableExtra("data");

                String un = receiveUNS.getUsername();


                i.putExtra("data", data);
                i.putExtra("username" , un);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        DBRecipes dbh = new DBRecipes(homePage.this);
        alRecipe.clear();
        alRecipe.addAll(dbh.filterPersonal(data.getUsername()));
        aRecipe.notifyDataSetChanged();
    }
}