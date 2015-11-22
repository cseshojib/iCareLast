package com.example.shojib.project_moon.DBHelperPackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "iCare.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context, String DATABASE_NAME, String val, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    //For general_info table
    public static final String TABLE_NAME_GENERAL_INFO = "general_info";
    public static final String COLUMN_GENERAL_INFO_USER_ID = "user_id";
    public static final String COLUMN_GENERAL_INFO_PROFILE_NAME = "profile_name";
    public static final String COLUMN_GENERAL_INFO_AGE = "age";
    public static final String COLUMN_GENERAL_INFO_BLOOD_GROUP = "blood_group";
    public static final String COLUMN_GENERAL_INFO_GENDER = "gender";
    public static final String COLUMN_GENERAL_INFO_HEIGHT="height";
    public static final String COLUMN_GENERAL_INFO_WEIGHT = "weight";
    public static final String COLUMN_GENERAL_INFO_BLOOD_PRESSURE = "blood_pressure";
    public static final String COLUMN_GENERAL_INFO_DISEASE = "disease";

    private static final String SQL_CREATE_TABLE_GENERAL_INFO = "CREATE TABLE "+ TABLE_NAME_GENERAL_INFO
            + "("
            + COLUMN_GENERAL_INFO_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GENERAL_INFO_PROFILE_NAME+" TEXT NOT NULL, "
            + COLUMN_GENERAL_INFO_AGE+" REAL, "
            + COLUMN_GENERAL_INFO_BLOOD_GROUP+" TEXT, "
            + COLUMN_GENERAL_INFO_GENDER+" TEXT NOT NULL ,"
            + COLUMN_GENERAL_INFO_HEIGHT+" REAL, "
            + COLUMN_GENERAL_INFO_WEIGHT+" REAL, "
            + COLUMN_GENERAL_INFO_BLOOD_PRESSURE+" REAL, "
            + COLUMN_GENERAL_INFO_DISEASE+" TEXT "
            + ");";




    //For medication table
    public static final String TABLE_NAME_MEDICATION = "medication";
    public static final String COLUMN_MEDICATION_MEDICINE_ID = "medication_id";
    public static final String COLUMN_MEDICATION_MEDICINE_NAME = "medicine_name";
    public static final String COLUMN_MEDICATION_REASON = "reason";
    public static final String COLUMN_MEDICATION_REMINDER = "reminder";

    private static final String SQL_CREATE_TABLE_MEDICATION = "CREATE TABLE "+ TABLE_NAME_MEDICATION
            + "("
            + COLUMN_MEDICATION_MEDICINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MEDICATION_MEDICINE_NAME+" TEXT NOT NULL, "
            + COLUMN_MEDICATION_REASON+" TEXT, "
            + COLUMN_GENERAL_INFO_USER_ID+" INTEGER NOT NULL, "
            + COLUMN_MEDICATION_REMINDER+" INTEGER "
            + ");";

    //For medicine_time table
    public static final String TABLE_NAME_MEDICATION_TIME = "medication_time";
    public static final String COLUMN_MEDICATION_TIME_MEDICINE_TIME = "medicine_time";

    private static final String SQL_CREATE_TABLE_MEDICATION_TIME = "CREATE TABLE "+ TABLE_NAME_MEDICATION_TIME
            + "("
            + COLUMN_MEDICATION_MEDICINE_ID+" INTEGER NOT NULL, "
            + COLUMN_MEDICATION_TIME_MEDICINE_TIME+" TEXT "
            + ");";




    //For vaccination table
    public static final String TABLE_NAME_VACCINATION = "vaccination";
    public static final String COLUMN_VACCINATION_VACCINE_ID = "vaccine_id";
    public static final String COLUMN_VACCINATION_VACCINE_NAME = "vaccine_name";
    public static final String COLUMN_VACCINATION_REASON = "reason";
    public static final String COLUMN_VACCINATION_REMINDER = "reminder";

    private static final String SQL_CREATE_TABLE_VACCINATION = "CREATE TABLE "+ TABLE_NAME_VACCINATION
            + "("
            + COLUMN_VACCINATION_VACCINE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_VACCINATION_VACCINE_NAME+" TEXT NOT NULL, "
            + COLUMN_VACCINATION_REASON+" TEXT, "
            + COLUMN_GENERAL_INFO_USER_ID+" INTEGER NOT NULL, "
            + COLUMN_VACCINATION_REMINDER+" INTEGER "
            + ");";


    //For vaccine_date table
    public static final String TABLE_NAME_VACCINE_DATE = "vaccine_date";
    public static final String COLUMN_VACCINE_DATE_= "vaccine_date";

    private static final String SQL_CREATE_TABLE_VACCINE_DATE = "CREATE TABLE "+ TABLE_NAME_VACCINE_DATE
            + "("
            + COLUMN_VACCINATION_VACCINE_ID + " INTEGER NOT NULL, "
            + COLUMN_VACCINE_DATE_ + " TEXT "
            + ");";




    //For rbs table
    public static final String TABLE_NAME_RBS = "rbs";
    public static final String COLUMN_RBS_ID = "rbs_id";
    public static final String COLUMN_RBS_UNIT = "rbs_unit";
    public static final String COLUMN_RBS_DATE = "rbs_date";
    public static final String COLUMN_RBS_TIME = "rbs_time";

    private static final String SQL_CREATE_TABLE_RBS = "CREATE TABLE "+ TABLE_NAME_RBS
            + "("
            + COLUMN_RBS_ID   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_RBS_UNIT + " REAL NOT NULL, "
            + COLUMN_RBS_DATE + " TEXT NOT NULL, "
            + COLUMN_RBS_TIME + " TEXT NOT NULL, "
            + COLUMN_GENERAL_INFO_USER_ID + " INTEGER NOT NULL "
            +");";




    //For doctor table
    public static final String TABLE_NAME_DOCTOR = "doctor";
    public static final String COLUMN_DOCTOR_ID = "doctor_id";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String COLUMN_DOCTOR_SPECIALITY = "speciality";
    public static final String COLUMN_DOCTOR_PHONE = "phone";
    public static final String COLUMN_DOCTOR_ADDRESS = "address";
    public static final String COLUMN_DOCTOR_APPOINTMENT_DATE = "appointment_date";
    public static final String COLUMN_DOCTOR_APPOINTMENT_TIME = "appointment_time";
    public static final String COLUMN_DOCTOR_REMINDER = "reminder";


    private static final String SQL_CREATE_TABLE_DOCTOR = "CREATE TABLE "+ TABLE_NAME_DOCTOR
            + "("
            + COLUMN_DOCTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DOCTOR_NAME + " TEXT NOT NULL, "
            + COLUMN_DOCTOR_SPECIALITY + " TEXT, "
            + COLUMN_DOCTOR_PHONE + " TEXT, "
            + COLUMN_DOCTOR_ADDRESS + " TEXT, "
            + COLUMN_DOCTOR_APPOINTMENT_DATE + " TEXT, "
            + COLUMN_DOCTOR_APPOINTMENT_TIME + " TEXT, "
            + COLUMN_DOCTOR_REMINDER + " INTEGER, "
            + COLUMN_GENERAL_INFO_USER_ID + " INTEGER NOT NULL "
            + ");";




    //For diet_chart table
    public static final String TABLE_NAME_DIET_CHART = "diet_chart";
    public static final String COLUMN_DIET_ID = "diet_id";
    public static final String COLUMN_DIET_FOOD_ITEM = "food_item";

    private static final String SQL_CREATE_TABLE_DIET_CHART = "CREATE TABLE "+ TABLE_NAME_DIET_CHART
            + "("
            + COLUMN_DIET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DIET_FOOD_ITEM + " TEXT, "
            + COLUMN_GENERAL_INFO_USER_ID + " INTEGER NOT NULL"
            + ");";





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_GENERAL_INFO);
        db.execSQL(SQL_CREATE_TABLE_MEDICATION);
        db.execSQL(SQL_CREATE_TABLE_MEDICATION_TIME);
        db.execSQL(SQL_CREATE_TABLE_VACCINATION);
        db.execSQL(SQL_CREATE_TABLE_VACCINE_DATE);
        db.execSQL(SQL_CREATE_TABLE_RBS);
        db.execSQL(SQL_CREATE_TABLE_DOCTOR);
        db.execSQL(SQL_CREATE_TABLE_DIET_CHART);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Survey Db helper",
                "Upgrading the database from version " + oldVersion + " to " + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GENERAL_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MEDICATION_TIME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_VACCINATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_VACCINE_DATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RBS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DOCTOR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DIET_CHART);

        // recreate the tables
        onCreate(db);

    }



}
