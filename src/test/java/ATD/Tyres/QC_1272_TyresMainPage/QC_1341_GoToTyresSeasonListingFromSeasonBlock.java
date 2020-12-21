package ATD.Tyres.QC_1272_TyresMainPage;


import ATD.Tyres_maker_page_Logic;
import Common.SetUp;
import ATD.TyresListing_page_Logic;
import ATD.Tyres_page_Logic;
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

public class QC_1341_GoToTyresSeasonListingFromSeasonBlock {

    private TyresListing_page_Logic tyresListingPageLogic = new TyresListing_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres,tyres2,tyres3,tyres4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Checks Transition To Tyres Season Listing From Season Block")
    public void testGoToTyresSeasonListingFromSeasonBlock(String route) {
        openPage(route);
        new Tyres_page_Logic().clickSeasonButtonAndCheckTransition()
                              .checkCharacteristicOnListing("Winterreifen", tyresListingPageLogic.seasonCharacteristic())
                              .checkSeasonValueInSelector("Winterreifen");
    }

    @DataProvider(name = "routesMaker", parallel = true)
    Object[] dataProviderMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_maker");
    }

    @Test(dataProvider = "routesMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checks Transition To Tyres Season Listing From Season Block")
    public void testGoToTyresSeasonListingFromSeasonBlockMaker(String route) {
        openPage(route);
        new Tyres_maker_page_Logic().clickSeasonButtonAndCheckTransition(1)
                .checkCharacteristicOnListing("Winterreifen", tyresListingPageLogic.seasonCharacteristic())
                .checkSeasonValueInSelector("Winterreifen");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
