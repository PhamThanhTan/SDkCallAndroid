package com.example.sdkandroid.Token;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.twilio.voice.Call;
import com.twilio.voice.CallInvite;
import com.twilio.voice.RegistrationException;
import com.twilio.voice.RegistrationListener;
import com.twilio.voice.UnregistrationListener;
import com.twilio.voice.Voice;

import java.util.HashMap;

/**
 * Created by Namlong on 10/27/2017.
 */

public class BCTHelper {
    private static final String TAG = BCTHelper.class.getSimpleName();
    private Context mContext;
    private String fcmToken;
    private String token;
    private String token_chat;
    private Call actionCall;
    private static BCTHelper sInstance;
    RegistrationListener registrationListener = registrationListener();
    UnregistrationListener unregistrationListener=unregistrationListener();
    public BCTHelper(Context context) {
        mContext = context;
    }

    public static void initialize(Context context) {
        sInstance = new BCTHelper(context);
    }

    public static BCTHelper getInstance() {
        return sInstance;
    }

    public void registerWithFCM() {
        fcmToken = FirebaseInstanceId.getInstance().getToken();
        if (fcmToken != null) {
            Log.e(TAG, "Registering with FCM");
            Voice.register(mContext, token,Voice.RegistrationChannel.FCM,fcmToken, registrationListener);
        }
    }
    public String getKeyFcm(){
        return fcmToken;
    }
    public void unregisterWithFCM() {
        fcmToken =getKeyFcm();
        if (fcmToken != null) {
           Log.e(TAG, "UnRegistering with FCM");
            Voice.unregister(mContext,token, Voice.RegistrationChannel.FCM,fcmToken,unregistrationListener);
        }
    }
    private RegistrationListener registrationListener() {
        return new RegistrationListener() {
            @Override
            public void onRegistered(String s, String s1) {
                Log.e(TAG, "Successfully registered FCM " + fcmToken);
            }

            @Override
            public void onError(RegistrationException e, String s, String s1) {
                String message = String.format("Registration Error: %d, %s", e.getErrorCode(), e.getMessage());
                Log.e(TAG, message);
            }
        };
    }
    private UnregistrationListener unregistrationListener(){
        return new UnregistrationListener() {
            @Override
            public void onUnregistered(String s, String s1) {
                Log.e(TAG, "Successfully unregistered FCM ");
            }

            @Override
            public void onError(RegistrationException e, String s, String s1) {
                String message = String.format("UnRegistration Error: %d, %s", e.getErrorCode(), e.getMessage());
                Log.e(TAG, message);
            }
        };
    }
    public void retrieveCapabilityToken(String identity,String linkgetToken) {
        Uri.Builder b = Uri.parse(linkgetToken + "/accessToken").buildUpon();
        b.appendQueryParameter("clientId", identity);
        b.appendQueryParameter("clientOS", "android");
        Ion.with(mContext)
                .load(b.toString())
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject jsonObject) {
                        if (e == null) {
                            Log.e("AccessToken", jsonObject.get("token").getAsString());
                            Log.e("ClientId", jsonObject.get("identity").getAsString());
                            token = jsonObject.get("token").getAsString();
                            registerWithFCM();
                        } else {
                            Log.e(TAG, "Error retrieving token: " + e.toString());
                        }
                    }
                });
    }
    public String getToken() {
        return token;
    }

    public void call(HashMap<String, String> twilioParams, Call.Listener listener) {
        Voice.call(mContext, token, twilioParams, listener);
    }

    public void phoneReject(CallInvite callInvite) {
        if (callInvite != null)
            callInvite.reject(mContext);
    }

    public void phoneAccept(CallInvite callInvite, Call.Listener listener) {
        if (callInvite != null) {
            callInvite.accept(mContext, listener);
        }
    }

    public void phoneDisconect(Call activeCall) {
        if (activeCall != null) {
            activeCall.disconnect();
            activeCall = null;
        }
    }
}
