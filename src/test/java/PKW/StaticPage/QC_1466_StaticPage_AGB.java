package PKW.StaticPage;

import AWS.Delivery_prices_aws;
import Common.SetUp;
import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1466_StaticPage_AGB {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "main");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks elements on the AGB page")
    public void testStaticPage_AGB(String route) throws SQLException {
        List<String> countriesNameAws = new Delivery_prices_aws()
                .openAndLoginDeliveryPriceAwsPage()
                .countriesFromDeliveryPricesAwsPage();
        openPage(route);
        new Main_page_Logic().clickHeaderAgbLink()
                .checkDownloadButtons()
                .checkElementsOnThePage()
                .checkingPriceInTheCountryList()
                .checkingFlagsInTheCountryList()
                .checkingCountriesOnSiteAndOnAWS(countriesNameAws)
                .checkingLinksInTheText();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}


