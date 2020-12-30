package Common;

import io.qameta.allure.Step;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotification {

    @Step("Sending email notification about page loading time")
    public static void emailUtils(String recipient, String textMessage, String subjectMessage, String myAccountEmail, String password) throws Exception {
        System.out.println("Preparing to sent email");
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session, myAccountEmail, recipient, subjectMessage, textMessage);

        Transport.send(message);
        System.out.println("Message was sent successfully");
    }

    private static Message prepareMessage(Session session, String recipient, String myAccountEmail, String textMessage, String subjectMessage) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subjectMessage);
            message.setText(textMessage);
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
