package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page;
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

public class QC_119_FiltersSorting_TestLangeFilterApplying {
    private Listing_page listingPage = new Listing_page();
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
    @Description(value = "Test checks Lange side filter")
    public void testLangeFilter(String route) {
        openPage(route);
        String characteristic = listingPage.langeFilterAttribute().text();
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Lange side filter LKW")
    public void testLangeFilterLKW() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        String characteristic = listingPage.langeFilterCheckboxLKW().text();
        listingPage.langeFilterCheckboxLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRouteLKW(), listingPage.langeProductAttributeTecdocRouteLKW());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
