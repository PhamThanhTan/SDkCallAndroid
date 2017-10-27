package com.example.sdkandroid.Token;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Namlong on 10/27/2017.
 */

public class BCTService extends IntentService {
    public static final String TAG = BCTService.class.getSimpleName();

    public BCTService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "Service Started!");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }
}
