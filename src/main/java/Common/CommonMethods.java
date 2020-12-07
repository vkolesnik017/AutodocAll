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
