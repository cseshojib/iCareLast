package com.example.shojib.project_moon.GeneralProfile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.shojib.project_moon.DBHelperPackage.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suuny on 11/23/2015.
 */
public class GeneralProfileDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns={
            DbHelper.COLUMN_GENERAL_INFO_USER_ID,
            DbHelper.COLUMN_GENERAL_INFO_PROFILE_NAME,
            DbHelper.COLUMN_GENERAL_INFO_AGE,
            DbHelper.COLUMN_GENERAL_INFO_BLOOD_GROUP,
            DbHelper.COLUMN_GENERAL_INFO_GENDER,
            DbHelper.COLUMN_GENERAL_INFO_HEIGHT,
            DbHelper.COLUMN_GENERAL_INFO_WEIGHT,
            DbHelper.COLUMN_GENERAL_INFO_BLOOD_PRESSURE,
            DbHelper.COLUMN_GENERAL_INFO_DISEASE

    };
    public GeneralProfileDataBaseQuery(Context context) {
        this.context = context;
        this.mPDbHelper = new DbHelper(context,DbHelper.DATABASE_NAME,null,DbHelper.DATABASE_VERSION);
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void open() throws SQLException {
        mSqLiteDatabase=mPDbHelper.getWritableDatabase();
    }
    private void close()
    {
        mPDbHelper.close();
    }
    public  void createNewProfile(String profileName, String age, String bloodGroup, String gender, float height, float weight, float bloodPressure, String disease)
    {
        ContentValues values=new ContentValues();


        values.put(DbHelper.COLUMN_GENERAL_INFO_PROFILE_NAME,profileName);
        values.put(DbHelper.COLUMN_GENERAL_INFO_AGE,age);
        values.put(DbHelper.COLUMN_GENERAL_INFO_BLOOD_GROUP,bloodGroup);
        values.put(DbHelper.COLUMN_GENERAL_INFO_GENDER,gender);
        values.put(DbHelper.COLUMN_GENERAL_INFO_HEIGHT,height);
        values.put(DbHelper.COLUMN_GENERAL_INFO_WEIGHT,weight);
        values.put(DbHelper.COLUMN_GENERAL_INFO_BLOOD_PRESSURE,bloodPressure);
        values.put(DbHelper.COLUMN_GENERAL_INFO_DISEASE, disease);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_GENERAL_INFO, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_GENERAL_INFO, allColumns, DbHelper.COLUMN_GENERAL_INFO_USER_ID + " = " + insertId, null, null, null, null);



    }

    public List<GeneralProfileModule> getAllProfile()
    {   List<GeneralProfileModule> generalProfileModuleList=new ArrayList<>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_GENERAL_INFO, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    GeneralProfileModule generalProfileModule=cursorToProfileModule(cursor);
                    generalProfileModuleList.add(generalProfileModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }
        return generalProfileModuleList;


    }

    private GeneralProfileModule cursorToProfileModule(Cursor cursor) {

        GeneralProfileModule generalProfileModule=new GeneralProfileModule();
        generalProfileModule.setUserId(cursor.getLong(0));
        generalProfileModule.setProfileName(cursor.getString(1));
        generalProfileModule.setAge(cursor.getString(2));
        generalProfileModule.setBloodGroup(cursor.getString(3));
        generalProfileModule.setGender(cursor.getString(4));
        generalProfileModule.setHeight(cursor.getFloat(5));
        generalProfileModule.setWeight(cursor.getFloat(6));
        generalProfileModule.setBloodPressure(cursor.getFloat(7));
        generalProfileModule.setDisease(cursor.getString(8));


        return generalProfileModule;
    }
    private void updateProfileByProfileId(long userId, String profileName, String age, String bloodGroup, String gender, float height, float weight, float bloodPressure, String disease)
    {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values=new ContentValues();

        values.put(DbHelper.COLUMN_GENERAL_INFO_PROFILE_NAME,profileName);
        values.put(DbHelper.COLUMN_GENERAL_INFO_AGE, age);
        values.put(DbHelper.COLUMN_GENERAL_INFO_BLOOD_GROUP, bloodGroup);
        values.put(DbHelper.COLUMN_GENERAL_INFO_GENDER, gender);
        values.put(DbHelper.COLUMN_GENERAL_INFO_HEIGHT, height);
        values.put(DbHelper.COLUMN_GENERAL_INFO_WEIGHT, weight);
        values.put(DbHelper.COLUMN_GENERAL_INFO_BLOOD_PRESSURE, bloodPressure);
        values.put(DbHelper.COLUMN_GENERAL_INFO_DISEASE, disease);
         mSqLiteDatabase.update(DbHelper.TABLE_NAME_GENERAL_INFO, values, DbHelper.COLUMN_GENERAL_INFO_USER_ID + " = " + userId, null);
   close();
    }

    public void profileDeletByProfileId(long ePId){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_GENERAL_INFO, DbHelper.COLUMN_GENERAL_INFO_USER_ID + " = " + ePId, null);
        close();

    }

    public GeneralProfileModule getSingleProfileById(long pID) {
        GeneralProfileModule generalProfileModule=null;

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_GENERAL_INFO,allColumns,DbHelper.COLUMN_GENERAL_INFO_USER_ID+" = ?"+pID,new String[]{String.valueOf(pID)},null,null,null);

        if (cursor != null) {
            try {
                cursor.moveToFirst();
               generalProfileModule = cursorToProfileModule(cursor);
            } finally {
                //close();
            }
        }
        return generalProfileModule;
    }
}
