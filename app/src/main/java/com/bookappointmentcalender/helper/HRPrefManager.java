package com.bookappointmentcalender.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.appointmentbooking.model.GetAllSpecialistModel;
import com.google.gson.Gson;

public class HRPrefManager {
    private static HRPrefManager instance;
    public static final String PREF_NAME = "foodieCustomer";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String KEY_ALL_SPECIALIST ="all_specialsit";
    private static final String WHICH_DAY_SELECED="which_day";
    private static final String WHICH_SPECIALIST_SELECTED="which_specialist";
    private static  final String SPECIALIST_NAME ="specialist_name";
    private static  final String KEY_IS_ALL_STAFF="is_all_staff";
    private Context context;
    int PRIVATE_MODE = 0;

    public HRPrefManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }
    public static HRPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new HRPrefManager(context);
        }
        return instance;
    }

    public GetAllSpecialistModel getKeyAllSpecialist(){
        Gson gson = new Gson();
        String json = preferences.getString(KEY_ALL_SPECIALIST, "");
        return gson.fromJson(json, GetAllSpecialistModel.class);
    }

    public void setKeyAllSpecialist(GetAllSpecialistModel user){
        Gson gson = new Gson();
        String json = gson.toJson(user);
        editor.putString(KEY_ALL_SPECIALIST, json);
        editor.commit();
    }

    // set here selected day
    public void setWhichDaySelected(String whichDay) {
        editor.putString(WHICH_DAY_SELECED, whichDay);
        editor.commit();
    }
    // get here selected day
    public  String getWhichDaySelected() {
        return this.preferences.getString(WHICH_DAY_SELECED, "");
    }
    // set here selected specialistId
    public void setWhichSpecialistSelected(String specialsitId) {
        editor.putString(WHICH_SPECIALIST_SELECTED, specialsitId);
        editor.commit();
    }
    // get here selected specialist id
    public  String getWhichSpecialistSelected() {
        return this.preferences.getString(WHICH_SPECIALIST_SELECTED, "");
    }
    // set here selected specialist name
    public void setWhichSpecialistSelectedName(String specialsitId) {
        editor.putString(SPECIALIST_NAME, specialsitId);
        editor.commit();
    }
    // get here selected specialist id
    public  String getWhichSpecialistSelectedName() {
        return this.preferences.getString(SPECIALIST_NAME, "");
    }
    //set all staff true
    public void setKeyIsAllStaff(boolean value) {
        setBooleanValue(KEY_IS_ALL_STAFF, value);
    }

    // get all staff true
    public boolean getKeyIsAllStaff() {
        return getBooleanValue(KEY_IS_ALL_STAFF);
    }

    public boolean getBooleanValue(String key) {
        return this.preferences.getBoolean(key, false);
    }
    public void setBooleanValue(String key, boolean value) {
        this.editor.putBoolean(key, value);
        this.editor.commit();
    }

    public void clearPrefs() {
        this.editor.clear();
        this.editor.commit();
    }
}
