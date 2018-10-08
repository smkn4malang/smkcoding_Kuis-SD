package com.dycode.edu.kuissdapp;

import android.provider.BaseColumns;

public final class KuisContract {

    private KuisContract() {

    }

    public static class tabelSoal implements BaseColumns{
        public static final String NAMA_TABEL="pertanyaan_kuis";
        public static final String KOLOM_SOAL="soal";
        public static final String PILIHAN1="pilihan1";
        public static final String PILIHAN2="pilihan2";
        public static final String PILIHAN3="pilihan3";
        public static final String PILIHAN4="pilihan4";
        public static final String KOLOM_JAWABAN="jawaban";
    }
}
