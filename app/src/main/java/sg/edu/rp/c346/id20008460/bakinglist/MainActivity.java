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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etName , etPassword;
    TextView tvRegister ;
    Button btnLogin ;

    ArrayList<Users> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.buttonLogin);
        tvRegister = findViewById(R.id.tvRegister);

        etName = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);

        DBUsers dbh = new DBUsers(MainActivity.this);
        userList = new ArrayList<Users>();
        userList.addAll(dbh.getAllUser());


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(MainActivity.this, toRegister.class);
                startActivity(show);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uName = etName.getText().toString();
                String pw = etPassword.getText().toString();

                for (int i = 0 ; i < userList.size() ; i ++) {
                    if (userList.get(i).getRole().equals("admin")) {
                        if (userList.get(i).getUsername().equals(uName) && pw.equals(userList.get(i).getPassword())) {

                            Toast.makeText(MainActivity.this, "Logged in as admin", Toast.LENGTH_SHORT).show();
                            Intent show = new Intent(MainActivity.this, manageUsers.class);
                            startActivity(show);
                        }
                    } else if (userList.get(i).getRole().equals("member"))

                    if (uName.equals(userList.get(i).getUsername()) && pw.equals(userList.get(i).getPassword())) {
                        Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();


                        Intent show = new Intent(MainActivity.this, homePage.class);
                        show.putExtra("data", userList.get(i));
                        startActivity(show);


                    }

                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        userList.clear();
        DBUsers dbh = new DBUsers(MainActivity.this);

        userList.addAll(dbh.getAllUser());

    }
}