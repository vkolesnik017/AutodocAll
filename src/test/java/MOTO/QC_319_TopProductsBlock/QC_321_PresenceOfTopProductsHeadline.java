package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Categories_page_Logic;
import ATD.Moto_Category_page_Logic;
import ATD.Moto_main_page_Logic;
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

public class QC_321_PresenceOfTopProductsHeadline {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2,moto_catalog2,moto_categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products headline")
    public void testChecksPresenceOfTopProductsHeadline(String route)  {
        openPage(route);

        new Moto_Categories_page_Logic()
                .presenceOfTopProductsHeadline();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products headline")
    public void testChecksPresenceOfTopProductsHeadlineBlockMain(String route)  {
        openPage(route);

        new Moto_main_page_Logic()
                .presenceOfTopProductsHeadline();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2,moto_parent_category");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products headline")
    public void testChecksPresenceOfTopProductsBlockHeadlineCategory(String route)  {
        openPage(route);

        new Moto_Category_page_Logic()
                .presenceOfTopProductsHeadline();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
