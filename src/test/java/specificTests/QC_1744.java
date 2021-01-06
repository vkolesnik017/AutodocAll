package specificTests;

import ATD.CommonMethods;
import Common.Excel;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_1744 {

    private final String dataFile = "C://Autotests/files/data/QC_1744_data.xls";
    private final String result = "C://Autotests/files/res/QC_1744_result.txt";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "pkw", parallel = true)
    Object[] dataProviderPKW() {
        return new Excel().setUpAllCellFromExcel(dataFile, "pkwURL");
    }

    @Owner(value = "alex")
    @Test(dataProvider = "pkw")
    @Description(value = "")
    public void testPkwCheckingDomain(String route) throws IOException {
        try {
            open(route);
            checkingDomainName("https://test.autodoc.de/", route);
        } catch (Throwable throwable) {
            new CommonMethods().writerInFile(result, true, route + "#" + "FAIL");
        }
    }


    @DataProvider(name = "moto", parallel = true)
    Object[] dataProviderMoto() {
        return new Excel().setUpAllCellFromExcel(dataFile, "motoURL");
    }

    @Owner(value = "alex")
    @Test(dataProvider = "moto")
    @Description(value = "")
    public void testMotoCheckingDomain(String route) throws IOException {
        try {
            open(route);
            checkingDomainName("https://test.moto.autodoc.de/", route);
        } catch (Throwable throwable) {
            new CommonMethods().writerInFile(result, true, route + "#" + "FAIL");
        }
    }


    @DataProvider(name = "lkw", parallel = true)
    Object[] dataProviderLKW() {
        return new Excel().setUpAllCellFromExcel(dataFile, "lkwURL");
    }

    @Owner(value = "alex")
    @Test(dataProvider = "lkw")
    @Description(value = "")
    public void testLKWCheckingDomain(String route) throws IOException {
        try {
            open(route);
            checkingDomainName("https://test.lkwteile.autodoc.de/", route);
        } catch (Throwable throwable) {
            new CommonMethods().writerInFile(result, true, route + "#" + "FAIL");
        }
    }

    @AfterMethod
    private void tearDown() {
        closeWebDriver();
    }

    private void checkingDomainName(String domain, String route) throws IOException {
        System.out.println(route);
        String currentUrl = url();
        URL url = new URL(route);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        if (!currentUrl.contains(domain) || responseCode == 300) {
            new CommonMethods().writerInFile(result, true, route + "#" + responseCode);
            Assert.fail();

        }
    }

}
