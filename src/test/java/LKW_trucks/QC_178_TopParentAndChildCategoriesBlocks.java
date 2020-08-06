package LKW_trucks;

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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_178_TopParentAndChildCategoriesBlocks {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks Top parent and child categories blocks")
    public void testChecksTopParentAndChildCategoriesBlocks(String route) {
        openPage(route);
        new LKW_main_page_Logic().visibilityOfTopParentBlock()
                .checkMainElementsOfParentCategoryBlock()
                .checkOfTransitionToParentCategory()
                .checkOfTransitionToChildCategory()
                .checkOfTransitionToCatalog()
                .checkSuccessfullyLKWCategoriesPageLoading();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
