package com.example.shojib.project_moon.Medication.MedicationTime;

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
public class MedicineTimeDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns = {
            DbHelper.COLUMN_MEDICATION_MEDICINE_ID,
            DbHelper.COLUMN_MEDICATION_TIME_MEDICINE_TIME

    };

    public MedicineTimeDataBaseQuery(Context context) {
        this.context = context;
        this.mPDbHelper = new DbHelper(context, DbHelper.DATABASE_NAME, null, DbHelper.DATABASE_VERSION);
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void open() throws SQLException {
        mSqLiteDatabase = mPDbHelper.getWritableDatabase();
    }

    private void close() {
        mPDbHelper.close();
    }

    public void CreateNewMedicineTime(long medicineId, String medicineTime) {
        ContentValues values = new ContentValues();


        values.put(DbHelper.COLUMN_MEDICATION_MEDICINE_ID, medicineId);
        values.put(DbHelper.COLUMN_MEDICATION_TIME_MEDICINE_TIME, medicineTime);


        mSqLiteDatabase.query(DbHelper.TABLE_NAME_MEDICATION_TIME, allColumns, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + medicineId, null, null, null, null);


    }

    public List<MedicineTimeModule> getAllMedicineTime() {
        List<MedicineTimeModule> medicineTimeModuleList = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_NAME_MEDICATION_TIME, allColumns, null, null, null, null, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    MedicineTimeModule medicineTimeModule = cursorToMedicineTimeModule(cursor);
                    medicineTimeModuleList.add(medicineTimeModule);
                    cursor.moveToNext();

                }
            } finally {
                cursor.close();
            }
        }
        return medicineTimeModuleList;


    }

    private MedicineTimeModule cursorToMedicineTimeModule(Cursor cursor) {

        MedicineTimeModule medicineTimeModule = new MedicineTimeModule();
        medicineTimeModule.setMedicineId(cursor.getLong(0));
        medicineTimeModule.setMedicineTime(cursor.getString(1));


        return medicineTimeModule;
    }

    private void UpDateMedicineByMedicineId(long medicineId, String medicineTime) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();


        values.put(DbHelper.COLUMN_MEDICATION_MEDICINE_ID, medicineId);
        values.put(DbHelper.COLUMN_MEDICATION_TIME_MEDICINE_TIME, medicineTime);


            mSqLiteDatabase.update(DbHelper.TABLE_NAME_MEDICATION_TIME, values, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + medicineId, null);


            close();
        }

    public void MedicineTimeDeletByMedicineId(long ePId) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_MEDICATION_TIME, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + ePId, null);
        close();

    }

}
