package PKW.General_Common.QC_2321_StaticPages;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_236 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("PKW").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false) //TODO не ранится в связи с багом  SITES-9624.
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks elements on the Datenschultz page")
    public void testStaticPage_Datenschultz(String route) throws IOException {
        openPage(route);
        new Main_page_Logic().clickFooterDatenchutzLink()
                .getStatusLinksCode()
                .checkHrefAttributeOnEmails()
                .checkDownloadPdf();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}


