package com.example.gnlm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String GUNLUK_TABLE = "GUNLUK_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TARIH_GIRIS = "TARIH_GIRIS";
    public static final String COLUMN_BASLIK_GIRIS = "BASLIK_GIRIS";
    public static final String COLUMN_METIN_GIRIS = "METIN_GIRIS";
    public DatabaseHelper(@Nullable Context context) {
        super(context, "gunluk.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createtable= "CREATE TABLE " + GUNLUK_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TARIH_GIRIS + " STRING, " + COLUMN_BASLIK_GIRIS + " STRING," +COLUMN_METIN_GIRIS+ " STRING)";
        db.execSQL(createtable);
    }
    public boolean addoneGUN(GunlukNesne nesne){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TARIH_GIRIS,nesne.getTarih());
        cv.put(COLUMN_BASLIK_GIRIS,nesne.getBaslik());
        cv.put(COLUMN_METIN_GIRIS,nesne.getMetin());
        long insert=db.insert(GUNLUK_TABLE,null,cv);
        if(insert==-1){ return false; }
        else{ return true; }
    }
    public List<GunlukNesne> tumgunlukgetir(){
        List<GunlukNesne>gunluklist=new ArrayList<>();
        String query=" SELECT * FROM " +GUNLUK_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String tarih=cursor.getString(1);
                String baslik=cursor.getString(2);
                String metin=cursor.getString(3);
                GunlukNesne g=new GunlukNesne(id,tarih,baslik,metin);
                gunluklist.add(g);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gunluklist;
    }
    public boolean deletesatir(GunlukNesne gunluk){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+GUNLUK_TABLE+" WHERE "+COLUMN_ID+"="+gunluk.getId();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){ return true; }
        else { return false; }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
