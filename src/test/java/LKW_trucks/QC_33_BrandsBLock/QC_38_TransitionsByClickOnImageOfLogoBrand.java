package LKW_trucks.QC_33_BrandsBLock;

import ATD.*;
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

public class QC_38_TransitionsByClickOnImageOfLogoBrand {
    private DataBase dataBase = new DataBase();

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
    public void testChecksTransitionByClickOnImageOfLogoBrand(String route) {
        openPage(route);
        new LKW_Category_page_Logic().clickOnBrand("BOSCH").checkSuccessfullyCategoryBrandPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/mf-bosch");
     }


    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderForCategoryMaker() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_maker,lkw_category_maker_brand2");
    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition by click on image of logo brand at Category_Maker_route")
    public void testChecksTransitionByClickOnImageOfLogoBrandInCategoryMaker(String categoryMakerRoute) {
        openPage(categoryMakerRoute);
        new LKW_Category_maker_Logic().clickOnBrand("KNECHT").checkSuccessfullyCategoryMakerBrandPageLoading("https://lkwteile.autodoc.de/ersatzteile/olfilter-200157/daf/mf-knecht");
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
