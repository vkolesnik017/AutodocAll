package Common;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.JavascriptExecutor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PageLoadTime {
Excel excel = new Excel();
    public void pageLoadingTime(int averageLoadTime, int percent) throws Exception {
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
                    .text("Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: 2 s.")
                    .icon_emoji(":superman:")
                    .as_user("false")
                    .build();
            SlackUtils.sendMessage(slackMessage);
            CommonMethods.emailUtils("olgalavr2666@gmail.com", "Notification about page loading time!", "Url: " + currentUrl + " Date: " + dateFormat.format(date) + ". Page load time: " + pageLoadTime_s + " s." + " Normal time is: 2 s.");

        } else {
            System.out.println("Percent norm!");
        }
    }
    public static void writeInExcel(String nameSheet, String fileName, String textOne, String textTwo, long textThree, long textFour, Date textFive) throws IOException {
        final String result = "/Users/olhalavrynenko/Desktop/Performance.xls";
        java.io.File f = new File(fileName);

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
            cell1.setCellValue(textOne);
            cell2.setCellValue(textTwo);
            cell3.setCellValue(textThree);
            cell4.setCellValue(textFour);
            cell5.setCellValue(textFive);
            FileOutputStream outFile = new FileOutputStream(f);
            workbook.write(outFile);
        } else {
            //create form scratch document
            HSSFWorkbook workbook = new HSSFWorkbook();
            //create page
            HSSFSheet sheet = workbook.createSheet(nameSheet);
            int rowNum = 0;
            Row row = sheet.createRow(rowNum);
            Cell cell = row.createCell(0, CellType.STRING);
            Cell cellUrl = row.createCell(1, CellType.STRING);
            Cell cellTime = row.createCell(2, CellType.STRING);
            Cell cellPercent = row.createCell(3, CellType.STRING);
            Cell cellDate = row.createCell(4, CellType.STRING);
            cell.setCellValue("Route");
            cellUrl.setCellValue("Url");
            cellTime.setCellValue("Page Loading Time");
            cellPercent.setCellValue("Percent, %");
            cellDate.setCellValue("Date");

            int currentRow = sheet.getPhysicalNumberOfRows() + 1;
            Row rowOne = sheet.createRow(currentRow);
            rowOne.createCell(0).setCellValue(textOne);
            rowOne.createCell(1).setCellValue(textTwo);
            rowOne.createCell(2).setCellValue(textThree);
            rowOne.createCell(3).setCellValue(textFour);
            rowOne.createCell(5).setCellValue(textFive);
            f.getParentFile().mkdirs();
            FileOutputStream outFile = new FileOutputStream(f);
            workbook.write(outFile);
            System.out.println("Created file: " + f.getAbsolutePath());
        }
}
}
