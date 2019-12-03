package com.example.pineappleym.lite.Functions.WorldTime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Citybase {
    private MyOpenHelper dphelper;
    private SQLiteDatabase db;
    public Citybase(Context contxet){
        dphelper = new MyOpenHelper(contxet,"myDatabase",null,1);
        db = dphelper.getWritableDatabase();
        db = dphelper.getReadableDatabase();
    }
    public void insertCity(City city){
        ContentValues values = new ContentValues();
        values.put("name",city.getName());
        values.put("area",city.getArea());
        db.insert("city",null,values);
    }
    public void deleteCityByName(String name){
        String whereClause = "name = ?";
        String[] whereArgs = { name+"" };
        db.delete("city", whereClause, whereArgs);
    }
    public ArrayList<City> getCitys(){
        ArrayList<City> cityArrayList = new ArrayList<>();
        Cursor c = db.query("city",null,null,null,null,null,null);
        if (c.getCount() == 0 || !c.moveToFirst()) return null;
        do{
            City city = new City(c.getString(1),c.getString(2));
            cityArrayList.add(city);
        }while (c.moveToNext());
        return cityArrayList;
    }
    public void insertCityToAll(City city){
        ContentValues values = new ContentValues();
        values.put("name",city.getName());
        values.put("area",city.getArea());
        db.insert("all_city",null,values);
    }
    public boolean query(City city){
        String selection = "name = ?";
        String[] selectionArgs = { city.getName()};
        Cursor cursor = db.query("city",null,selection,selectionArgs,null,null,null);
        if(cursor.getCount()!= 0 && cursor.moveToNext()){
            return false;
        }else {
            return true;
        }
    }
    public ArrayList<City> getAllCitys(){
        ArrayList<City> cityArrayList = new ArrayList<>();
        Cursor c = db.query("all_city",null,null,null,null,null,null);
        if (c.getCount() == 0 || !c.moveToFirst()) return null;
        do{
            City city = new City(c.getString(1),c.getString(2));
            cityArrayList.add(city);
        }while (c.moveToNext());
        return cityArrayList;
    }
    public void close(){
        dphelper.close();
    }
}


