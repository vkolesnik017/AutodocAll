package ATD.Listings.QC_1298_AccountingForDynamicGenericsOfProductsInSearchResults;


import ATD.Listing_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1316_DynamicProductNameMatchNameInSearchByArticle {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "AT", "main", "search26");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Dynamic Product Name Match Name In Search By Article")
    public void testDynamicProductNameMatchNameInSearchByArticle(String route) {
        openPage(route);
        new Listing_page_Logic().clickFirstProductOnListing()
                                .checkTextInProductTitle("Glühlampe, Abbiegescheinwerfer")
                                .checkProductFitsCarOrGoToAWS("BMW 3 Touring (E91) 318 d, Baujahr 07.2007 - 06.2012, 1995 , 136 PS");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
