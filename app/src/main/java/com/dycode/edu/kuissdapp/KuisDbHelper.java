package com.dycode.edu.kuissdapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dycode.edu.kuissdapp.KuisContract.*;

import java.util.ArrayList;
import java.util.List;

public class KuisDbHelper extends SQLiteOpenHelper {

    public static final String NAMA_DATABASE="kuisSD.db";
    public static final int DATABASE_VERSION=1;

    private SQLiteDatabase db;

    public KuisDbHelper(Context context) {
        super(context, NAMA_DATABASE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.db = sqLiteDatabase;

        final String SQL_CREATE_TABLE_SOAL = "CREATE TABLE " +
                tabelSoal.NAMA_TABEL + "(" +
                tabelSoal._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tabelSoal.KOLOM_SOAL + " TEXT, " +
                tabelSoal.PILIHAN1 + " TEXT, " +
                tabelSoal.PILIHAN2 + " TEXT, " +
                tabelSoal.PILIHAN3 + " TEXT, " +
                tabelSoal.PILIHAN4 + " TEXT, " +
                tabelSoal.KOLOM_JAWABAN + " INTEGER" +
                ")";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SOAL);
        fillTabelSoal();

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tabelSoal.NAMA_TABEL);
        onCreate(db);

    }

    private void  fillTabelSoal(){
        Soal s1 = new Soal("A is correct", "A", "B", "C", "D", 1);
        addSoal(s1);
        Soal s2 = new Soal("B is correct", "A", "B", "C", "D", 2);
        addSoal(s2);
        Soal s3 = new Soal("C is correct", "A", "B", "C", "D", 3);
        addSoal(s3);
        Soal s4 = new Soal("D is correct", "A", "B", "C", "D", 4);
        addSoal(s4);
        Soal s5 = new Soal("A is correct again", "A", "B", "C", "D", 1);
        addSoal(s5);
    }

    private void addSoal(Soal soal){
        ContentValues cv = new ContentValues();
        cv.put(tabelSoal.KOLOM_SOAL, soal.getSoal());
        cv.put(tabelSoal.PILIHAN1, soal.getPilihan1());
        cv.put(tabelSoal.PILIHAN2, soal.getPilihan2());
        cv.put(tabelSoal.PILIHAN3, soal.getPilihan3());
        cv.put(tabelSoal.PILIHAN4, soal.getPilihan4());
        cv.put(tabelSoal.KOLOM_JAWABAN, soal.getJawaban());
        db.insert(tabelSoal.NAMA_TABEL, null, cv);
    }

    public List<Soal> getAllSoal(){
        List<Soal>soalList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + tabelSoal.NAMA_TABEL, new String[]{});

        while(c.moveToNext()){
                Soal soal = new Soal();
                soal.setSoal(c.getString(c.getColumnIndex(tabelSoal.KOLOM_SOAL)));
                soal.setPilihan1(c.getString(c.getColumnIndex(tabelSoal.PILIHAN1)));
                soal.setPilihan2(c.getString(c.getColumnIndex(tabelSoal.PILIHAN2)));
                soal.setPilihan3(c.getString(c.getColumnIndex(tabelSoal.PILIHAN3)));
                soal.setPilihan4(c.getString(c.getColumnIndex(tabelSoal.PILIHAN4)));
                soal.setJawaban(c.getInt(c.getColumnIndex(tabelSoal.KOLOM_JAWABAN)));
                soalList.add(soal);
        }

        c.close();
        return soalList;
    }
}
