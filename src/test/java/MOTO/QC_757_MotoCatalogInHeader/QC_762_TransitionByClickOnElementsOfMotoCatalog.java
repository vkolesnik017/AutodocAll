package MOTO.QC_757_MotoCatalogInHeader;

import ATD.*;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_762_TransitionByClickOnElementsOfMotoCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
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
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category2"));
        new Moto_Category_page_Logic().selectMotoInSelector("4081", "12008", "135713");
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category_car_list11"));
        new Moto_Category_car_list_page_Logic().clickOnLogoInHeader()
                .visibilityParentCategoriesInVerticalSelector()
                .selectParentCategoryInVerticalCatalog(1)
                .selectChildCategoryInVerticalCatalogSecondLevelWithMoto(4);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_category_car_list11"));
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
