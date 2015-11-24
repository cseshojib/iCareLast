package com.example.shojib.project_moon.Rbs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shojib.project_moon.DBHelperPackage.DbHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RbsDatabaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns = {
            DbHelper.COLUMN_RBS_ID,
            DbHelper.COLUMN_RBS_UNIT,
            DbHelper.COLUMN_RBS_DATE,
            DbHelper.COLUMN_RBS_TIME,
            DbHelper.COLUMN_GENERAL_INFO_USER_ID
    };

    public RbsDatabaseQuery(Context context){

        this.context = context;
        this.mPDbHelper = new DbHelper(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void open() throws SQLException {
        mSqLiteDatabase=mPDbHelper.getWritableDatabase();
    }

    private void close() {
        mPDbHelper.close();
    }


    public void createNewRbs(float rbsUnit, String rbsDate, String rbsTime, long userId){

        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_RBS_UNIT, rbsUnit);
        values.put(DbHelper.COLUMN_RBS_DATE, rbsDate);
        values.put(DbHelper.COLUMN_RBS_TIME, rbsTime);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_RBS, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_RBS, allColumns, DbHelper.COLUMN_RBS_ID + " = " + insertId, null, null, null, null);

    }

    public List<RbsModule> getAllRbs(){

        List<RbsModule> rbsModuleList = new ArrayList<>();

        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_RBS, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    RbsModule rbsModule=cursorToRbsModule(cursor);
                    rbsModuleList.add(rbsModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }
        return rbsModuleList;

    }

    private RbsModule cursorToRbsModule(Cursor cursor) {

        RbsModule rbsModule = new RbsModule();

        rbsModule.setRbsId(cursor.getLong(0));
        rbsModule.setRbsUnit(cursor.getFloat(1));
        rbsModule.setRbsDate(cursor.getString(2));
        rbsModule.setRbsTime(cursor.getString(3));
        rbsModule.setUserId(cursor.getLong(4));

        return rbsModule;
    }


    private void updateRbsByRbsId(long rbsId, float rbsUnit, String rbsDate, String rbsTime, long userId) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values=new ContentValues();

        values.put(DbHelper.COLUMN_RBS_ID,rbsId);
        values.put(DbHelper.COLUMN_RBS_UNIT, rbsUnit);
        values.put(DbHelper.COLUMN_RBS_DATE, rbsDate);
        values.put(DbHelper.COLUMN_RBS_TIME, rbsTime);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);

        mSqLiteDatabase.update(DbHelper.TABLE_NAME_RBS, values, DbHelper.COLUMN_RBS_ID + " = " + rbsId, null);

        close();
    }


    public void rbsDeletByRbsId(long ePId){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_RBS, DbHelper.COLUMN_RBS_ID + " = " + ePId, null);

        close();
    }

}
