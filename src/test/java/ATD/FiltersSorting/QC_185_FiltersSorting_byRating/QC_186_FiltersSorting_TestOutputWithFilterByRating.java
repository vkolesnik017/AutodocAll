package ATD.FiltersSorting.QC_185_FiltersSorting_byRating;


import ATD.DataBase;
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
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.refresh;

public class QC_186_FiltersSorting_TestOutputWithFilterByRating {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search15");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search4,lkw_category_car_list8");
    }


    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks output with filter by rating")
    public void testOutputWithFilterByRating(String route) {
        openPage(route);
        listingPage.ratingThreeStarsFilterCheckbox().click();
        listingPage.checkQuantityOfRatingStarsOnListing(3, listingPage.ratingInProductBlock());
        listingPage.ratingFiveStarsFilterCheckbox().click();
        listingPage.checkQuantityOfRatingStarsOnListing(5, listingPage.ratingInProductBlock());
        refresh();
        listingPage.checkQuantityOfRatingStarsOnListing(5, listingPage.ratingInProductBlock());
        listingPage.ratingFiveStarsFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueRatingOnListing(2);
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks output with filter by rating LKW")
    public void testOutputWithFilterByRatingLKW(String route) {
        openPage(route);
        listingPage.ratingThreeStarsFilterCheckbox().click();
        listingPage.checkQuantityOfRatingStarsOnListing(3, listingPage.ratingInProductBlock());
        listingPage.ratingFiveStarsFilterCheckbox().click();
        listingPage.checkQuantityOfRatingStarsOnListing(5, listingPage.ratingInProductBlock());
        refresh();
        listingPage.checkQuantityOfRatingStarsOnListing(5, listingPage.ratingInProductBlock());
        listingPage.ratingFiveStarsFilterCheckbox().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkUniqueRatingOnListing(2);
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks that rating filter is not present on search route")
    public void testRatingFilterPresenceOnSearchRoute() throws SQLException {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "search6"));
        listingPage.ratingFilterBlock().shouldNotBe(visible);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
