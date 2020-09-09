package PKW.StaticPage;

import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1466_StaticPage_AGB {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks elements on the AGB page")
    public void testStaticPage_AGB(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().clickHeaderAgbLink()
                .checkDownloadButtons()
                .checkElementsOnThePage()
                .checkingPriceInTheCountryList()
                .checkingFlagsInTheCountryList()
                .checkingLinksInTheText();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}


