package com.feifeier.letmeknow.managers;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;

/**
 * Created by champion on 17/4/23.
 * Sms短信数据库观察者
 */

public class SmsObserver extends ContentObserver {

    private ContentResolver mResolver;
    private SmsHandler mSmsHandler;


    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsObserver(Handler handler) {
        super(handler);

        this.mResolver =
    }

    private static class SmsHandler extends Handler {

    }
}
