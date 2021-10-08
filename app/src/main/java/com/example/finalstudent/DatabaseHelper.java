package com.example.finalstudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;


import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String STUDENT_TABLE = "STUDENT_TABLE";
    public static final String COLUMN_STUDENT_NAME = "STUDENT_NAME";
    public static final String COLUMN_STUDENT_SURNAME = "STUDENT_SURNAME";
    public static final String COLUMN_STUDENT_EMAIL = "STUDENT_EMAIL";
    public static final String COLUMN_STUDENT_PASSWORD = "STUDENT_PASSWORD";
    public static final String COLUMN_STUDENT_CONFIRMP = "STUDENT_CONFIRMP";
    public static final String COLUMN_ID = "ID";

    private final Context context;
    public static final String TUTOR_TABLE = "TUTOR_TABLE";
    public static final String COLUMN_TUTOR_NAME = "TUTOR_NAME";
    public static final String COLUMN_TUTOR_SURNAME = "TUTOR_SURNAME";
    public static final String COLUMN_TUTOR_EMAIL = "TUTOR_EMAIL";
    public static final String COLUMN_TUTOR_PASSWORD = "TUTOR_PASSWORD";
    public static final String COLUMN_TUTOR_ID = "TUTOR_ID";
    public static final String COLUMN_TUTOR_ACADEMICS = "TUTOR_ACADEMICS";
    public static final String COLUMN_APPROVAL_STATUS = "APPROVAL_STATUS";

    public static final String MODULE_TABLE = "MODULE_TABLE";
    public static final String COLUMN_MODULE_NAME = "MODULE_NAME";
    public static final String COLUMN_MODULE_ID = "MODULE_CODE";
    public static final String COLUMN_MODULE_DESCRIPTION = "MODULE_DESCRIPTION";

    public static final String MODULE_TUT_TABLE = "MODULE_TUT";
    public static final String COLUMN_TUT_ID = "MODULE_TUT_ID";

    public static final String PROJECT_TABLE ="PROJECTS";
    public static final String COLUMN_PROJECT_ID="PROJECT_ID";
    public static final String COLUMN_NOTES = "NOTES";
    public static final String COLUMN_VIDEOS = "VIDEOS";

    public static final String SESSION_TABLE ="SESSION";
    public static final String COLUMN_SESSION_ID ="SESSION_ID";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_IS_BOOKED ="IS_BOOKED";

    public static final String RATING_TABLE = "RATING";
    public static final String COLUMN_RATING_ID = "RATING_ID";
    public static final String COLUMN_NUM_STARS = "NUM_STARS";
    public static final String COLUMN_COMMENT ="COMMENT";

    private ByteArrayOutputStream objectByteArrayOutputStream;
    private byte[] imageInBytes;
/*

    public static final String DATABASE_NAME = "Student.sqLiteDatabase";
    public static final String TABLE_NAME = "Student_Table";
    public static final String Col_1 = "ID";
    public static final String  Col_2 = "NAME";
    public static final String Col_3 = "SURNAME";
    public static final String Col_4 = "MARKS";

 */


    public DatabaseHelper(@Nullable Context context) {
        super(context,"TutorApp.sqLiteDatabase",null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        try {
            sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON");
            String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + " (  " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_STUDENT_NAME + " TEXT, " + COLUMN_STUDENT_SURNAME + " TEXT, " + COLUMN_STUDENT_EMAIL + " TEXT, " + COLUMN_STUDENT_PASSWORD + " INT, " + COLUMN_STUDENT_CONFIRMP + " INT )";
            String tutorTable = "CREATE TABLE " + TUTOR_TABLE + " (  " + COLUMN_TUTOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TUTOR_NAME + " TEXT, " + COLUMN_TUTOR_SURNAME + " TEXT, " + COLUMN_TUTOR_EMAIL + " TEXT, " + COLUMN_TUTOR_PASSWORD + " INT, " + COLUMN_APPROVAL_STATUS + " INT DEFAULT 0," + COLUMN_TUTOR_ACADEMICS + "BLOB)";
            String ModuleTable = "CREATE TABLE " + MODULE_TABLE + " (  " + COLUMN_MODULE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_MODULE_NAME + " TEXT UNIQUE, " + COLUMN_MODULE_DESCRIPTION + " TEXT )";
            String ModuleTutTable = "CREATE TABLE " + MODULE_TUT_TABLE + " (  " + COLUMN_TUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_TUTOR_ID +" INTEGER, " +  COLUMN_MODULE_ID +" INTEGER, "+" FOREIGN KEY (" + COLUMN_TUTOR_ID + ")" + "REFERENCES " + TUTOR_TABLE + " (" + COLUMN_TUTOR_ID + "),"  + "FOREIGN KEY (" + COLUMN_MODULE_ID + ")" + "REFERENCES " + MODULE_TABLE + " (" + COLUMN_MODULE_ID + "))";
            String RatingTable = "CREATE TABLE " + RATING_TABLE + " (  " + COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NUM_STARS + "INTEGER, " + COLUMN_COMMENT + "TEXT, " + COLUMN_ID +" INTEGER, "+ COLUMN_TUTOR_ID +" INTEGER, "+ " FOREIGN KEY (" + COLUMN_TUTOR_ID + ")" + "REFERENCES " + TUTOR_TABLE + " (" + COLUMN_TUTOR_ID + "), " + "FOREIGN KEY (" + COLUMN_ID + ")" + "REFERENCES " + STUDENT_TABLE + " (" + COLUMN_ID + "))";
            String SessionTable = "CREATE TABLE " + SESSION_TABLE + " (  " + COLUMN_SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_DATE + " TEXT, " + COLUMN_IS_BOOKED + " INT DEFAULT 0," + COLUMN_ID +" INTEGER, "+ COLUMN_TUT_ID +" INTEGER, "+ " FOREIGN KEY (" + COLUMN_ID + ")" + "REFERENCES " + STUDENT_TABLE + " (" + COLUMN_ID + "), " + "FOREIGN KEY (" + COLUMN_TUT_ID + ")" + "REFERENCES " + MODULE_TUT_TABLE + " (" + COLUMN_TUT_ID + "))";
            String projectTable = "CREATE TABLE " + PROJECT_TABLE + " (  " + COLUMN_PROJECT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NOTES + " BLOB, "+COLUMN_VIDEOS + "BLOB, " +  COLUMN_TUT_ID +" INTEGER," + " FOREIGN KEY (" + COLUMN_TUT_ID + ")" + "REFERENCES " + MODULE_TUT_TABLE + " (" + COLUMN_TUT_ID + "))";
            sqLiteDatabase.execSQL(ModuleTable);
            sqLiteDatabase.execSQL(createTableStatement);
            sqLiteDatabase.execSQL(tutorTable);
            sqLiteDatabase.execSQL(ModuleTutTable);
            sqLiteDatabase.execSQL(RatingTable);
            sqLiteDatabase.execSQL(SessionTable);
            sqLiteDatabase.execSQL(projectTable);
        }
        catch (Exception e){
        Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TUTOR_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MODULE_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ MODULE_TUT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SESSION_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RATING_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PROJECT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean addOne(StudentModel std_Model)
    {
        if (checkUsernameStudent(std_Model.getEmail()).getCount() > 0 || checkUsername(std_Model.getEmail()).getCount() > 0) {
            Toast.makeText(context, "Student Email already exist", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(COLUMN_STUDENT_NAME, std_Model.getName());
            cv.put(COLUMN_STUDENT_SURNAME, std_Model.getSurname());
            cv.put(COLUMN_STUDENT_EMAIL, std_Model.getEmail());
            cv.put(COLUMN_STUDENT_PASSWORD, std_Model.getPassword());
            cv.put(COLUMN_STUDENT_CONFIRMP, std_Model.getConfirmP());

            long insert = db.insert(STUDENT_TABLE, null, cv);

            if (insert == -1) {
                return false;
            } else {
                return true;
            }
        }
    }

    void addTutor(String Name, String Surname, String Email, int password, Bitmap image){
        try{
        SQLiteDatabase db = this.getWritableDatabase();

        objectByteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap imageToStoreBitmap = image;
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayOutputStream);
        imageInBytes = objectByteArrayOutputStream.toByteArray();
        ContentValues cv = new ContentValues();


        if (checkUsername(Email).getCount() > 0 || checkUsernameStudent(Email).getCount() > 0) {
            Toast.makeText(context, "Tutor Email already exist", Toast.LENGTH_SHORT).show();}
        else {
            cv.put(COLUMN_TUTOR_NAME, Name);
            cv.put(COLUMN_TUTOR_SURNAME, Surname);
            cv.put(COLUMN_TUTOR_EMAIL, Email);
            cv.put(COLUMN_TUTOR_PASSWORD, password);
            cv.put(COLUMN_APPROVAL_STATUS, 0);
            cv.put(COLUMN_TUTOR_ACADEMICS,imageInBytes);
            long result = db.insert(TUTOR_TABLE, null, cv);
            if (result == -1) {
                Toast.makeText(context, "Failed to Add a new tutor", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }}catch(Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void addModule(String Name,String Descr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if(Name.equals("")||Descr.equals("")) {
            Toast.makeText(context, "Please fill in Module Name or Description", Toast.LENGTH_SHORT).show();
            return;
        }
        if (alreadyExist(Name).getCount() > 0) {
            Toast.makeText(context, "Module already exist", Toast.LENGTH_SHORT).show();
        }else {
            cv.put(COLUMN_MODULE_NAME, Name);
            cv.put(COLUMN_MODULE_DESCRIPTION, Descr);
            long result = db.insert(MODULE_TABLE, null, cv);
            if (result == -1) {
                Toast.makeText(context, "Failed to Add New Module", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Cursor alreadyExist (String searchString) {
        String query =  "SELECT MODULE_NAME FROM MODULE_TABLE WHERE MODULE_NAME =" +"'" + searchString + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor checkUsername(String searchString){
        String query =  "SELECT TUTOR_EMAIL FROM TUTOR_TABLE WHERE TUTOR_EMAIL=" +"'" + searchString + "'" ;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor checkUsernameStudent(String searchString){
    String query =  "SELECT STUDENT_EMAIL FROM STUDENT_TABLE WHERE STUDENT_EMAIL=" +"'" + searchString + "'" ;
    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
