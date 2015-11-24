package com.example.shojib.project_moon.Vaccine.VaccineDate;

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
public class VaccineDateDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns={
            DbHelper. COLUMN_VACCINATION_VACCINE_ID,
            DbHelper.COLUMN_VACCINE_DATE_
    };
    public VaccineDateDataBaseQuery(Context context) {
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
    public  void CreateNewVaccineDate(long vaccineId, String vaccineDate)
    {
        ContentValues values=new ContentValues();
        values.put(DbHelper.COLUMN_VACCINATION_VACCINE_ID,vaccineId);
        values.put(DbHelper.COLUMN_VACCINE_DATE_,vaccineDate);

      

        mSqLiteDatabase.query(DbHelper.TABLE_NAME_VACCINE_DATE, allColumns, DbHelper.COLUMN_VACCINATION_VACCINE_ID+ " = " + vaccineId, null, null, null, null);



    }

    public List<VaccineDateModule> getAllVaccineDate()
    {   List<VaccineDateModule> vaccineDateModuleList=new ArrayList<>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_VACCINE_DATE, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    VaccineDateModule vaccineDateModule=cursorToVaccineDateModule(cursor);
                    vaccineDateModuleList.add(vaccineDateModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }
        return vaccineDateModuleList;


    }

    private VaccineDateModule cursorToVaccineDateModule(Cursor cursor) {

        VaccineDateModule vaccineDateModule=new VaccineDateModule();
        vaccineDateModule.setVaccineId(cursor.getLong(0));
        vaccineDateModule.setVaccineDate(cursor.getString(1));


        return vaccineDateModule;
    }
    private void UpDateVaccineDateByVaccinById(long vaccineId,String vaccineDate)
    {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values=new ContentValues();
        values.put(DbHelper.COLUMN_VACCINATION_VACCINE_ID,vaccineId);
        values.put(DbHelper.COLUMN_VACCINE_DATE_, vaccineDate);




        mSqLiteDatabase.update(DbHelper.TABLE_NAME_VACCINE_DATE, values, DbHelper.COLUMN_VACCINATION_VACCINE_ID + " = " + vaccineId, null);


   close();
    }

    public void vaccineDateDeletByVaccineId(long ePId){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_VACCINE_DATE,DbHelper.COLUMN_VACCINATION_VACCINE_ID+ " = "+ePId,null);
        close();

    }

}
