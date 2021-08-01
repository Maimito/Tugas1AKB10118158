package com.example.uts_akb_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class FormMahasiswa extends AppCompatActivity {
    String nim, nama, kelas, prodi;
    Button simpan, edit, hapus, tampil;
    DataHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_mahasiswa);
        Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();

        simpan = (Button) findViewById(R.id.SimpanBtn);
        edit = (Button) findViewById(R.id.EditBtn);
        hapus = (Button) findViewById(R.id.HapusBtn);
        tampil = (Button) findViewById(R.id.TampilBtn);

        db = new DataHelper(this);
        db.getReadableDatabase();

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = ((EditText) findViewById(R.id.NIMTxt)).getText().toString();
                nama = ((EditText) findViewById(R.id.NamaTxt)).getText().toString();
                kelas = ((EditText) findViewById(R.id.KelasTxt)).getText().toString();
                prodi = ((EditText) findViewById(R.id.ProdiTxt)).getText().toString();

                if(nim.isEmpty() || nim == null || nama.isEmpty() || nama == null || kelas.isEmpty() || kelas == null || prodi.isEmpty() || prodi == null) {
                    Toast.makeText(getApplicationContext(),"Isi Data Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                    return;
                }

                db.simpanDataMhs(nim, nama, kelas, prodi);
                Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = ((EditText) findViewById(R.id.NIMTxt)).getText().toString();
                nama = ((EditText) findViewById(R.id.NamaTxt)).getText().toString();
                kelas = ((EditText) findViewById(R.id.KelasTxt)).getText().toString();
                prodi = ((EditText) findViewById(R.id.ProdiTxt)).getText().toString();

                if(nim.isEmpty() || nim == null || nama.isEmpty() || nama == null || kelas.isEmpty() || kelas == null || prodi.isEmpty() || prodi == null) {
                    Toast.makeText(getApplicationContext(),"Isi Data Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.mhsIsExist(nim)) {
                    db.editDataMhs(nim, nama, kelas, prodi);
                    Toast.makeText(getApplicationContext(),"Data Berhasil Diedit",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data Gagal Diedit",Toast.LENGTH_SHORT).show();
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nim = ((EditText) findViewById(R.id.NIMTxt)).getText().toString();

                if(nim.isEmpty() || nim == null) {
                    Toast.makeText(getApplicationContext(),"Isi Data Terlebih Dahulu",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(db.mhsIsExist(nim)) {
                    db.hapusDataMhs(nim);
                    Toast.makeText(getApplicationContext(),"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Data Gagal Dihapus",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> mhs = new ArrayList<String>();
                mhs = db.tampilDataMhs();
                String msg = "";

                for(int i = 0; i < mhs.size(); i++) {
                    //System.out.println(mhs.get(i));
                    msg += (mhs.get(i) + "\n");
                }

                if(mhs.size() == 0)
                    msg = "Tidak ada data mahasiswa";

                //System.out.println(msg);

                new AlertDialog.Builder(FormMahasiswa.this)
                        .setTitle("Data")
                        .setMessage(msg)
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}