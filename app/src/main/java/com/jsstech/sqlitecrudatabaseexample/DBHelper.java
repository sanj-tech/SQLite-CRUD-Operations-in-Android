package com.jsstech.sqlitecrudatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userDB";
    private static final int DATABASE_VERSION = 1;
    public static final String QUERY =
            "CREATE TABLE " + UserContract.TABLE_NAME + "(" + UserContract.USER_NAME + " TEXT," + UserContract.USER_CONTACT + " TEXT," + UserContract.USER_ADDRESS + " TEXT);";


    public DBHelper(@Nullable Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE","DATABASE CREATED OR OPENED");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QUERY);
        Log.e("DATABASE","TABLE CREATED");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1) {

    }

    public void addInformation(String name,String contact,String address,SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.USER_NAME,name);
        contentValues.put(UserContract.USER_CONTACT,contact);
        contentValues.put(UserContract.USER_ADDRESS,address);
        sqLiteDatabase.insert(UserContract.TABLE_NAME,null,contentValues);
        Log.e("DATABASE","DATA INSERTED");
    }

    public Cursor getInfos(SQLiteDatabase sqLiteDatabase) {
        Cursor cursor;
        String[] columns = {UserContract.USER_NAME,UserContract.USER_CONTACT,UserContract.USER_ADDRESS};
        cursor = sqLiteDatabase.query(UserContract.TABLE_NAME,columns,null,null,null,null,null);
        return cursor;
    }


    public Cursor getContact(String user_name,SQLiteDatabase sqLiteDatabase) {
        String[] column = {UserContract.USER_CONTACT,UserContract.USER_ADDRESS};
        String selection = UserContract.USER_NAME + " LIKE ?";
        String[] selection_args = {user_name};
        Cursor cursor = sqLiteDatabase.query(UserContract.TABLE_NAME,column,selection,selection_args,null,null,null);
        return cursor;
    }

    public void deleteInformations(String user_name, SQLiteDatabase sqLiteDatabase){
        String selection = UserContract.USER_NAME+" LIKE ?";
        String[] selection_args = {user_name};
        sqLiteDatabase.delete(UserContract.TABLE_NAME, selection, selection_args);
    }

    public int updateInformations(String old_name, String new_name, String new_mobile, String new_email, SQLiteDatabase sqLiteDatabase) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContract.USER_NAME, new_name);
        contentValues.put(UserContract.USER_CONTACT, new_mobile);
        contentValues.put(UserContract.USER_ADDRESS, new_email);
        String selection = UserContract.USER_NAME+" LIKE ?";
        String[] selection_args = {old_name};
        int obj = sqLiteDatabase.update(UserContract.TABLE_NAME, contentValues, selection, selection_args);
        return obj;



    }
}
