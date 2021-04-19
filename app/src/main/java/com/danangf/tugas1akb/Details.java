package com.danangf.tugas1akb;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    String status = "Android : ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Profil");
        Toast.makeText(getApplicationContext(), "Membuka profil dasar", Toast.LENGTH_SHORT).show();
        Log.d(status,"(qwerty) onCreate");
    }

    protected void onResume(){
        super.onResume();
        Log.d(status, "(Qwerty) onResume");
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Kembali", Toast.LENGTH_SHORT).show();
        Log.d(status, "(Qwerty) onDestroy");
    }
}