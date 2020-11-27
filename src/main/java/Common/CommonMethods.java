package Common;

import io.qameta.allure.Step;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        } if (expectedCost.equals(roundMin)) {
            return res = roundMin;
        }
        return res;
    }
}
