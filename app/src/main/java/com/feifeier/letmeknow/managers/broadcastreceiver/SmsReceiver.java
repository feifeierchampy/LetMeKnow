package com.feifeier.letmeknow.managers.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by champion on 17/4/22.
 * 监听短信
 */

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG= "SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent != null) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                // 每个SmsMessage都包含了SMS消息的详细信息
                // 包括发送方地址（电话号码) 时间戳 以及消息体
                SmsMessage[] messages = new SmsMessage[pdus.length];
                for(int i=0; i<pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                for(SmsMessage message : messages) {
                    String msg = message.getMessageBody();
                    long when = message.getTimestampMillis();
                    String from = message.getOriginatingAddress();

                    Log.d(TAG, "msg: " + msg + " when: " + when + " from: " + from);
                }

            }
        }
    }
}
