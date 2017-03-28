package com.fanfandou.common.util;

import com.fanfandou.common.base.BaseLogger;
import com.fanfandou.common.exception.ServiceException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 发送短信工具类
 * Created by wudi on 2016/3/9.
 */
public class EmailUtils extends BaseLogger {

    /*腾讯的协议*/
    public static String HOST = "smtp.exmail.qq.com";
    public static String PORT = "465";
    public static String FROM = "halin-network@hilink.cc";
    public static String PWD = "H1l1nk.0105";


    /**
     * 发送邮箱方法.
     */
    public static boolean sendEmail(String title, String eAddress, String content) throws ServiceException {
        Session session = setsslSecuritysession();
        try {
            Message msg = new MimeMessage(session);
            //Set message attributes
            msg.setFrom(new InternetAddress(FROM));
            InternetAddress[] address = {new InternetAddress(eAddress)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(title);
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=utf-8");

            Transport.send(msg);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取安全证书.
     */
    private static Session setsslSecuritysession() {
        String sslFactory = "javax.net.ssl.SSLSocketFactory";
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", HOST);
        props.setProperty("mail.smtp.socketFactory.class", sslFactory);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", PORT);
        props.setProperty("mail.smtp.socketFactory.port", PORT);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        } catch (GeneralSecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        props.put("mail.smtp.ssl.socketFactory", sf);

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PWD);
            }
        };
        return Session.getDefaultInstance(props, authenticator);

    }


    /**
     * 验证邮箱.
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


}
