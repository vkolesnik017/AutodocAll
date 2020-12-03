package ATD.SpecificTests;

import Common.SetUp;
import PKW.CommonMethods;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AI_3_PerformanceTestForAllRoutes {
    private CommonMethods commonMethods = new CommonMethods();

    private final String result = "/Users/olhalavrynenko/Desktop/Performance.xls";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShop("prod", "SK");
    }
//AT, BG, BE, CZ, DE, DK, EE, ES, FI, FR, EN, GR, HU, IT, LD, LT, LV, NL, NO, PL, PT, RO, SE, SI,
    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Performance test for main page")
    public void testCheckingTheLoadPageTime(String route) throws IOException {
        openPage(route);
        int averageLoadTime = 3;

        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        String currentRoute = getNameRouteFromJSVarInHTML();
        String currentUrl = url();
        sleep(2000);
        // Get the Load Event End
        long loadEventEnd = (long) js.executeScript("return window.performance.timing.loadEventEnd;");
        // Get the Load Event Start
        long navigationStart = (long) js.executeScript("return window.performance.timing.navigationStart;");
        // Difference between Load Event End and Navigation Event start is Page Load Time
        long pageLoadTime_ms = loadEventEnd - navigationStart;
        long pageLoadTime_s = (loadEventEnd - navigationStart) / 1000;
        if(pageLoadTime_s>averageLoadTime+(averageLoadTime*0.3)){
            System.out.println("Notification");
        }
        System.out.println("Page Load Time is " + pageLoadTime_ms + " millisseconds.");
        System.out.println("Page Load Time is " + pageLoadTime_s + " seconds.");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        writer(result, true, "Route: " +"#"+ currentRoute + " Url: " + currentUrl + " Date: " + dateFormat.format(date) + " Load time: " + pageLoadTime_s + " s. ");
        String articleNum = parseExcel(result)[0].trim();
        writer(result, true, "Route: " + "#" + currentRoute + " Url: " + currentUrl + " Date: " + dateFormat.format(date) + " Load time: " + pageLoadTime_s + " s. ");
    }

    private void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file" + " " + fileName);
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
