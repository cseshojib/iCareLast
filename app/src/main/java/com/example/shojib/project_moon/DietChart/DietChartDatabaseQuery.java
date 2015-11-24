package com.example.shojib.project_moon.DietChart;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.shojib.project_moon.DBHelperPackage.DbHelper;

import java.sql.SQLException;


public class DietChartDatabaseQuery {

    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns = {
            DbHelper.COLUMN_DIET_ID,
            DbHelper.COLUMN_DIET_FOOD_ITEM,
            DbHelper.COLUMN_GENERAL_INFO_USER_ID
    };


    public DietChartDatabaseQuery(Context context){
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
    private void close()
    {
        mPDbHelper.close();
    }

    public void createNewDietChart(String foodItem, long userId){

        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_DIET_FOOD_ITEM, foodItem);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_DIET_CHART, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_DIET_CHART, allColumns, DbHelper.COLUMN_DIET_ID + " = " + insertId, null, null, null, null);

    }

}
