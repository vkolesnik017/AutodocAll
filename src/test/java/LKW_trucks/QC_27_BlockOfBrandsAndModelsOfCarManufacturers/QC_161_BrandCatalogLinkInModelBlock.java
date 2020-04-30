package LKW_trucks.QC_27_BlockOfBrandsAndModelsOfCarManufacturers;

import ATD.LKW_main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_161_BrandCatalogLinkInModelBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check brands catalog link in Model/Brand block")
    public void testChecksBrandCatalogLink(String route) {
        openPage(route);
        new LKW_main_page_Logic()
                .goToAllTruckBrandsPage()
                .checkSuccessfullyLKWMakersPageLoading();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
