package com.example.namlong.sdkcall;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.sdkandroid.Token.BCTHelper;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Namlong on 10/27/2017.
 */

public class MyService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "Service Started!");
        BCTHelper.getInstance().retrieveCapabilityToken("09831212","http://saas-dev.beyond-inc.jp");
    }
}
