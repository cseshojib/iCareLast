package com.example.shojib.project_moon.Doctor;

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
public class DoctorDataBaseQuery {
    private Context context;
    private DbHelper mPDbHelper;
    private SQLiteDatabase mSqLiteDatabase;
    
    
    private String[] allColumns={
            DbHelper.COLUMN_DOCTOR_ID,
            DbHelper.COLUMN_DOCTOR_NAME,
            DbHelper.COLUMN_DOCTOR_SPECIALITY,
            DbHelper.COLUMN_DOCTOR_PHONE,
            DbHelper.COLUMN_DOCTOR_ADDRESS ,
            DbHelper.COLUMN_DOCTOR_APPOINTMENT_DATE ,
            DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME ,
            DbHelper.COLUMN_DOCTOR_REMINDER 
         

    };
    public DoctorDataBaseQuery(Context context) {
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
    public  void createNewDoctor( String doctorName, String speciality, String phone, String address, String appointmentDate, String appointmentTime, int reminder, long userId)
    {
        ContentValues values=new ContentValues();


        values.put(DbHelper.COLUMN_DOCTOR_NAME,doctorName);
        values.put(DbHelper.COLUMN_DOCTOR_SPECIALITY,speciality);
        values.put(DbHelper.COLUMN_DOCTOR_PHONE,phone);
        values.put(DbHelper.COLUMN_DOCTOR_ADDRESS,address);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_DATE,appointmentDate);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME,appointmentTime);
        values.put(DbHelper.COLUMN_VACCINATION_REMINDER,reminder);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID,userId);
        
      

        long insertId=mSqLiteDatabase.insert(DbHelper.TABLE_NAME_DOCTOR, null, values);
        mSqLiteDatabase.query(DbHelper.TABLE_NAME_DOCTOR, allColumns, DbHelper.COLUMN_DOCTOR_ID + " = " + insertId, null, null, null, null);



    }

    public List<DoctorModule> getAllDoctor()
    {   List<DoctorModule> doctorModuleList=new ArrayList<>();
        Cursor cursor=mSqLiteDatabase.query(DbHelper.TABLE_NAME_DOCTOR, allColumns, null, null, null, null, null);
        if(cursor!=null)
        {
            try {
                cursor.moveToFirst();
                while (!cursor.isAfterLast())
                {
                    DoctorModule doctorModule=cursorToDoctorModule(cursor);
                    doctorModuleList.add(doctorModule);
                    cursor.moveToNext();

                }
            }finally {
                cursor.close();
            }
        }
        return doctorModuleList;


    }

    private DoctorModule cursorToDoctorModule(Cursor cursor) {

        DoctorModule doctorModule=new DoctorModule();
        doctorModule.setDoctorId(cursor.getLong(0));
        doctorModule.setDoctorName(cursor.getString(1));
        doctorModule.setSpeciality(cursor.getString(2));
        doctorModule.setPhone(cursor.getString(3));
        doctorModule.setAddress(cursor.getString(4));
        doctorModule.setAppointmentDate(cursor.getString(5));
        doctorModule.setAppointmentTime(cursor.getString(6));
        doctorModule.setReminder(cursor.getInt(7));
        doctorModule.setUserId(cursor.getLong(8));


        return doctorModule;
    }
    private void updateDoctorByDoctorById(long doctorId,String doctorName, String speciality, String phone, String address, String appointmentDate, String appointmentTime, int reminder, long userId)
    {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues values=new ContentValues();



        values.put(DbHelper.COLUMN_DOCTOR_NAME,doctorName);
        values.put(DbHelper.COLUMN_DOCTOR_SPECIALITY,speciality);
        values.put(DbHelper.COLUMN_DOCTOR_PHONE,phone);
        values.put(DbHelper.COLUMN_DOCTOR_ADDRESS,address);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_DATE,appointmentDate);
        values.put(DbHelper.COLUMN_DOCTOR_APPOINTMENT_TIME,appointmentTime);
        values.put(DbHelper.COLUMN_VACCINATION_REMINDER,reminder);
        values.put(DbHelper.COLUMN_GENERAL_INFO_USER_ID,userId);


        mSqLiteDatabase.update(DbHelper.TABLE_NAME_DOCTOR, values, DbHelper.COLUMN_DOCTOR_ID + " = " + doctorId, null);


   close();
    }

    public void DoctorDeletByDoctorId(long ePId){
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mSqLiteDatabase.delete(DbHelper.TABLE_NAME_DOCTOR,DbHelper.COLUMN_DOCTOR_ID+ " = "+ePId,null);
        close();

    }

}
