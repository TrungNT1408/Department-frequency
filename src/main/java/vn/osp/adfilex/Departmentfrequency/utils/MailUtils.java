/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author Nguyen_Toan
 */
public class MailUtils {

    public static boolean sendMail(String mailTo, String link, String emailUsername, String emailPassword) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailUsername));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject("[Smart-TeleSale]: Đăng ký tài khoản free. ", "utf-8");
            message.setContent("<h4 style='color:black;'>Xác nhận đăng ký tài khoản hệ thống Smart-TeleSale:</h4>"
                    + "<p>Để xác nhận đăng ký tài khoản hãy click vào link: " + "<a style='color:blue;text-decoration:none;' href='" + link + "'>Tại đây</a>" + " </p><br>"
                    + "<div style='with:100%;text-align:center;'>"
                    + "<p style='text-align:center;font-weight:bold'>Công ty cổ phần công nghệ cao và truyền thông số Adfilex.</p>"
                    + "<p style='text-align:center;'>Trụ sở:  ...., ......., ......., ........ , Hà Nội</p>"
                    + "<p style='text-align:center;'>Tel: (024) ..........; (024) ........; Fax: (024) ..........</p>"
                    + "<p style='text-align:center;'>Website: https://adfilex.vn/</p></div>", "text/html; charset=UTF-8");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
