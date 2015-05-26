package com.projety.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import com.projety.model.User;

/**
 * Created by Djeme Mahamat on 15/02/2015.
 */
public class ProjetYApplication extends Application {


    private static ProjetYApplication mInstance=null;
    private static String PREFS_NAME="PROJETY_LOGIN_PREF";
    private static String PREF_SHOW_LOGIN_ACTIVITY="SHOW_LOGIN_ACTIVITY";
    private static User mUser;




    @Override
    public void onCreate() {

        super.onCreate();

    }

    public static ProjetYApplication getInstance() {

        if (mInstance == null) {
            mInstance = new ProjetYApplication();
        }
            return mInstance;

    }

    public static String getPREFS_NAME(){
        return  PREFS_NAME;
    }

    public static  String getPREF_SHOW_LOGIN_ACTIVITY(){
        return PREF_SHOW_LOGIN_ACTIVITY;
    }

    public static User getmUser() {
        return mUser;
    }

    public static void setmUser(User mUser) {
        getInstance().mUser = mUser;
    }
}