package com.example.uts_akb_2021;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DataHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "data_mahasiswa.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Mahasiswa (" +
                "NIM VARCHAR(8) PRIMARY KEY," +
                "Nama TEXT NOT NULL," +
                "Kelas TEXT NOT NULL," +
                "Prodi Text NOT NULL)";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);

        sql = "CREATE TABLE Admin (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Username VARCHAR(12) NOT NULL," +
                "Password TEXT NOT NULL)";
        Log.d("Admin", "onCreate: " + sql);
        db.execSQL(sql);

        sql = "INSERT OR REPLACE INTO Admin (Username, Password) VALUES ('admin', 'admin')";
        Log.d("Insert admin", "onCreate: " + sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }

    public Boolean adminIsExist(String username, String password) {
        boolean isExist = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Admin WHERE Username='" + username + "' AND Password='" + password + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount() > 0)
            isExist = true;

        cursor.close();

        return isExist;
    }

    public void simpanDataMhs(String NIM, String nama, String kelas, String prodi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "INSERT INTO Mahasiswa (NIM, Nama, Kelas, Prodi) VALUES ('" + NIM + "', '" + nama + "', '" + kelas + "', '" + prodi + "')";
        db.execSQL(sql);
    }

    public void editDataMhs(String NIM, String nama, String kelas, String prodi) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "UPDATE Mahasiswa SET Nama='" + nama + "', Kelas='" + kelas + "', Prodi='" + prodi + "' WHERE NIM='" + NIM + "'";
        db.execSQL(sql);
    }

    public Boolean mhsIsExist(String NIM) {
        boolean isExist = false;

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Mahasiswa WHERE NIM='" + NIM + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if(cursor.getCount() > 0)
            isExist = true;

        cursor.close();

        return isExist;
    }

    public void hapusDataMhs(String NIM) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "DELETE FROM Mahasiswa WHERE NIM='" + NIM + "'";
        db.execSQL(sql);
    }

    public ArrayList<String> tampilDataMhs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Mahasiswa";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> mhs = new ArrayList<String>();

        if(cursor.getCount() == 0)
            return mhs;

        cursor.moveToFirst();

        do {
            String nim = cursor.getString(0);
            String nama = cursor.getString(1);
            String kelas = cursor.getString(2);
            String prodi = cursor.getString(3);

            mhs.add(nim + ", " + nama + ", " + kelas + ", " + prodi);
        } while (cursor.moveToNext());

        cursor.close();

        return mhs;
    }
}
