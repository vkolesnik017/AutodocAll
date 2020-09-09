package MOTO.QC_319_TopProductsBlock;

import ATD.Moto_Category_page_Logic;
import ATD.Moto_main_page_Logic;
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

public class QC_325_VisibilityOfTopProductCharacteristicByHover {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main,moto_categories_maker2,moto_categories,moto_catalog2,moto_catalog_model2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of TOP product characteristic by hover")
    public void testChecksVisibilityOfTopProductCharacteristicByHover(String route) {
        openPage(route);
            new Moto_main_page_Logic()
                    .visibilityOfPopUpWithAdditionInfo();
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_parent_category_maker2");   //
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks visibility of TOP product characteristic by hover")
    public void testChecksVisibilityOfTopProductCharacteristicByHoverCategory(String route) {
        openPage(route);
        new Moto_Category_page_Logic()
                .visibilityOfPopUpWithAdditionInfo();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
