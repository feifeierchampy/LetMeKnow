package com.feifeier.letmeknow.models.mail;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import static com.feifeier.letmeknow.config.MailConfig.FROM_MAIL_ADDRESS;
import static com.feifeier.letmeknow.config.MailConfig.FROM_MAIL_PASSWORD;
import static com.feifeier.letmeknow.config.MailConfig.MAIL_SERVER_HOST;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class MailSender {

    // =====================发件人======================
    // 发件人邮箱地址
    private String mFromMailAddress;
    // 发件人邮箱密码
    private String mFromMailPassword;
    // 发件人邮箱服务器
    private String mFromServerHost;

    // ======================收件人=====================
    // 收件人邮箱地址
    private String mToMailAddress;

    // ====================邮件内容======================
    // 邮件主题
    private String mMesSubject;
    // 邮件正文
    private String mMesBodyText;

    // =============发送邮件API需要的一些变量==============
    //
    // 这些变量我也不确定是声明为全局变量还是局部变量比较好
    // 先都搞成全局的把
    // 看后续重构有没有新的想法
    //
    private Properties mProperties;
    private Session mSession;
    private Address mFromAddress;
    private Address mToAddress;

    public static class Builder {

        private String mFromMailAddress = FROM_MAIL_ADDRESS;
        private String mFromMailPassword = FROM_MAIL_PASSWORD;
        private String mFromServerHost = MAIL_SERVER_HOST;
        private String mToMailAddress;
        private String mMesSubject;
        private String mMesBodyText;

        public Builder() {
        }

        public Builder fromMailAddress(String fromMailAddress) {
            this.mFromMailAddress = fromMailAddress;
            return this;
        }

        public Builder fromMailPassword(String fromMailPassword) {
            this.mFromMailAddress = fromMailPassword;
            return this;
        }

        public Builder fromServerHost(String fromServerHost) {
            this.mFromServerHost = fromServerHost;
            return this;
        }

        public Builder toMailAddress(String toMailAddress) {
            this.mToMailAddress = toMailAddress;
            return this;
        }

        public Builder mesSubject(String mesSubject) {
            this.mMesSubject = mesSubject;
            return this;
        }

        public Builder mesBodyText(String mesBodyText) {
            this.mMesBodyText = mesBodyText;
            return this;
        }

        public MailSender build() {
            return new MailSender(this);
        }
    }

    private MailSender(Builder builder) {
        this.mFromMailAddress = builder.mFromMailAddress;
        this.mFromMailPassword = builder.mFromMailPassword;
        this.mFromServerHost = builder.mFromServerHost;
        this.mToMailAddress = builder.mToMailAddress;
        this.mMesSubject = builder.mMesSubject;
        this.mMesBodyText = builder.mMesBodyText;

        init();
    }

    private void init() {
        mProperties = new Properties();
        mProperties.put("mail.smtp.host", mFromServerHost);

        mSession = Session.getInstance(mProperties);

        try {
            // 发件地址
            mFromAddress = new InternetAddress(mFromMailAddress);
            // 收件地址
            mToAddress = new InternetAddress(mToMailAddress);
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public void send(String title, String body) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(mSession);
        mimeMessage.setFrom(mFromAddress);
        mimeMessage.setRecipient(MimeMessage.RecipientType.TO, mToAddress);
        // 邮件主题
        mimeMessage.setSubject(title);
        // 邮件正文
        mimeMessage.setText(body);
        mimeMessage.saveChanges();

        mSession.setDebug(true);

        Transport transport = mSession.getTransport("smtp");
        transport.connect(mFromServerHost, mFromMailAddress,mFromMailPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }


    public void sendEmail() throws MessagingException {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", MAIL_SERVER_HOST);

        // 基本的邮件会话
        Session session = Session.getInstance(properties);
        // 构造信息体
        MimeMessage message = new MimeMessage(session);
        // 发件地址
        Address address = new InternetAddress(FROM_MAIL_ADDRESS);
        message.setFrom(address);

        // 收件地址
        Address toAddress = new InternetAddress("332560026@qq.com");
        message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
        // 邮件主题
        message.setSubject("showmeourcode");
        // 正文
        message.setText("this is about how to read the fucking source code");

        message.saveChanges();

        session.setDebug(true);

        Transport transport = session.getTransport("smtp");
        transport.connect(MAIL_SERVER_HOST, FROM_MAIL_ADDRESS, FROM_MAIL_PASSWORD);

//        message.saveChanges();
//
//        session.setDebug(true);
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
