package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class manageUsers extends AppCompatActivity {

    ListView lvMembers;
    TextView tvAdmin;
    Spinner spnRoles;

    CustomAdapter aa ;
    ArrayList<Users> alMem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        lvMembers = findViewById(R.id.lv);
        tvAdmin = findViewById(R.id.tvAddAdmin);
        spnRoles = findViewById(R.id.spinner);

        alMem = new ArrayList<Users>();

        DBUsers dbh = new DBUsers(manageUsers.this);

        alMem.addAll(dbh.getAllUser());
        aa = new CustomAdapter(this, R.layout.row, alMem);

        lvMembers.setAdapter(aa);

        lvMembers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Users data = alMem.get(position);
                Intent i = new Intent(manageUsers.this,
                        ModifyUsers.class);

                i.putExtra("data", data);
                startActivity(i);
            }
        });

        tvAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(manageUsers.this,
                        addAdmin.class);
                startActivity(i);

            }
        });

        spnRoles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                alMem.clear();
                DBUsers dbh = new DBUsers(manageUsers.this);

                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefeditor = prefs.edit();

                prefeditor.putInt("spnIndex" , position);

                switch (position) {
                    case 0 :

                        alMem.addAll(dbh.filterRole("member"));
                        aa.notifyDataSetChanged();
                        prefeditor.putString("role" , "member");
                        break;
                    case 1 :
                        alMem.addAll(dbh.filterRole("admin"));
                        aa.notifyDataSetChanged();
                        prefeditor.putString("role" , "admin");
                        break;
                }

                prefeditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        int spnResume = prefs.getInt("spnIndex" , 0);
        String role = prefs.getString("role" , "");

        spnRoles.setSelection(spnResume);

        DBUsers dbh = new DBUsers(manageUsers.this);
        alMem.clear();

        alMem.addAll(dbh.filterRole(role));

        aa.notifyDataSetChanged();
    }
}