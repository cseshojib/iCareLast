package com.example.shojib.project_moon.Rbs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.shojib.project_moon.DBHelperPackage.DbHelper;

import java.sql.SQLException;

/**
 * Created by hasan on 11/24/15.
 */
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
    private void close()
    {
        mPDbHelper.close();
    }


    public void createNewRbs(float rbsUnit, String rbsDate, String rbsTime, long userId){

    }


}
