package com.example.shojib.project_moon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "member";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_AGE = "age";
    public static final String CONTACTS_COLUMN_BLOOD = "bloodgp";
    public static final String CONTACTS_COLUMN_GENDER = "gender";
    public static final String CONTACTS_COLUMN_WEIGHT = "weight";
    public static final String CONTACTS_COLUMN_ADDRESS = "address";
    public static final String CONTACTS_COLUMN_MARITAL = "marital";
    private HashMap hp;



    public static final String CONTACTS_TABLE_NAME_DOC = "doctor";
    public static final String CONTACTS_COLUMN_ID_2 = "id2";
    public static final String CONTACTS_COLUMN_NAME_DOC = "name_doctor";
    public static final String CONTACTS_COLUMN_SPECTIATIALITIES_DOC = "spectialities";
    public static final String CONTACTS_COLUMN_PHONE_DOC = "phone";
    public static final String CONTACTS_COLUMN_ADDRESS_DOC = "address_doctors";
   public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
      public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table member " +
                        "(id integer primary key, name text,age text,bloodgp text,gender text,weight text,address text,marital text)"
        );
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS member");
        onCreate(db);
    }

    public boolean insertContact  (String name, String age, String bloodgp, String gender,String weight, String address, String marital)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("bloodgp", bloodgp);
        contentValues.put("gender", gender);
        contentValues.put("weight", weight);
        contentValues.put("address", address);
        contentValues.put("marital", marital);
        db.insert("member", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from member where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String age, String bloodgp, String gender,String weight, String address, String marital)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("age", age);
        contentValues.put("bloodgp", bloodgp);
        contentValues.put("gender", gender);
        contentValues.put("weight", weight);
        contentValues.put("address", address);
        contentValues.put("marital", marital);
        db.update("member", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("member",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from member", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
