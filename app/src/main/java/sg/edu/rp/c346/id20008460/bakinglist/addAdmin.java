package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class addAdmin extends AppCompatActivity {

    EditText etAUN , etAPW , etAPW2;
    Button btnAdd ;
    ArrayList<Users> newAdmin , existingUsers;
    ArrayList<String> existingUName;
    boolean exist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        etAUN = findViewById(R.id.editTextUN);
        etAPW= findViewById(R.id.editTextAPW1);
        etAPW2= findViewById(R.id.editTextAPW2);
        btnAdd = findViewById(R.id.buttonAddAdmin);

        newAdmin = new ArrayList<Users>();
        existingUsers = new ArrayList<Users>();
        existingUName = new ArrayList<String>();

        DBUsers dbh = new DBUsers(addAdmin.this);

        existingUsers.addAll(dbh.getAllUser());

        for (int e = 0 ; e < existingUsers.size() ; e ++) {
            existingUName.add(existingUsers.get(e).getUsername());
        }



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                exist = false;
                for (int i = 0 ; i < existingUName.size() ; i ++) {
                    if ((etAUN.getText().toString()).equals(existingUName.get(i))) {
                        Toast.makeText(addAdmin.this, "Username Already Exist", Toast.LENGTH_SHORT).show();
                        exist = true;
                        break;
                    } else {
                        exist = false;
                    }
                }

                if (exist == false) {
                    if (exist == false) {
                        if (etAPW.getText().toString().equals(etAPW2.getText().toString())) {
                            // add to the database
                            String newUserID = etAUN.getText().toString();
                            String newPassword = etAPW2.getText().toString();

                            DBUsers dbh = new DBUsers(addAdmin.this);
                            long inserted_id = dbh.insertUser(newUserID, newPassword, "admin");
                            Toast.makeText(addAdmin.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                            finish();


                        } else {
                            Toast.makeText(addAdmin.this, "Password does not match.Please check your password again", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (exist == true) {
                    Toast.makeText(addAdmin.this, "Username exist", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}