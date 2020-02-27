package ATD.FiltersSorting.QC_115_FiltersSorting_sideFilters;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_124_FiltersSorting_TestSideFilterCancelling {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list2,search4,search5");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling")
    public void testSideFilterCancelling(String route) {
        openPage(route);
        String characteristic = listingPage.activeSideFilter2().text();
        listingPage.activeSideFilter2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.langeProductAttributeGenericRoute().size();
        listingPage.activeSideFilter().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.langeProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
        close();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWcar() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list6"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.hoheProductAttributeGenericRouteLKW(), listingPage.hoheProductAttributeTecdocRouteLKW());
        int numberOfAttributesFilter = listingPage.hoheProductAttributeTecdocRoute().size();
        listingPage.activeSideFilterLkwHohe().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.hoheProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
        close();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWmodel() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list7"));
        String characteristic = listingPage.langeFilterAttribute2().text();
        listingPage.langeFilterCheckbox2().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.langeProductAttributeGenericRoute(), listingPage.langeProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.langeProductAttributeTecdocRoute().size();
        listingPage.activeSideFilterLkwCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.langeProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
        close();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks side filter cancelling on LKW routes")
    public void testSideFilterCancellingLKWsearch() throws SQLException {
        openPage("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        String characteristic = listingPage.durchmesserSideFilterButtonFirstValue().text();
        listingPage.durchmesserSideFilterButtonFirstValue().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(characteristic, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
        int numberOfAttributesFilter = listingPage.durchmesserProductAttributeGenericRoute().size();
        listingPage.durchmesserSideFilterButtonFirstValue().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        int numberOfAttributesNoFilter = listingPage.durchmesserProductAttributeTecdocRoute().size();
        Assert.assertNotEquals(numberOfAttributesFilter, numberOfAttributesNoFilter);
        close();
    }
}
