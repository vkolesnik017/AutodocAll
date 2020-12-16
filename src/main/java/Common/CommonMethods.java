package Common;

import io.qameta.allure.Step;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

public class CommonMethods {

    @Step("Get the expected date of a calendar in the format {dataFormat} you want, by entering the expected months {minusMonths}, days {minusDays}")
    public static String getExpectedCalendarData(String dataFormat, int minusMonths, int minusDays) {
        return DateTimeFormatter.ofPattern(dataFormat).format(LocalDateTime.now().minusMonths(minusMonths).minusDays(minusDays));
    }

    @Step("Generation of random dates for the last expected year")
    public static String generationRandomDates(int expectedYear) {
        LocalDate now = LocalDate.now();
        LocalDate then = now.minusYears(expectedYear);
        long difference = now.toEpochDay() - then.toEpochDay();
        int randomDifference = new Random().nextInt((int) difference);
        LocalDate randomDate = then.plusDays(randomDifference);
        return String.valueOf(randomDate);
    }

    @Step("Rounds the current cost {cost} as closely as possible to the expected cost {expectedCost}")
    public static Float roundOfTheCost(Float cost, Float expectedCost) {
        BigDecimal result = new BigDecimal(cost);
        BigDecimal formatCostUp = result.setScale(2, RoundingMode.UP);
        float roundMax = Float.parseFloat(String.valueOf(formatCostUp));
        BigDecimal formatCostDown = result.setScale(2, RoundingMode.FLOOR);
        float roundMin = Float.parseFloat(String.valueOf((formatCostDown)));
        float res = 0.0f;
        if (expectedCost.equals(roundMax)) {
            return res = roundMax;
        } else {
            BigDecimal resultAfter = new BigDecimal(roundMax);
            BigDecimal costUP = resultAfter.setScale(2, RoundingMode.UP);
            float formatCostUP = Float.parseFloat(String.valueOf(costUP));
            if (expectedCost.equals(formatCostUP)) {
                return res = formatCostUP;
            }
        }
        if (expectedCost.equals(roundMin)) {
            return res = roundMin;
        } else {
            BigDecimal resultAfter = new BigDecimal(roundMin);
            BigDecimal costDOWN = resultAfter.setScale(2, RoundingMode.DOWN);
            float formatCostDOWN = Float.parseFloat(String.valueOf(costDOWN));
            if (expectedCost.equals(formatCostDOWN)) {
                return res = formatCostDOWN;
            }
            return res;
        }
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

