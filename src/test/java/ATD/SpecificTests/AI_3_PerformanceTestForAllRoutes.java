package ATD.SpecificTests;

import Common.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AI_3_PerformanceTestForAllRoutes {

    private final String result = "/Users/olhalavrynenko/Desktop/Performance.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "The test shows the page load time and reports by email when the average page load time is exceeded")
    public void testCheckingTheLoadPageTime(String route) throws Exception {
        openPage(route);
        int averageLoadTime = 3;
        int percent = 30;
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

//            SlackMessage slackMessage = SlackMessage.builder()
//                    .channel("Lavrynenko Olha")
//                    .username("Load bot")
//                    .text("Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: 2 s.")
//                    .icon_emoji(":superman:")
//                    .as_user("false")
//                    .build();
//            SlackUtils.sendMessage(slackMessage);

            CommonMethods.EmailUtils("olgalavr2666@gmail.com", "Notification about page loading time!", "Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: 2 s.");

        } else {
            System.out.println("Percent norm!");
        }
        String nameSheet = "ATD";
        //skin
        String fileName = result;
        File f = new File(fileName);

        if (f != null && f.exists()) {
            FileInputStream inputStream = new FileInputStream(f);
            // Get the workbook instance for XLS file
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            // Get first sheet from the workbook
            HSSFSheet sheet = (workbook.getSheet(nameSheet) == null)
                    ? workbook.createSheet(nameSheet) : workbook.getSheet(nameSheet);

            int currentRow = sheet.getPhysicalNumberOfRows() + 1;
            Row row = sheet.createRow(currentRow);
            Cell cell1 = row.createCell(0);
            Cell cell2 = row.createCell(1);
            Cell cell3 = row.createCell(2);
            Cell cell4 = row.createCell(3);
            Cell cell5 = row.createCell(4);
            cell1.setCellValue(currentRoute);
            cell2.setCellValue(currentUrl);
            cell3.setCellValue(dateFormat.format(date));
            cell4.setCellValue(pageLoadTime_s);
            cell5.setCellValue(percentDeviate);
            FileOutputStream outFile = new FileOutputStream(f);
            workbook.write(outFile);
        } else {
            //create form scratch document
            HSSFWorkbook workbook = new HSSFWorkbook();
            //create page
            HSSFSheet sheet = workbook.createSheet(nameSheet);
            int rowNum = 1;
            Row row = sheet.createRow(rowNum);
            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("atd");
            row.createCell(1).setCellValue(currentRoute);
            row.createCell(2).setCellValue(currentUrl);
            row.createCell(3).setCellValue(dateFormat.format(date));
            row.createCell(4).setCellValue(pageLoadTime_s);
            row.createCell(5).setCellValue(percentDeviate);
            f.getParentFile().mkdirs();
            FileOutputStream outFile = new FileOutputStream(f);
            workbook.write(outFile);
            System.out.println("Created file: " + f.getAbsolutePath());
        }
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
