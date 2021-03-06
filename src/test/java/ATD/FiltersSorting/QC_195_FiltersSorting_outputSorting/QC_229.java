package ATD.FiltersSorting.QC_195_FiltersSorting_outputSorting;


import ATD.Listing_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_229 {
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks addToBasket buttons sorting with filter by side")
    public void testSortingWithFilterBySide() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "category_car_list"));
        new Listing_page_Logic().clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkAddToBasketButtonsSortingWithPagination();
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model11,moto_category_car_list19");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks addToBasket buttons sorting with filter by side")
    public void testSortingWithFilterBySideMoto(String routes) {
        openPage(routes);
        new Listing_page_Logic().clickFilterBySideBack()
                .waitUntilPreloaderDisappear()
                .checkSortingOfGreyButton();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
