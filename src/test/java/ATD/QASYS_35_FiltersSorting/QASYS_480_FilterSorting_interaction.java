package ATD.QASYS_35_FiltersSorting;


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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_480_FilterSorting_interaction {
    private Listing_page listingPage = new Listing_page();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search,lkw_category_car_list");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC01 Test checks Hohe and by side filters interaction")
    public void testHoheAndSideFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String hoheValue = listingPage.hoheThirdSideFilterButton().text();
        listingPage.hoheThirdSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC02 Test checks Oberflache and by side filters interaction")
    public void testOberflacheAndSideFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String oberflacheValue = listingPage.oberflacheSideFilterButton().text();
        listingPage.oberflacheSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(oberflacheValue, listingPage.oberflacheProductAttributeGenericRoute(), listingPage.oberflacheProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC03 Test checks Bremsscheibenart and by side filters interaction")
    public void testBremsscheibenartAndSideFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String bremsscheibenartValue = listingPage.bremsscheibenartSideFilterButton().text();
        listingPage.bremsscheibenartSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(bremsscheibenartValue, listingPage.bremsscheibenartProductAttributeGenericRoute(), listingPage.bremsscheibenartProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC04 Test checks Durchmesser and by side filters interaction")
    public void testDurchmesserAndSideFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String durchmesserValue = listingPage.durchmesserSideFilterButton().text();
        listingPage.durchmesserSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(durchmesserValue, listingPage.durchmesserProductAttributeGenericRoute(), listingPage.durchmesserProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC05 Test checks Furprnummer and by side filters interaction")
    public void testFurprnummerAndSideFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String furprnummerValue = listingPage.furprnummerSideFilterButton().text();
        listingPage.furprnummerSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(furprnummerValue, listingPage.furprnummerProductAttributeGenericRoute(), listingPage.furprnummerProductAttributeTecdocRoute());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC06 Test checks side and brand filters interaction")
    public void testSideAndBrandFilterInteraction(String route) {
        open(route);
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC07 Test checks Hohe and Lochanzahl filters interaction")
    public void testHoheAndLochanzahlFilterInteraction(String route) {
        open(route);
        String hoheValue = listingPage.hoheThirdSideFilterButton().text();
        listingPage.hoheThirdSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String lochanzahlValue = listingPage.lochanzahlSideFilterButton().text();
        listingPage.lochanzahlSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(lochanzahlValue, listingPage.lochanzahlProductAttributeGenericRoute(), listingPage.lochanzahlProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC08 Test checks side, brand and Hohe filters interaction LKW route search")
    public void testSideAndBrandAndHoheFilterInteractionLKWsearch() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_search"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String hoheValue = listingPage.hoheFirstSideFilterButton().text();
        listingPage.hoheFirstSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC08 Test checks side, brand and Hohe filters interaction LKW route model")
    public void testSideAndBrandAndHoheFilterInteractionLKWmodel() throws SQLException {
        open("https://lkwteile.autodoc.de/" + dataBase.getRouteByRouteName("DE", "lkw_category_car_list2"));
        listingPage.filterBySideBack().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String hoheValue = listingPage.hoheThirdSideFilterButton().text();
        listingPage.hoheThirdSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String brandName = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter("Hinterachse", listingPage.einbauseiteProductAttributeGenericRoute(), listingPage.einbauseiteProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC09 Test checks brand and mark filters interaction Oem listing")
    public void testBrandAndMarkFilterInteractionOem() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen3"));
        closeCookiesFooterMessage();
        String carBrandName = listingPage.carBrandFilterOem().attr("data-value");
        String brandName = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.carBrandFilterOem().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.getBrandFromTitle(brandName, 4, true, listingPage.productTitleInListMode());
        listingPage.checkProductCharacteristicOnListingOem(carBrandName, listingPage.carBrandApplicabilityAttribute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "TC10 Test checks brand and  filters interaction instruments listing")
    public void testBrandAndMarkFilterInteraction() throws SQLException {
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_instruments"));
        String brandName = listingPage.firstBrandNameInFiler().attr("alt");
        String genericName = listingPage.firstGeneric().text();
        listingPage.firstGeneric().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandInFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.priceFilterSubmitButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductTitleOnListing(brandName, true, listingPage.productTitleInListMode());
        listingPage.checkProductTitleOnListing(genericName, true, listingPage.productTitleInListMode());
    }
}
