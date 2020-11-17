package Common;

import io.qameta.allure.Step;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonMethods {


    @Step("Get the expected date of a calendar in the format {dataFormat} you want, by entering the expected months {minusMonths}, days {minusDays}")
    public static String getExpectedCalendarData(String dataFormat, int minusMonths, int minusDays) {
        String date = DateTimeFormatter.ofPattern(dataFormat).format(LocalDateTime.now().minusMonths(minusMonths).minusDays(minusDays));
        return date;
    }
}
