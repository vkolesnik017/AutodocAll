package ATD.Tyres.QC_2066_CheckProductsOutOfStock;

import ATD.SetUp;
import ATD.Tyre_form_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_2079_CheckingForPopUpErrorAboutInvalidEmail {
    private String invalidEmail = "QC_2079_autotest@";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main", "tyre_form5,tyres_season13,tyres_brand8,tyres_group_season_brand2,tyres_size9,tyres_dimension9,tyres_season_size13,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking for a pop-up error about invalid email when trying to send mail for feedback")
    public void testCheckingForPopUpErrorAboutInvalidEmail(String route) {
        open(route);

        new Tyre_form_page_Logic().displayingCustomerFeedbackPopUp()
                .displayingOfPopUPAboutInvalidEmail(invalidEmail);

    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
