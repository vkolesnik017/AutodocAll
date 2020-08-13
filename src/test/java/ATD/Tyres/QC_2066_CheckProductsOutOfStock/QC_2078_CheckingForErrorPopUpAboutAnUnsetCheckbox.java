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
import static com.codeborne.selenide.Selenide.*;

public class QC_2078_CheckingForErrorPopUpAboutAnUnsetCheckbox {
    private String email = "QC_2078_autotest@mailinator.com";

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
    @Description(value = "Test checking for an error popup about an unset checkbox when trying to send mail for feedback")
    public void testCheckingForErrorPopUpAboutAnUnsetCheckbox(String route) {
        open(route);

        new Tyre_form_page_Logic().displayingCustomerFeedbackPopUp()
                .displayingOfPopUPAboutUnsetCheckBox(email);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
