package ATD.LKW_trucks;

import ATD.LKW_maker_page_Logic;
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

public class QC_123_Sidebar_block_linking_to_catalog_of_model_on_catalog_brands {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_makers");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Sidebar block linking to the catalog of the model on the root catalog of brands")
    public void testChecksSidebarBlockLinkingCatalog(String route) {
        openPage(route);
         new LKW_maker_page_Logic().checkElementsOnPage()
                 .selectTopCar("MERCEDES-BENZ UNIMOG")
                 .checkSuccessfullyMakerCarListPageLoading("https://lkwteile.autodoc.de/lastkraftwagen/mercedes-benz/unimog");
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
