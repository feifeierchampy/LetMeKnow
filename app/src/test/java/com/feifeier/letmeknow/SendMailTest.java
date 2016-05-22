package com.feifeier.letmeknow;

import com.feifeier.letmeknow.mail.MailSender;

import org.junit.Test;

import javax.mail.MessagingException;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class SendMailTest {

    @Test
    public void test_sendEmail() throws MessagingException, InterruptedException {
        MailSender sendMail = new MailSender();
        sendMail.sendEmail();
    }
}
