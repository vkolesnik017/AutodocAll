package Common;

import io.qameta.allure.Step;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

public class CommonMethods {

    @Step("Get the expected date of a calendar in the format {dataFormat} you want, by entering the expected months {minusMonths}, days {minusDays}")
    public static String getExpectedCalendarData(String dataFormat, int minusMonths, int minusDays) {
        String date = DateTimeFormatter.ofPattern(dataFormat).format(LocalDateTime.now().minusMonths(minusMonths).minusDays(minusDays));
        return date;
    }

    @Step("Rounds the current cost {cost} as closely as possible to the expected cost {expectedCost}")
    public static Float roundOfTheCost(Float cost, Float expectedCost) {
        BigDecimal result = new BigDecimal(cost);
        BigDecimal formatCostUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatCostUp));
        BigDecimal formatCostDOWN = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatCostDOWN)));
        float res = 0.0f;
        if (expectedCost.equals(roundMax)) {
            return res = roundMax;
        }
        if (expectedCost.equals(roundMin)) {
            return res = roundMin;
        }
        return res;
    }

    @Step("Sending email notification about page loading time")
    public static void EmailUtils(String recipient, String textMessage, String subjectMessage) throws Exception {
        System.out.println("Preparing to sent email");
        final Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "olgalavr2666@gmail.com";
        String password = "8790498Ko";

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

