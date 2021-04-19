package com.danangf.tugas1akb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String status = "Android : ";

    Button button1, button2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Beranda");
    }
    protected void onStart() {
        super.onStart();
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        //event on click
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,
                        Details.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/Maimito";
                Intent web = new Intent(Intent.ACTION_VIEW);
                web.setData(Uri.parse(url));
                startActivity(web);
                Toast.makeText(getApplicationContext(), "Kembali", Toast.LENGTH_SHORT).show();
            }
        });
        // Toast.makeText(this, "Siklus onStart", Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(this, "Terima kasih telah menggunakan aplikasi ini", Toast.LENGTH_SHORT).show();
    }

}