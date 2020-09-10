package ATD.FiltersSorting.QC_404_FiltersSorting_byPrice;


import Common.DataBase;
import ATD.Listing_page_Logic;
import ATD.Main_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.waitingWhileLinkBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class QC_419_FiltersSorting_TestPriceFilterResetACC {
    private Listing_page_Logic listingPage = new Listing_page_Logic();
    private DataBase dataBase = new DataBase();
    private Main_page mainPage = new Main_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks price filter reset on listing ACC")
    public void checkPriceFilterResetAcc() throws SQLException {
        openPage("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "category_car_list"));
        open("https://autodoc.de/" +  dataBase.getRouteByRouteName("DE", "listing_accessories2"));
        String defaultMinPriceAcc = listingPage.minPriceMapping().getText();
        String defaultMaxPriceAcc = listingPage.maxPriceMapping().getText();
        executeJavaScript("arguments[0].value='20';", listingPage.minPriceValue());
        executeJavaScript("arguments[0].value='30';", listingPage.maxPriceValue());
        listingPage.priceFilterSubmitButton().click();
        listingPage.minPriceMapping().shouldHave(text("20"));
        listingPage.maxPriceMapping().shouldHave(text("30"));
        listingPage.checkPricesRange(16.00f, 33.00f, listingPage.priceOfAllProductsOnPageInList());
        listingPage.resetPriceFilerButton().click();
        listingPage.minPriceMapping().shouldHave(text(defaultMinPriceAcc));
        listingPage.maxPriceMapping().shouldHave(text(defaultMaxPriceAcc));
        float minFloatAcc = Float.parseFloat(defaultMinPriceAcc);
        float maxFloatAcc = Float.parseFloat(defaultMaxPriceAcc);
        listingPage.checkPricesRange(minFloatAcc, maxFloatAcc, listingPage.priceOfAllProductsOnPageInTile());
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/autozubehoer/handyhalterung?");
    }
    
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
