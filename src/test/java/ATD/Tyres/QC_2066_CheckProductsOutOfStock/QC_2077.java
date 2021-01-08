package ATD.Tyres.QC_2066_CheckProductsOutOfStock;

import Common.SetUp;
import ATD.Tyre_form_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_2077 {

    private String email = "QC_2077_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyre_form5,tyres_season13,tyres_brand8,tyres_group_season_brand2,tyres_size9,tyres_dimension9,tyres_season_size13,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test сhecking for a pop-up of success in sending mail for feedback ")
    public void testCheckingForPopUpOfSuccessInSendingMailForFeedback(String route) {
        open(route);

        new Tyre_form_page_Logic().displayingCustomerFeedbackPopUp()
        .displayingOfPopUPAboutSuccessfulSendingLetter(email);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
