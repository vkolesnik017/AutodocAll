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
//import static Common.Excel.writeInExcel;
//import static Common.PageLoadTime.pageLoadingTime;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class AI_3_PerformanceTestForAllRoutes {
    PageLoadTime pageLoadTime = new PageLoadTime();
    Excel excel = new Excel();
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
        String fileName = result;
        pageLoadTime.pageLoadingTime(3, 30);

//        excel.writeInExcel("ATD", "result", currentRoute, currentUrl, pageLoadTime_s, percentDeviate, date);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
