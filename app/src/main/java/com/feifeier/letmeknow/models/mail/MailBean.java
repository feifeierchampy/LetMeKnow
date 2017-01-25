package com.feifeier.letmeknow.models.mail;

import java.util.Properties;

/**
 * Created by CHAMPION on 2016/5/22.
 */
public class MailBean {

    // 发送邮件的服务器地址 和 端口
    private String mailServerHost = "smtp.163.com";
    private String mailServerPort = "25";

    // 发件人邮箱地址
    private String fromAddress;

    // 收件人地址
    private String toAddress;

    // 登录邮件发送服务器的用户名和密码
    private String userName;
    private String password;

    // 是否需要身份验证
    private boolean validate = true;

    // 邮件主题
    private String subject;

    // 邮件的文本内容
    private String body;

    // 获得邮件会话属性
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", this.mailServerHost);
        properties.put("mail.smtp.port", this.mailServerPort);
        properties.put("mail.smtp.auth", this.validate);

        return properties;
    }


    public String getMailServerHost() {
        return mailServerHost;
    }

    public void setMailServerHost(String mailServerHost) {
        this.mailServerHost = mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public void setMailServerPort(String mailServerPort) {
        this.mailServerPort = mailServerPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
