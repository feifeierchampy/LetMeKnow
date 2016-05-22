package com.feifeier.letmeknow.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class MailSender {

    public void sendEmail() throws MessagingException {
        Properties properties = new Properties();

//        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.smtp.port", "465");
//        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.host", "smtp.163.com");
//        properties.put("mail.smtp.auth", "true");

        // 基本的邮件会话
        Session session = Session.getInstance(properties);
        // 构造信息体
        MimeMessage message = new MimeMessage(session);
        // 发件地址
        Address address = new InternetAddress("champy123@163.com");
        message.setFrom(address);

        // 收件地址
        Address toAddress = new InternetAddress("332560026@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
        // 邮件主题
        message.setSubject("Hello World");
        // 正文
        message.setText("hahahhaha");

        message.saveChanges();

        session.setDebug(true);

        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.163.com", "champy123@163.com", "l137301794011");

        message.saveChanges();

        session.setDebug(true);
        // 发送
        transport.sendMessage(message, message.getAllRecipients());

        transport.close();
    }

    public boolean sendTextEmail(MailBean mailBean) {
        Properties properties = mailBean.getProperties();

        MyAuthenticator authenticator = null;
        // 是否需要身份验证
        if (mailBean.isValidate()) {
            authenticator = new MyAuthenticator(mailBean.getUserName(), mailBean.getPassword());
        }
        // 基本的邮件会话
        Session session = Session.getInstance(properties, authenticator);

        // 构造信息体
        MimeMessage message = new MimeMessage(session);

        // 发件地址
        Address fromAddress = null;
        try {
            fromAddress = new InternetAddress(mailBean.getFromAddress());
            message.setFrom(fromAddress);
            // 收件地址
            Address toAddress = new InternetAddress(mailBean.getToAddress());

            // 邮件主题
            message.setSubject(mailBean.getSubject());

            // 邮件正文
            message.setText(mailBean.getBody());

            session.setDebug(true);

            Transport.send(message);

            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

}
