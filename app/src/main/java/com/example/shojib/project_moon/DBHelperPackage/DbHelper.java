package com.example.shojib.project_moon.DBHelperPackage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "survey.db";
    public static final int DATABASE_VERSION = 1;

    //For Questionnaire Table jjk
    public static final String TABLE_NAME_QUESTIONNAIRE = "questionnaire";
    public static final String COLUMN_QUESTIONNAIRE_QUESTION_ID = "_id";
    public static final String COLUMN_QUESTIONNAIRE_SURVEY_TITLE = "survey_title";
    public static final String COLUMN_QUESTIONNAIRE_QUESTION_VERSION = "question_version";
    public static final String COLUMN_QUESTIONNAIRE_FULL_QUESTION = "full_question";
    public static final String COLUMN_QUESTIONNAIRE_NUMBER_OF_TOTAL_QESTION = "total_question";
    public static final String COLUMN_QUESTIONNAIRE_LAST_UPDATE_TIME="datetime";
    public static final String COLUMN_SERVER_QUESTION_ID = "server_q_id";



    private static final String SQL_CREATE_TABLE_QUESTIONNAIRE= "CREATE TABLE "+ TABLE_NAME_QUESTIONNAIRE
            + "("
            + COLUMN_QUESTIONNAIRE_QUESTION_ID + " INTEGER PRIMARY KEY, "
            + COLUMN_QUESTIONNAIRE_SURVEY_TITLE+" TEXT NOT NULL, "
            + COLUMN_QUESTIONNAIRE_QUESTION_VERSION+" INTEGER NOT NULL, "
            + COLUMN_QUESTIONNAIRE_FULL_QUESTION+" TEXT NOT NULL, "
            + COLUMN_QUESTIONNAIRE_NUMBER_OF_TOTAL_QESTION+" INTEGER NOT NULL ,"
            +COLUMN_QUESTIONNAIRE_LAST_UPDATE_TIME+" TEXT NOT NULL, "
            +COLUMN_SERVER_QUESTION_ID+" TEXT NOT NULL "
            + ");";


    //For Answer Table
    public static final String TABLE_NAME_ANSWER="answer";
    public static final String COLUMN_ANSWER_FORM_ID="_form_id"; //answer auto incremented ID
    public static final String COLUMN_FULL_ANSWER_STRING="answer_string";
    public static final String COLUMN_ANSWER_LAST_SAVED_TIME="saved_time";
    public static final String COLUMN_ANSWER_TIME_DURATION="time_duration";
    public static final String COLUMN_COMPLETED_ANSWER ="_completed";
    public static final String COLUMN_START_LATITUDE="start_latitude";
    public static final String COLUMN_START_LONGITUDE="start_longitude";
    public static final String COLUMN_FINISH_LATITUDE="finish_latitude";
    public static final String COLUMN_FINISH_LONGITUDE="finish_longitude";



    private static final String SQL_CREATE_TABLE_ANSWER= "CREATE TABLE "+TABLE_NAME_ANSWER
            + "("
            + COLUMN_ANSWER_FORM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SERVER_QUESTION_ID+ " TEXT NOT NULL ,"
            + COLUMN_QUESTIONNAIRE_QUESTION_VERSION+" INTEGER NOT NULL, "
            + COLUMN_QUESTIONNAIRE_SURVEY_TITLE+" TEXT , "
            + COLUMN_FULL_ANSWER_STRING+" TEXT NOT NULL , "
            + COLUMN_ANSWER_LAST_SAVED_TIME+" TEXT NOT NULL , "
            + COLUMN_ANSWER_TIME_DURATION+" TEXT  , "
            + COLUMN_COMPLETED_ANSWER +" INTEGER NOT NULL , "
            + COLUMN_START_LATITUDE +" TEXT  ,"
            + COLUMN_START_LONGITUDE +" TEXT  ,"
            + COLUMN_FINISH_LATITUDE+" TEXT ,"
            + COLUMN_FINISH_LONGITUDE+" TEXT "

            + ");";


    //image tabel
    public static final  String TABLE_NAME_IMAGE ="image";
    public static final String  COLUMN_IMAGE_ID="_image_id";
    public static final String  COLUMN_IMAGE="_image";
    public static final String COLUMN_TAKEN_IMAGE_FLAG="taken_image";

    private static final String SQL_CREATE_TABLE_IMAGE=" CREATE TABLE "+ TABLE_NAME_IMAGE
            +"("
            +COLUMN_IMAGE_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_ANSWER_FORM_ID+" INTEGER NOT NULL ,"
            +COLUMN_IMAGE +" BLOB ,"
            + COLUMN_TAKEN_IMAGE_FLAG+" INTEGER NOT NULL "
            +");";



    public DbHelper(Context context, String DATABASE_NAME, String val, int DATABASE_VERSION) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_QUESTIONNAIRE);
        db.execSQL(SQL_CREATE_TABLE_ANSWER);
        db.execSQL(SQL_CREATE_TABLE_IMAGE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("Survey Db helper",
                "Upgrading the database from version " + oldVersion + " to " + newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_QUESTIONNAIRE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ANSWER);
    //    db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_IMAGE);

        // recreate the tables
        onCreate(db);

    }



}
