package com.feifeier.letmeknow;


import com.feifeier.letmeknow.models.mail.MailSender;

import org.junit.Test;

import javax.mail.MessagingException;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class SendMailTest {

    @Test
    public void test_sendEmail() throws MessagingException, InterruptedException {
        MailSender mailSender = new MailSender.Builder()
                .toMailAddress("332560026@qq.com")
                .build();

        mailSender.send("you don't hava to", "you don't have to give it all away");
//        mailSender.sendEmail();
    }
}
