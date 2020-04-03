package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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
import static com.codeborne.selenide.Selenide.close;

public class QC_140_FiltersSorting_TestHoheAndLochanzahlFilterInteraction {
    private Listing_page_Logic listingPageLogic = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list,search2");
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
    public void tearDown() {
        close();
    }
}
