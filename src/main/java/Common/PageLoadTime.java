package Common;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static Common.EmailNotification.emailUtils;
import static Common.Excel.writeInExcel;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PageLoadTime {


    @Step("Method get a page load time, then write results in excel.If page load time more than 30% of normal, user take a notification about loading time by email and in Slack. PageLoadTime")
    public void pageLoadingTime(int averageLoadTime, int percent, String slackWebhookUrl) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        String currentRoute = getNameRouteFromJSVarInHTML();
        String currentUrl = url();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        sleep(2000);
        long loadEventEnd = (long) js.executeScript("return window.performance.timing.loadEventEnd;");
        long navigationStart = (long) js.executeScript("return window.performance.timing.navigationStart;");
        long pageLoadTime_ms = loadEventEnd - navigationStart;
        long pageLoadTime_s = (loadEventEnd - navigationStart) / 1000;
        long percentage = (pageLoadTime_s * 100) / averageLoadTime;
        long percentDeviate = percentage - 100;
        if (percentDeviate > percent) {

            SlackUtils slackMessage = SlackUtils.builder()
                    .channel("Lavrynenko Olha")
                    .username("Load bot")
                    .text("Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: " + averageLoadTime)
                    .icon_emoji(":superman:")
                    .as_user("false")
                    .build();
            SlackUtils.sendMessage(slackMessage, slackWebhookUrl);

            emailUtils("olgalavr2666@gmail.com", "Notification about page loading time!", "Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: " + averageLoadTime, "olgalavr2666@gmail.com", "8790498Ko");

        } else {
            System.out.println("Percent norm!");
        }
        final String result = "/Users/olhalavrynenko/Desktop/Performance.xls";

        writeInExcel(result, "ATD", currentRoute, currentUrl, String.valueOf(pageLoadTime_s), percentDeviate, dateFormat.format(date));
    }
}

