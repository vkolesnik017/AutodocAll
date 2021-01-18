package ATD.Section_Tyres.QC_2066_CheckProductsOutOfStock;

import Common.SetUp;
import ATD.Tyre_form_page_Logic;
import ATD.Tyres_dimension_page_Logic;
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

public class QC_2080 {
    private String invalidEmail = "QC_2080_autotest@";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyre_form5,tyres_season13,tyres_brand8,tyres_group_season_brand2,tyres_size9"); //,
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking for an error popup about invalid email and an unset checkbox when trying to send mail for feedback")
    public void testCheckingForPopUpAboutInvalidEmailAndWithCheckbox(String route) {
        open(route);

        new Tyre_form_page_Logic().displayingCustomerFeedbackPopUp()
                .displayingOfPopUPAboutInvalidEmailAndWithCheckbox(invalidEmail);
    }


    @DataProvider(name = "routesTyres", parallel = true)
    Object[] dataProviderTyres() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "tyres_dimension9,tyres_season_size13,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");
    }

    @Test(dataProvider = "routesTyres")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking for an error popup about invalid email and an unset checkbox when trying to send mail for feedback")
    public void testCheckingForPopUpAboutInvalidEmailAndWithCheckboxTyres(String route) {
        open(route);

        new Tyres_dimension_page_Logic()
                .displayingCustomerFeedbackPopUp()
                .displayingOfPopUPAboutInvalidEmailAndWithCheckbox(invalidEmail);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
