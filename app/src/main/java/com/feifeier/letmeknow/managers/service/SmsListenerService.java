package com.feifeier.letmeknow.managers.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.feifeier.letmeknow.managers.SmsObserver;

public class SmsListenerService extends Service {

    private final static String TAG = "SmsListenerService";
    private final static Uri SMS_MESSAGE_URI = Uri.parse("content://sms");

    private SmsObserver mObserver;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG, "onCreate");

        Toast.makeText(this, "监听短信服务启动了...", Toast.LENGTH_LONG).show();

        ContentResolver resolver = getContentResolver();
        mObserver = new SmsObserver(resolver, new Handler());
        resolver.registerContentObserver(SMS_MESSAGE_URI, true, mObserver);

    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
        this.getContentResolver().unregisterContentObserver(mObserver);
    }
}
