package PKW.Listings.QC_429_Listing_OEN;

import PKW.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_430 {


    private Main_page_Logic mainPageLogic = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new Common.SetUp("PKW").setUpShopsWithMainRoute("prod", "DE", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Chebanenko")
    @Description(value = "Test checks redirect to Oen listing from search input")
    public void testChecksRedirectToOenListingFromSearchInput(String route) {
        openPage(route);
        mainPageLogic.inputTextInSearchBar("1j0615124a")
                .clickTooltipInSearchByExactText("OEN 1J0615124A");
        waitWhileRouteBecomeExpected("oe_number");

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

