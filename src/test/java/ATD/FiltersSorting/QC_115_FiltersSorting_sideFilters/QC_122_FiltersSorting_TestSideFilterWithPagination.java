package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_122_FiltersSorting_TestSideFilterWithPagination {
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
    @Description(value = "Test checks side filter with pagination")
    public void testSideFilterWithPagination(String route) {
        openPage(route);
        closeCookiesFooterMessage();
        String characteristic = listingPage.langeFilterAttribute().text();
        listingPage.langeFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        close();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter with pagination")
    public void testSideFilterWithPaginationLKW() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" +  dataBase.getRouteByRouteName("DE", "lkw_category_car_list"));
        closeCookiesFooterMessage();
        String characteristic = listingPage.langeFilterCheckboxLKW().text();
        listingPage.langeFilterCheckboxLKW().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.secondListingPage().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRouteLKW(), listingPage.langeProductAttributeTecdocRouteLKW());
        close();
    }
}
