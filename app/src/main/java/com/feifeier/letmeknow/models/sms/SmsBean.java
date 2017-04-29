package com.feifeier.letmeknow.models.sms;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by champion on 17/4/22.
 * 短息实体类
 */

public class SmsBean {

    public int id;
    public String threadId;
    public String smsAddress;
    public String smsBody;
    public String read;
    public long smsDate;
    public long nowDate;
    @Override
    public String toString() {
        return "Sms: " + "id: " + id + "\n" +
                " threadId: " + threadId + "\n" +
                " smsAddress: " + smsAddress + "\n" +
                " smsBody: " + smsBody + "\n" +
                " smsDate: " + longToString(smsDate) + "\n" +
                " nowDate: " + longToString(nowDate);
    }

    public String longToString(long second) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return formatter.format(new Date(second));
    }
}
