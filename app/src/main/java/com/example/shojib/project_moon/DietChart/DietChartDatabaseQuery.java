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
            DbHelper.COLUMN_DIET_FOOD_ITEM
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

    public void createNewDietChart(String foodItem){

        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_DIET_FOOD_ITEM, foodItem);

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_DIET_CHART, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_DIET_CHART, allColumns, DbHelper.COLUMN_DIET_ID + " = " + insertId, null, null, null, null);

    }

    public void getSingleDietChartById(Long dietId){

    }

}


/*

public List<DietChartModule> getAllDietChart(){

        List<DietChartModule> dietChartModuleList = new ArrayList<>();


        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_DIET_CHART, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    DietChartModule dietChartModule = cursorToDietChartModule(cursor);
                    dietChartModuleList.add(dietChartModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }

        return dietChartModuleList;
    }

    private DietChartModule cursorToDietChartModule(Cursor cursor) {

        DietChartModule dietChartModule = new DietChartModule();

        dietChartModule.setDietId(cursor.getLong(0));
        dietChartModule.setFoodItem(cursor.getString(1));

        return null;
    }

 */