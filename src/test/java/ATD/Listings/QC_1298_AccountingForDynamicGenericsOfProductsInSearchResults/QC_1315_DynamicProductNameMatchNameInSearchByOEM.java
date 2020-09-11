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

public class QC_1315_DynamicProductNameMatchNameInSearchByOEM {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search27");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Dynamic Product Name Match Name In Search By OEM")
    public void testDynamicProductNameMatchNameInSearchByOEM(String route) {
        openPage(route);
        new Listing_page_Logic().checkGenericAndArticleAndOEMnumberAndGoToProductPage("CORTECO","Wellendichtring, Zwischenwelle", "46085509B", "4403530")
                .checkTextInProductTitle("Wellendichtring, Zwischenwelle")
                .checkProductFitsCarOrGoToAWS("OPEL Vivaro A Pritsche / Fahrgestell (X83) 2.5 DTi, Baujahr 08.2003 - ..., 2463 , 135 PS");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
