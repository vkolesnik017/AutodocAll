package Common;

import io.qameta.allure.Step;

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
}

