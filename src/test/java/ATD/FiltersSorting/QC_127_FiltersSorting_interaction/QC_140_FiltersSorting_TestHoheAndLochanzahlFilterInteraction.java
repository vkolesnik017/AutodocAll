package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


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

public class QC_140_FiltersSorting_TestHoheAndLochanzahlFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
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
    public void testHoheAndLochanzahlFilterInteraction(String route) {
        openPage(route);
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
    @Description(value = "Test checks Hohe and Lochanzahl filters interaction searchroute")
    public void testHoheAndLochanzahlFilterInteractionSearchRoute() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "search16"));
        String hoheValue = listingPage.hoheFirstSideFilterButton().text();
        listingPage.hoheFirstSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        String lochanzahlValue = listingPage.lochanzahlSideFilterButton().text();
        listingPage.lochanzahlSideFilterButton().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.checkProductAttributeOnListingWithCarAndFilter(hoheValue, listingPage.hoheProductAttributeGenericRoute(), listingPage.hoheProductAttributeTecdocRoute());
        listingPage.checkProductAttributeOnListingWithCarAndFilter(lochanzahlValue, listingPage.lochanzahlProductAttributeGenericRoute(), listingPage.lochanzahlProductAttributeTecdocRoute());
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
