package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_120_FiltersSorting_TestWischblattausfuhrung {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Wischblattausfuhrung side filter")
    public void testWischblattausfuhrungFilter(String route) {
        openPage(route);
        String characteristic = listingPage.wischblattausfuhrungFilterAttribute().text();
        listingPage.wischblattausfuhrungFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Wischblattausfuhrung side filter LKW")
    public void testWischblattausfuhrungFilterLKw() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String characteristic = listingPage.wischblattausfuhrungFilterAttribute().text();
        listingPage.wischblattausfuhrungFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.wischblattausfuhrungProductAttributeGenericRoute(), listingPage.wischblattausfuhrungProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
