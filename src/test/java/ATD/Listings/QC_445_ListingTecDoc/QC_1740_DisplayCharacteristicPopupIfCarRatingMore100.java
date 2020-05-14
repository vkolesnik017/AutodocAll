package ATD.Listings.QC_445_ListingTecDoc;


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

public class QC_1740_DisplayCharacteristicPopupIfCarRatingMore100 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "EN", "main", "category_car_list22");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Display Characteristic Popup If Car Rating More 100")
    public void testDisplayCharacteristicPopupIfCarRatingMore100(String route) {
        openPage(route);
        new Listing_page_Logic().clickInfoButton()
                                .compareCharacteristicsPopup();
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
