package com.example.shojib.project_moon.Medication.Medicine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shojib.project_moon.DBHelperPackage.DbHelper;
import com.example.shojib.project_moon.GeneralProfile.GeneralProfileModule;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MedicationDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    private String[] allColumns = {
            DbHelper.COLUMN_MEDICATION_MEDICINE_ID,
            DbHelper.COLUMN_MEDICATION_MEDICINE_NAME,
            DbHelper.COLUMN_MEDICATION_REASON,
            DbHelper.COLUMN_GENERAL_INFO_USER_ID,
            DbHelper.COLUMN_MEDICATION_REMINDER

    };


    public MedicationDataBaseQuery(Context context) {
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

    public void createNewMedication(String medicineName, String medicineReason, long userId, String reminder) {
        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_MEDICATION_MEDICINE_NAME, medicineName);
        values.put(DbHelper.COLUMN_MEDICATION_REASON, medicineReason);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);
        values.put(DbHelper.COLUMN_MEDICATION_REMINDER, reminder);

        long insertId = mSqLiteDatabase.insert(DbHelper.TABLE_NAME_MEDICATION, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_MEDICATION, allColumns, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + insertId, null, null, null, null);

    }

    public List<MedicationModule> getAllMedicine() {

        List<MedicationModule> medicationModuleList = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_NAME_MEDICATION, allColumns, null, null, null, null, null);
        if (cursor != null) {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    MedicationModule medicationModule = cursorToMedicineModule(cursor);
                    medicationModuleList.add(medicationModule);
                    cursor.moveToNext();

                }
            } finally {
                cursor.close();
            }
        }
        return medicationModuleList;

    }

    private MedicationModule cursorToMedicineModule(Cursor cursor) {

        MedicationModule medicationModule = new MedicationModule();

        medicationModule.setMedicineId(cursor.getLong(0));
        medicationModule.setMedicineName(cursor.getString(1));
        medicationModule.setMedicineReason(cursor.getString(2));
        medicationModule.setUserId(cursor.getLong(3));
        medicationModule.setReminder(cursor.getString(4));

        return medicationModule;
    }

    public void updateMedicineByMedicineId(long medicineId, String medicineName, String medicineReason, long userId, String reminder) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values = new ContentValues();

        values.put(DbHelper.COLUMN_MEDICATION_MEDICINE_NAME, medicineName);
        values.put(DbHelper.COLUMN_MEDICATION_REASON, medicineReason);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID, userId);
        values.put(DbHelper.COLUMN_MEDICATION_REMINDER, reminder);

        mSqLiteDatabase.update(DbHelper.TABLE_NAME_MEDICATION, values, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + medicineId, null);

        close();

    }

    public void medicineDeletByMedicineId(long ePId) {

        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_MEDICATION, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + " = " + ePId, null);

        close();
    }

    public MedicationModule getSingleMedicineById(long pID) {

        Cursor cursor = mSqLiteDatabase.query(DbHelper.TABLE_NAME_MEDICATION, allColumns, DbHelper.COLUMN_MEDICATION_MEDICINE_ID + "=? ", new String[]{String.valueOf(pID)}, null, null, null, null);
        MedicationModule medicationModule= null;

        if (cursor != null) {
            try {
                cursor.moveToFirst();
                medicationModule = cursorToMedicineModule(cursor);
            }
            finally
            {
                //close();
            }
        }

        return medicationModule;
    }
}

