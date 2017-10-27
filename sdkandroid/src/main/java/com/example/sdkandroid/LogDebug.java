package com.example.sdkandroid;

import android.util.Log;

/**
 * Created by Namlong on 10/27/2017.
 */

public class LogDebug {
   private static final String LOG_TAG="MyActivity";
    public static void d(String message){
        Log.d(LOG_TAG,message);
    }
}
