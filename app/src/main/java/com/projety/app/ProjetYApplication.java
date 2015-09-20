package com.projety.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.LruCache;

import com.activeandroid.ActiveAndroid;
import com.projety.model.Party;
import com.projety.model.User;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCache;
import com.vincentbrison.openlibraries.android.dualcache.lib.DualCacheBuilder;
import com.vincentbrison.openlibraries.android.dualcache.lib.SizeOf;

/**
 * Created by Djeme Mahamat on 15/02/2015.
 */
public class ProjetYApplication extends Application {


    private static ProjetYApplication mInstance=null;
    private static String PREFS_NAME="PROJETY_LOGIN_PREF";
    private static String PREF_SHOW_LOGIN_ACTIVITY="SHOW_LOGIN_ACTIVITY";
    private static User mUser;
    private static DualCache cache;
    private static LruCache<Object,Object> lruCache;



    @Override
    public void onCreate() {

        super.onCreate();
       // ActiveAndroid.initialize(this);
        /**
         *  cache = new DualCacheBuilder<Party>(CACHE_NAME, TEST_APP_VERSION, Party.class)
         .useReferenceInRam(RAM_MAX_SIZE, new SizeOfVehiculeForTesting())
         .useDefaultSerializerInDisk(DISK_MAX_SIZE, true);
         *

        cache = new DualCacheBuilder<Party>("toto", 1, Party.class)
                .useReferenceInRam(10, new SizeOf<Party>() {
                    @Override
                    public int sizeOf(Party object) {
                        return 10;
                    }
                })
                .useDefaultSerializerInDisk(20, true);
         */

        lruCache = new LruCache<Object, Object>(1024);


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

    public static void setCache(LruCache<Object,Object> cache){
        lruCache=cache;
    }

    public static LruCache<Object,Object> getCache(){
        return lruCache;
    }
}