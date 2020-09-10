package ATD.Listings.QC_445_ListingTecDoc;


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

public class QC_1741_CharacteristicPopupIsNotDisplayedIfCarRatingLess100 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list23");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Characteristic Popup Is Not Displayed If Car Rating Less 100")
    public void testCharacteristicPopupIsNotDisplayedIfCarRatingLess100(String route) {
        openPage(route);
        new Listing_page_Logic().checkInfoPopupInvisibility();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
