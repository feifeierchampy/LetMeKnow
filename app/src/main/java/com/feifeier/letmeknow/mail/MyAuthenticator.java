package com.feifeier.letmeknow.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class MyAuthenticator extends Authenticator {

    private String userName;
    private String password;

    public MyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}
