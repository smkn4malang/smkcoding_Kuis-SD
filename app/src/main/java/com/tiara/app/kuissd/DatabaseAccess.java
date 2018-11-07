package com.tiara.app.kuissd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor = null;

    public DatabaseAccess(Context context) {
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public List<Soal> getSoalMatematika() {
        List<Soal> soalList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM matematika", new String[]{});
        while (c.moveToNext()) {
            Soal soal = new Soal();
            soal.setKelas(c.getString(c.getColumnIndex("id_kelas")));
            soal.setSoal(c.getString(c.getColumnIndex("pertanyaan")));
            soal.setPilihan1(c.getString(c.getColumnIndex("pilihan1")));
            soal.setPilihan2(c.getString(c.getColumnIndex("pilihan2")));
            soal.setPilihan3(c.getString(c.getColumnIndex("pilihan3")));
            soal.setJawaban(c.getInt(c.getColumnIndex("jawaban")));
            soalList.add(soal);
        }
        c.close();
        return soalList;
    }

}
