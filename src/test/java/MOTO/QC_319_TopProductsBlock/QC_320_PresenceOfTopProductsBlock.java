package MOTO.QC_319_TopProductsBlock;

import ATD.*;
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

public class QC_320_PresenceOfTopProductsBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_categories,moto_catalog_model2,moto_catalog2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products block")
    public void testChecksPresenceOfTopProductsBlock(String route)  {
        openPage(route);

        new Moto_Categories_page_Logic()
                .presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesMain", parallel = true)
    Object[] dataProviderMain() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main,moto_categories_maker2");
    }

    @Test(dataProvider = "routesMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products block")
    public void testChecksPresenceOfTopProductsBlockMain(String route)  {
        openPage(route);

        new Moto_main_page_Logic()
                .presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products block")
    public void testChecksPresenceOfTopProductsBlockCategory(String route)  {
        openPage(route);

        new Moto_Category_page_Logic()
                .presenceOfTopProductsBlock();
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");
    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of TOP products block")
    public void testChecksPresenceOfTopProductsBlockParentCategory(String route)  {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .presenceOfTopProductsBlock();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
