package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import Common.DataBase;
import ATD.Listing_page_Logic;
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

public class QC_140_FiltersSorting_TestHoheAndLochanzahlFilterInteraction {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private DataBase dataBase = new DataBase("ATD");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Hohe and Lochanzahl filters interaction")
    public void testHoheAndLochanzahlFilterInteraction(String route) throws Exception {
        openPage(route);
        String hoheValue = listingPageLogic.getTextFromElement(listingPageLogic.hoheThirdSideFilterButton());
        listingPageLogic.hoverOnSideFilterAndClick(listingPageLogic.hoheThirdSideFilterButton())
                .waitUntilPreloaderDisappear();
        String lochanzahlValue = listingPageLogic.getTextFromElement(listingPageLogic.lochanzahlSideFilterButton());
        listingPageLogic.hoverOnSideFilterAndClick(listingPageLogic.lochanzahlSideFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPageLogic.hoheProductAttributeGenericRoute(), listingPageLogic.hoheProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(lochanzahlValue, listingPageLogic.lochanzahlProductAttributeGenericRoute(), listingPageLogic.lochanzahlProductAttributeTecdocRoute());
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks Hohe and Lochanzahl filters interaction search route")
    public void testHoheAndLochanzahlFilterInteractionSearchRoute() throws Exception {
        openPage("https://autodoc.de/" + dataBase.getRouteByRouteName("DE", "search16"));
        String hoheValue = listingPageLogic.getTextFromElement(listingPageLogic.hoheFirstSideFilterButton());
        listingPageLogic.hoverOnSideFilterAndClickSearchRouteHohe(listingPageLogic.hoheFirstSideFilterButton())
                .waitUntilPreloaderDisappear();
        String lochanzahlValue = listingPageLogic.getTextFromElement(listingPageLogic.lochanzahlSideFilterButton());
        listingPageLogic.hoverOnSideFilterAndClickSearchRouteHohe(listingPageLogic.lochanzahlSideFilterButton())
                .waitUntilPreloaderDisappear()
                .checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPageLogic.hoheProductAttributeGenericRoute(), listingPageLogic.hoheProductAttributeTecdocRoute())
                .checkProductAttributeOnListingWithCarAndFilter(lochanzahlValue, listingPageLogic.lochanzahlProductAttributeGenericRoute(), listingPageLogic.lochanzahlProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
