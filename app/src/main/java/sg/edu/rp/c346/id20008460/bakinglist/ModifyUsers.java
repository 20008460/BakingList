package sg.edu.rp.c346.id20008460.bakinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModifyUsers extends AppCompatActivity {

    EditText etName , etPw1 , etPw2;
    Button btnUpdate, btnCancel, btnDelete;
    Users data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_users);

        etName = findViewById(R.id.etUpdateUN);
        etPw1 = findViewById(R.id.etUpdatePw1);
        etPw2 = findViewById(R.id.etUpdatePw2);

        btnUpdate = findViewById(R.id.buttonUpdate);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);

        Intent i = getIntent();
        data = (Users) i.getSerializableExtra("data");

        etName.setText(data.getUsername());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBUsers dbh = new DBUsers(ModifyUsers.this);
                data.setUsername(etName.getText().toString());
                data.setPassword(etPw1.getText().toString());

                dbh.updateUser(data);
                dbh.close();

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(ModifyUsers.this, manageUsers.class);
                startActivity(show);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBUsers dbh = new DBUsers(ModifyUsers.this);
                dbh.deleteUser(data.getId());

                finish();
            }
        });

    }
}