package com.emon.pocketwallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "pocket_wallet_table", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table expense (id INTEGER primary key autoincrement, amount DOUBLE, catagories text, time text, notes text )");

        db.execSQL("create table income (id INTEGER primary key autoincrement, amount DOUBLE, catagories text, time text, notes text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists expense");
        db.execSQL("drop table if exists income");

    }


    public void AddExpense(Double amount, String catagories,  String notes){

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\nHH:mm", Locale.getDefault());
        String formattedTime = sdf.format(new Date(currentTimeMillis));


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("catagories", catagories);
        contentValues.put("time", formattedTime);
        contentValues.put("notes", notes);
        db.insert("expense", null, contentValues);

    }

    public void AddInComes(Double amount, String catagories, String notes){

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\nHH:mm", Locale.getDefault());
        String formattedTime = sdf.format(new Date(currentTimeMillis));

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("catagories", catagories);
        contentValues.put("time", formattedTime);
        contentValues.put("notes", notes);



        db.insert("income", null, contentValues);

    }
    /////===========


    public Double ExpenseTotalBalance(){

        double expensetotalexpense = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("select * from expense", null);
        if (cursor != null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double expense = cursor.getDouble(1);
                expensetotalexpense = expensetotalexpense+expense;
            }

        }

        return expensetotalexpense;

    }

    public Double IncomeTotalBalance(){

        double incomeTotalBalance = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery("select * from income", null);
        if (cursor != null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                double expense = cursor.getDouble(1);
                incomeTotalBalance = incomeTotalBalance+expense;
            }

        }

        return incomeTotalBalance;

    }


    public Cursor showExpense(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from expense ORDER BY id DESC", null);

        return cursor;
    }

    public Cursor showIncome(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from income ORDER BY id DESC", null);

        return cursor;
    }


}
