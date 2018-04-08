package com.taxation.web.util;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;

/**
 * 发邮件工具类
 * @author laizhiming
 *
 */
@SuppressWarnings("unchecked")
public class EmailSender {

    private static final Logger log = LoggerFactory.getLogger(EmailSender.class);
    private static ResourceBundle resource = ResourceBundle.getBundle("config");
    //application.properties
    private final static String EMAIL_HOSTNAME = resource.getString("email_hostname");//邮箱服务器，如：smtp.qq.com
    private final static String EMAIL_USERNAME = resource.getString("email_username");//邮箱账户，如：xxx@qq.com
    private final static String EMAIL_PASSWORD = resource.getString("email_password");//邮箱密码
    private final static String EMAIL_NAME = resource.getString("email_name");//发件人名称（昵称）
    private final static String EMAIL_PORT = resource.getString("email_port");//端口
    private final static String EMAIL_CHARTSET = resource.getString("email_chartset");//端口
    private final static boolean EMAIL_IS_SSL = "true".equalsIgnoreCase(resource.getString("email_is_ssl"));//是否支持SSL链接

    /**
     * 发送纯文本邮件
     * @param subject 主题
     * @param msg 内容
     * @param to 主送
     */
    public static void sendSimpleEmail(String subject, String msg, Object to){
        sendSimpleEmail(subject, msg, to, null, null);
    }

    /**
     * 发送纯文本邮件
     * @param subject 主题
     * @param msg 内容
     * @param to 主送
     * @param cc 抄送
     */
    public static void sendSimpleEmail(String subject, String msg, Object to, Object cc){
        sendSimpleEmail(subject, msg, to, cc, null);
    }

    /**
     * 发送纯文本邮件
     * @param subject 主题
     * @param msg 内容
     * @param to 主送
     * @param cc 抄送
     * @param bcc 密送
     */
    public static void sendSimpleEmail(String subject, String msg, Object to, Object cc, Object bcc){
        SimpleEmail email = new SimpleEmail();
        email.setHostName(EMAIL_HOSTNAME);
        email.setSmtpPort(Integer.parseInt(EMAIL_PORT));
        email.setAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
        email.setSSLOnConnect(EMAIL_IS_SSL);
        email.setCharset(EMAIL_CHARTSET);

        email.setSubject(subject);
        try {
            email.setFrom(EMAIL_USERNAME, EMAIL_NAME,EMAIL_CHARTSET);
            if(to instanceof String){
                email.addTo((String) to);
            }else if(to instanceof String[]){
                email.addTo((String[]) to);
            }else if(to instanceof Collection){
                email.addTo(((Collection<String>) to).toArray(new String[0]));
            }

            if (cc != null) {
                if(cc instanceof String){
                    email.addCc((String) cc);
                }else if(cc instanceof String[]){
                    email.addCc((String[]) cc);
                }else if(cc instanceof Collection){
                    email.addCc(((Collection<String>) cc).toArray(new String[0]));
                }
            }

            if (bcc != null) {
                if(bcc instanceof String){
                    email.addBcc((String) bcc);
                }else if(bcc instanceof String[]){
                    email.addBcc((String[]) bcc);
                }else if(bcc instanceof Collection){
                    email.addBcc(((Collection<String>) bcc).toArray(new String[0]));
                }
            }

            email.buildMimeMessage();

            //设置内容的字符集为UTF-8,先buildMimeMessage才能设置内容文本
            email.getMimeMessage().setText(msg,EMAIL_CHARTSET);
            email.sendMimeMessage();

            if(log.isInfoEnabled()){
                log.info("email sent success!");
            }
        } catch (EmailException e) {
            log.error(e.getMessage(), e);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args){
        System.out.println(EMAIL_HOSTNAME);
        sendSimpleEmail("taxation_service@163.com","张建伟","690045424@qq.com",null);
    }

}