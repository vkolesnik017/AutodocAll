package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;

import ATD.LKW_Search_page_Logic;
import ATD.Search_page_Logic;
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
import java.util.List;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2663_InteractionOfGenericAndBrandFilters {
    private Search_page_Logic searchPage = new Search_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search43");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks interaction of generic and brand filters")
    public void testChecksInteractionOfGenericAndBrandFilters(String route) throws SQLException {
        openPage(route);
        searchPage.presenceOfTecDocListing().selectGeneric("_2");
        checkingContainsUrl("sort_categories%5B0%5D=2");
        searchPage.checkListingWithSelectedGeneric("Starter").selectBrandWithTitle("21");
        checkingContainsUrl("&supplier%5B1%5D=21");
        searchPage.checkListingWithSelectedBrands("21").checkTitleOfProductsWithGeneric("Starter").clickRestoringDefaultValues();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "search43"));
        searchPage.selectGeneric("_4");
        checkingContainsUrl("&sort_categories%5B0%5D=4");
        searchPage.checkTitleOfProductsWithGeneric("Generator").selectGeneric("-all");
        List<String> generics = searchPage.getTitleOfAllGeneric();
        searchPage.presenceAllGenericsInListing(generics);
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search16");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks interaction of generic and brand filters")
    public void testChecksInteractionOfGenericAndBrandFiltersLKW(String route) throws SQLException {
        openPage(route);
        searchPage.presenceOfTecDocListing().selectGeneric("_2");
        checkingContainsUrl("sort_categories%5B0%5D=2");
        searchPage.checkListingWithSelectedGeneric("Starter");
        new LKW_Search_page_Logic().selectBrandWithId("21");
        checkingContainsUrl("&supplier%5B1%5D=21");
        searchPage.checkListingWithSelectedBrands("21").checkTitleOfProductsWithGeneric("Starter").clickRestoringDefaultValues();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "lkw_search16"));
        searchPage.selectGeneric("_4");
        checkingContainsUrl("&sort_categories%5B0%5D=4");
        searchPage.checkTitleOfProductsWithGeneric("Generator").selectGeneric("-all");
        List<String> generics = searchPage.getTitleOfAllGeneric();
        searchPage.presenceAllGenericsInListing(generics);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
