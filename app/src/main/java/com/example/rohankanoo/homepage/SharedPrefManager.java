package com.example.rohankanoo.homepage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rohan Kanoo on 28-06-2017.
 */
public class SharedPrefManager {
    private static SharedPrefManager mInstance;
    private static Context mCtx;

    //flag = 0 for employee login
    //flag = 1 for student login
    private static final String FLAG = "flag";
    private static final String SHARED_PREF_NAME = "mysharedpref12";
    private static final String KEY_EMPLOYEE_USERNAME = "username";
    private static final String KEY_STUDENT_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_SDMS = "sdms";
    private static final String KEY_PLACEMENT = "placement";
    private static final String KEY_BATCH = "batch";
    private static final String KEY_SSC = "ssc";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PIN = "pin";
    private static final String KEY_AADHAR = "aadhar";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_JOB = "job";
    private static final String KEY_EDUCATION = "sducation";
    private static final String KEY_FIRSTNAME = "fistname";
    private static final String KEY_LASTNAME = "lastname";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean employeeLogin(String flag, String id, String fname, String lname){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FLAG, flag);
        editor.putString(KEY_EMPLOYEE_USERNAME,id);
        editor.putString(KEY_FIRSTNAME,fname);
        editor.putString(KEY_LASTNAME, lname);
        //editor.putString(KEY_USER_EMAIL,email);

        editor.apply();

        return  true;
    }

    public boolean studentLogin(String flag, String id, String fname, String lname){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(FLAG, flag);
        editor.putString(KEY_STUDENT_USERNAME,id);
        editor.putString(KEY_FIRSTNAME,fname);
        editor.putString(KEY_LASTNAME, lname);
        //editor.putString(KEY_USER_EMAIL,email);

        editor.apply();

        return  true;
    }

    public boolean studentDetails(String id, String name, String gender, String dob, String sdms, String placement, String batch, String ssc, String address, String pin, String aadhar, String email, String number, String job, String education){

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_STUDENT_USERNAME,id);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_GENDER, gender);
        editor.putString(KEY_DOB, dob);
        editor.putString(KEY_SDMS, sdms);
        editor.putString(KEY_PLACEMENT, placement);
        editor.putString(KEY_BATCH, batch);
        editor.putString(KEY_SSC, ssc);
        editor.putString(KEY_ADDRESS, address);
        editor.putString(KEY_PIN, pin);
        editor.putString(KEY_AADHAR, aadhar);
        editor.putString(KEY_EMAIL,email);
        editor.putString(KEY_NUMBER, number);
        editor.putString(KEY_JOB, job);
        editor.putString(KEY_EDUCATION, education);

        editor.apply();

        return  true;
    }

    public int isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if(sharedPreferences.getString(KEY_EMPLOYEE_USERNAME, null) != null && sharedPreferences.getString(FLAG, null) == "0"){
            return 0;
        }

        else if (sharedPreferences.getString(KEY_STUDENT_USERNAME, null) != null && sharedPreferences.getString(FLAG, null) == "1") {
            return 1;
        }
        return -1;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        return true;
    }

    public String getFirstname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_FIRSTNAME, null);
    }

    public String getLastname(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_LASTNAME, null);
    }

    public String getUserid(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_STUDENT_USERNAME, null);
    }

    public String getFullName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public String getGender(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_GENDER, null);
    }

    public String getDOB(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_DOB, null);
    }

    public String getSDMS(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SDMS, null);
    }

    public String getPlacement(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PLACEMENT, null);
    }

    public String getBatch(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_BATCH, null);
    }

    public String getSSC(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SSC, null);
    }

    public String getAddress(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ADDRESS, null);
    }

    public String getPin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PIN, null);
    }

    public String getAadhar(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_AADHAR, null);
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public String getContact(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NUMBER, null);
    }

    public String getJob(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JOB, null);
    }

    public String getEducation(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EDUCATION, null);
    }
}