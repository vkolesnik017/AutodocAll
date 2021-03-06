package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

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

import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_762 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transitions to the corresponding root when clicking on the elements of the moto catalog in the header")
    public void testChecksTransitionByClickOnElementsOfMotoCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .visibilityParentCategoriesInVerticalSelector()
                .selectParentCategoryInVerticalCatalog(1)
                .selectChildCategoryInVerticalCatalogSecondLevel(5);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category2"));
        new Moto_Category_page_Logic().selectMotoInSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list11"));
        new Moto_Category_car_list_page_Logic().clickOnLogoInHeader()
                .visibilityParentCategoriesInVerticalSelector()
                .selectParentCategoryInVerticalCatalog(1)
                .selectChildCategoryInVerticalCatalogSecondLevelWithMoto(4);
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_category_car_list11"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
