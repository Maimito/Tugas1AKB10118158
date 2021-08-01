package com.example.uts_akb_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String username, password;
    Button login, logout;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button) findViewById(R.id.loginBtn);
        logout = (Button) findViewById(R.id.logoutBtn);
        db = new DataHelper(this);
        db.getReadableDatabase();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = ((EditText) findViewById(R.id.usernameText)).getText().toString();
                password = ((EditText) findViewById(R.id.passText)).getText().toString();

                if(username.isEmpty() || username == null || password.isEmpty() || password == null) {
                    Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.adminIsExist(username, password)) {
                    Intent intent = new Intent(MainActivity.this, FormMahasiswa.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"Login Gagal",Toast.LENGTH_SHORT).show();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }
}