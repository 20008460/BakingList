package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class toRegister extends AppCompatActivity {

    EditText etUsername , etPassword , etPassword2;
    Button btnRegister;
    ArrayList<Users> newUser , existingUser;
    ArrayList<String> existingUName;
    boolean exist = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_register);

        etUsername = findViewById(R.id.etNewUser);
        etPassword = findViewById(R.id.etNewPw);
        etPassword2 = findViewById(R.id.etRePassword);
        btnRegister = findViewById(R.id.btnRegister);

        newUser = new ArrayList<Users>();
        existingUser = new ArrayList<Users>();
        existingUName = new ArrayList<String>();

        DBUsers dbh = new DBUsers(toRegister.this);

        existingUser.addAll(dbh.getAllUser());

        for (int e = 0 ; e < existingUser.size() ; e ++) {
            existingUName.add(existingUser.get(e).getUsername());
        }



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exist = false;
                for (int i = 0 ; i < existingUName.size() ; i ++) {
                    if ((etUsername.getText().toString()).equals(existingUName.get(i))) {
                        Toast.makeText(toRegister.this, "Username Already Exist", Toast.LENGTH_SHORT).show();
                        exist = true;
                        break;
                    } else {
                        exist = false;
                    }
                }

                if (exist == false) {
//                    if (exist == false) {
                        if (etPassword.getText().toString().equals(etPassword2.getText().toString())) {
                            // add to the database
                            String newUserID = etUsername.getText().toString();
                            String newPassword = etPassword.getText().toString();

                            DBUsers dbh = new DBUsers(toRegister.this);
                            long inserted_id = dbh.insertUser(newUserID, newPassword, "member");
                            Toast.makeText(toRegister.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            finish();


                        } else {
                            Toast.makeText(toRegister.this, "Password does not match.Please check your password again", Toast.LENGTH_SHORT).show();
                        }
//                    }

                } else if (exist == true) {
                        Toast.makeText(toRegister.this, "Username exist", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}