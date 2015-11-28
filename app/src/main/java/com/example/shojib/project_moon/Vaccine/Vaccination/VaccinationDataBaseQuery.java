package com.example.shojib.project_moon.Vaccine.Vaccination;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shojib.project_moon.DBHelperPackage.DbHelper;
import com.example.shojib.project_moon.Medication.Medicine.MedicationModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suuny on 11/23/2015.
 */
public class VaccinationDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    private long vID;


    private String[] allColumns={
            DbHelper. COLUMN_VACCINATION_VACCINE_ID,
            DbHelper.COLUMN_VACCINATION_VACCINE_NAME,
            DbHelper.COLUMN_VACCINATION_REASON,
            DbHelper.COLUMN_GENERAL_INFO_USER_ID,
            DbHelper.COLUMN_VACCINATION_REMINDER

    };
    public VaccinationDataBaseQuery(Context context) {
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
    public  void createNewVaccine(String vaccineName, String vaccineReason, long userId, int reminder)
    {
        ContentValues values=new ContentValues();


        values.put(DbHelper.COLUMN_VACCINATION_VACCINE_NAME,vaccineName);
        values.put(DbHelper.COLUMN_VACCINATION_REASON,vaccineReason);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID,userId);
        values.put(DbHelper.COLUMN_VACCINATION_REMINDER,reminder);
      

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_VACCINATION, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_VACCINATION, allColumns, DbHelper.COLUMN_VACCINATION_VACCINE_ID + " = " + insertId, null, null, null, null);



    }

    public List<VaccinationModule> getAllVaccine()
    {   List<VaccinationModule> vaccinationModuleList=new ArrayList<>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_VACCINATION, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    VaccinationModule vaccinationModule=cursorToVaccineModule(cursor);
                    vaccinationModuleList.add(vaccinationModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }
        return vaccinationModuleList;


    }

    private VaccinationModule cursorToVaccineModule(Cursor cursor) {

        VaccinationModule vaccinationModule=new VaccinationModule();
        vaccinationModule.setVaccineId(cursor.getLong(0));
        vaccinationModule.setVaccineName(cursor.getString(1));
        vaccinationModule.setVaccineReason(cursor.getString(2));
        vaccinationModule.setUserId(cursor.getLong(3));
        vaccinationModule.setReminder(cursor.getInt(4));

        return vaccinationModule;
    }
    public void updateVaccineByVaccinById(long vaccineId, String vaccineName, String vaccineReason, long userId, int reminder)
    {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values=new ContentValues();


        values.put(DbHelper.COLUMN_VACCINATION_VACCINE_NAME, vaccineName);
        values.put(DbHelper.COLUMN_VACCINATION_REASON, vaccineReason);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);
        values.put(DbHelper.COLUMN_VACCINATION_REMINDER, reminder);


        mSqLiteDatabase.update(DbHelper.TABLE_NAME_VACCINATION, values, DbHelper.COLUMN_VACCINATION_VACCINE_ID + " = " + vaccineId, null);


   close();
    }

    public void vaccineDeletByVaccinId(long ePId){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_VACCINATION,DbHelper.COLUMN_VACCINATION_VACCINE_ID+ " = "+ePId,null);
        close();

    }
    public VaccinationModule getSingleVaccineById(long pID) {

        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_NAME_VACCINATION, allColumns, DbHelper.COLUMN_VACCINATION_VACCINE_ID + "=? ", new String[]{String.valueOf(pID)}, null, null, null, null);
        VaccinationModule vaccinationModule= null;

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                vaccinationModule = cursorToVaccineModule(cursor);
            }
            finally
            {
                //close();
            }
        }

        return vaccinationModule;
    }



}
