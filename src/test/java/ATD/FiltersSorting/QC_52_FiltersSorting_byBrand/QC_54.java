package ATD.FiltersSorting.QC_52_FiltersSorting_byBrand;


import ATD.Category_car_list_page_Logic;
import ATD.Listing_instruments_page_Logic;
import ATD.Listing_page_Logic;
import ATD.Search_page_Logic;
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

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_54 {

    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private Listing_instruments_page_Logic instrumentsPage = new Listing_instruments_page_Logic();
    private Category_car_list_page_Logic carListPage = new Category_car_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }



    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", ",search19");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination")
    public void checkBrandFilterPagination(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("65");
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }


    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search6");
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand filter with pagination LKW")
    public void checkBrandFilterPaginationLKW(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("65");
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }


    @DataProvider(name = "routesAccessories", parallel = true)
    Object[] dataProviderAccessories() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_accessories3,accessories_listing_criteria");
    }

    @Test(dataProvider = "routesAccessories")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks brand filter with pagination Accessories")
    public void checkBrandFilterPaginationAccessories(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("100285");
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }


    @DataProvider(name = "routesChemical", parallel = true)
    Object[] dataProviderChemical() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals3");
    }

    @Test(dataProvider = "routesChemical")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks brand filter with pagination Chemical")
    public void checkBrandFilterPaginationChemical(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("100292");
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.clickSecondListingPageButton()
                .checkThatListingDisplaysProductOfOneSelectedBrand(brand1, true, listingPageLogic.productTitleInListMode());
    }


    @DataProvider(name = "routesInstruments", parallel = true)
    Object[] dataProviderInstruments() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_instruments6");
    }

    @Test(dataProvider = "routesInstruments")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks brand filter with pagination Instruments")
    public void checkBrandFilterPaginationInstruments(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectVisibleBrandInBlock("4023").selectVisibleBrandInBlock("100333");
        List<String> selectedBrands = instrumentsPage.getTitleOfSelectedBrands();
        new Search_page_Logic().goToNextPage();
        instrumentsPage.checkListingWithSelectedBrands(selectedBrands);
    }


    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks brand filter with pagination Instruments")
    public void checkBrandFilterPaginationCarList(String route) {
        openPage(route);
        carListPage.selectBrandInBlock("442").selectBrandInBlock("11005").selectBrandInBlock("3");
        List<String> selectedBrands = instrumentsPage.getTitleOfSelectedBrands();
        carListPage.checkListingWithVisibleSelectedBrands(selectedBrands);
    }


    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list16,moto_category_car_list_model8");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checks brand filter Moto")
    public void checkBrandFilterMoto(String route) {
        openPage(route);
        new Category_car_list_page_Logic().selectBrandInBlock("67");
        String brand1 = listingPageLogic.getTextOrAttributeFromFilter(listingPageLogic.firstResetBrandBtn(), listingPageLogic.firstBrandNameInFiler(), "alt");
        listingPageLogic.checkProductTitleOnListing(brand1, true, listingPageLogic.productTitleInListMode());
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
