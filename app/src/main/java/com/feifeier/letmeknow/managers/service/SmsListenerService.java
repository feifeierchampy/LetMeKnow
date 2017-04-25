package com.feifeier.letmeknow.managers.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class SmsListenerService extends Service {
    public SmsListenerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this, "监听短信服务启动了...", Toast.LENGTH_LONG).show();

        ContentResolver resolver = getContentResolver();

    }
}
