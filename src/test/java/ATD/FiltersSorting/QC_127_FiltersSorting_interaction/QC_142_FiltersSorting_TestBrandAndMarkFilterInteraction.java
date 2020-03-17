package ATD.FiltersSorting.QC_127_FiltersSorting_interaction;


import ATD.DataBase;
import ATD.Listing_page;
import ATD.Listing_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.close;

public class QC_142_FiltersSorting_TestBrandAndMarkFilterInteraction {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks brand and mark filters interaction Oem listing")
    public void testBrandAndMarkFilterInteractionOem() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_oen3"));
        closeCookiesFooterMessage();
        String carBrandName = listingPage.carBrandFilterOem().attr("data-value");
        String brandName = listingPage.firstBrandNameOemListing().attr("alt");
        listingPage.carBrandFilterOem().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.firstBrandButtonOemListing().click();
        listingPage.preloader().shouldBe(attribute("style", "display: none;"));
        listingPage.getBrandFromTitle(brandName, 4, true, listingPage.productTitleInListMode());
        listingPage.checkProductCharacteristicOnListingOem(carBrandName, listingPage.carBrandApplicabilityAttribute());
        close();
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
