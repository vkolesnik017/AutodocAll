package ATD.LKW_trucks.QC_33_BrandsBLock;

import ATD.*;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_38_TransitionsByClickOnImageOfLogoBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category2,lkw_category_brand");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on image of logo brand")
    public void testChecksTransitionByClickOnImageOfLogoBrand(String route) throws SQLException {
        openPage(route);
        new LKW_Category_page_Logic().clickOnBrand("BOSCH");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_category_brand2"));
    }

    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderForCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker2,lkw_category_maker_brand");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on image of logo brand at Category_Maker_route")
    public void testChecksTransitionByClickOnImageOfLogoBrandInCategoryMaker(String categoryMakerRoute) throws SQLException {
        openPage(categoryMakerRoute);
        new LKW_Category_maker_Logic().clickOnBrand("BOSCH");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "lkw_category_maker_brand3"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
