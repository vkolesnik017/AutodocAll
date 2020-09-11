package ATD.MOTO.QC_385_BlockBrandsAndModelsOfAutoManufacturers;

import ATD.Moto_Categories_maker_page_Logic;
import ATD.Moto_Categories_page_Logic;
import ATD.Moto_makers_page_Logic;
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

public class QC_387_PresenceOfBrandsAndModelsBlocksOfAutomakers {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories_maker2,moto_category_maker3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of brands and models blocks  of automakers")
    public void testChecksPresenceOfBrandsAndModelsBlocksOfAutomakers(String route) {
        openPage(route);

        new Moto_Categories_maker_page_Logic()
                .presenceOfModelsBlock();
    }

    @DataProvider(name = "routesMakers", parallel = true)
    Object[] dataProviderMakers() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_makers");
    }

    @Test(dataProvider = "routesMakers")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of brands and models blocks  of automakers")
    public void testChecksPresenceOfBrandsAndModelsBlocksOfAutomakersMakers(String route) {
        openPage(route);

        new Moto_makers_page_Logic()
                .presenceOfAutomakersBlock();
    }

    @DataProvider(name = "routesCategories", parallel = true)
    Object[] dataProviderCategories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories");
    }

    @Test(dataProvider = "routesCategories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the presence of brands and models blocks  of automakers")
    public void testChecksPresenceOfBrandsAndModelsBlocksOfAutomakersCategories(String route) {
        openPage(route);

        new Moto_Categories_page_Logic()
                .presenceOfMotomakersBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
