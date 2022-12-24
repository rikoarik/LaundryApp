package com.laundryapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.laundryapp.Model.ModelListPesanan;
import com.laundryapp.Model.ModelRiwayat;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // static variable
    private static final int DATABASE_VERSION = 1;

    // Database name
    private static final String DATABASE_NAME = "laundry";

    // table name
    private static final String PESANAN = "Pesanan";
    private static final String RIWAYAT = "Riwayat";

    // column tables pesanan
    private static final String ID_COL = "id";
    private static final String NAME_COL = "nama";
    private static final String TGL_COL = "tanggal";
    private static final String ESTIMASI_COL = "estimasi";
    private static final String JENIS_BARANG_COL = "jenis_barang";
    private static final String JUMLAH_BARANG_COL = "jumlah_barang";
    private static final String TOTAL_BARANG_COL = "total_barang";
    private static final String TOTAL_HARGA_COL = "total_harga";
    // column tables riwayat
    private static final String ID_HISTORY = "id";
    private static final String NAME_HISTORY = "nama";
    private static final String TGL_HISTORY = "tanggal";
    private static final String ESTIMASI_HISTORY = "estimasi";
    private static final String JENIS_BARANG_HISTORY = "jenis_barang";
    private static final String JUMLAH_BARANG_HISTORY = "jumlah_barang";
    private static final String TOTAL_BARANG_HISTORY = "total_barang";
    private static final String TOTAL_HARGA_HISTORY = "total_harga";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String pesanan = "CREATE TABLE " + PESANAN + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + TGL_COL + " TEXT,"
                + ESTIMASI_COL + " TEXT,"
                + JENIS_BARANG_COL + " TEXT,"
                + JUMLAH_BARANG_COL + " INTEGER,"
                + TOTAL_BARANG_COL + " INTEGER,"
                + TOTAL_HARGA_COL + " INTEGER)";
        sqLiteDatabase.execSQL(pesanan);

        String riwayat = "CREATE TABLE " + RIWAYAT + "("
                + ID_HISTORY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_HISTORY + " TEXT,"
                + TGL_HISTORY + " TEXT,"
                + ESTIMASI_HISTORY + " TEXT,"
                + JENIS_BARANG_HISTORY + " TEXT,"
                + JUMLAH_BARANG_HISTORY + " INTEGER,"
                + TOTAL_BARANG_HISTORY + " INTEGER,"
                + TOTAL_HARGA_HISTORY + " INTEGER)";

        sqLiteDatabase.execSQL(riwayat);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PESANAN);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RIWAYAT);
        onCreate(sqLiteDatabase);

    }

    public void addRiwayat(ModelRiwayat riwayat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_HISTORY, riwayat.getNamariwayat());
        values.put(TGL_HISTORY, riwayat.getTanggalriwayat());
        values.put(ESTIMASI_HISTORY, riwayat.getEstimasiriwayat());
        values.put(JENIS_BARANG_HISTORY, riwayat.getJenisriwayat());
        values.put(JUMLAH_BARANG_HISTORY, riwayat.getJumlahBarangriwayat());
        values.put(TOTAL_BARANG_HISTORY, riwayat.getTotalItemriwayat());
        values.put(TOTAL_HARGA_HISTORY, riwayat.getJumlahriwayat());
        db.insert(RIWAYAT, null, values);
        db.close();
    }

    public void addPesanan(ModelListPesanan pesanan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, pesanan.getNama());
        values.put(TGL_COL, pesanan.getTanggal());
        values.put(ESTIMASI_COL, pesanan.getEstimasi());
        values.put(JENIS_BARANG_COL, pesanan.getJenis());
        values.put(JUMLAH_BARANG_COL, pesanan.getJumlahBarang());
        values.put(TOTAL_BARANG_COL, pesanan.getTotalItem());
        values.put(TOTAL_HARGA_COL, pesanan.getJumlah());

        db.insert(PESANAN, null, values);
        db.close();

    }

    public List<ModelListPesanan> getListPesanan() {
        List<ModelListPesanan> pesananList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PESANAN;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ModelListPesanan pesanan = new ModelListPesanan();
                pesanan.setId(cursor.getInt(0));
                pesanan.setNama(cursor.getString(1));
                pesanan.setTanggal(cursor.getString(2));
                pesanan.setEstimasi(cursor.getString(3));
                pesanan.setJenis(cursor.getString(4));
                pesanan.setJumlahBarang(cursor.getString(5));
                pesanan.setTotalItem(cursor.getInt(6));
                pesanan.setJumlah(cursor.getInt(7));

                pesananList.add(pesanan);
            } while (cursor.moveToNext());
        }

        return pesananList;
    }

    public List<ModelRiwayat> getListRiwayat() {
        List<ModelRiwayat> riwayatList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + RIWAYAT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ModelRiwayat riwayat = new ModelRiwayat();
                riwayat.setIdriwayat(cursor.getInt(0));
                riwayat.setNamariwayat(cursor.getString(1));
                riwayat.setTanggalriwayat(cursor.getString(2));
                riwayat.setEstimasiriwayat(cursor.getString(3));
                riwayat.setJenisriwayat(cursor.getString(4));
                riwayat.setJumlahBarangriwayat(cursor.getString(5));
                riwayat.setTotalItemriwayat(cursor.getInt(6));
                riwayat.setJumlahriwayat(cursor.getInt(7));

                riwayatList.add(riwayat);
            } while (cursor.moveToNext());
        }

        return riwayatList;

    }

    public void delete(String name) {
        //Open the database
        SQLiteDatabase database = this.getWritableDatabase();

        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        database.execSQL("DELETE FROM " + PESANAN + " WHERE " + NAME_COL + "= '" + name + "'");

        //Close the database
        database.close();
    }

}
