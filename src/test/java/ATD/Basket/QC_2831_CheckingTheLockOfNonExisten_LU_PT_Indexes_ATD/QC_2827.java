package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.CartAddress_page_Logic;
import ATD.CartPayments_page_Logic;
import ATD.Product_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2827 {

    private String mail = "QC_2827_autotestATD@mailinator.com";

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "PT","main","product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("PT")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("PT")
                .getZipMasksAndComparesWithExpectedForShipping("1111-111")
                .getZipMasksAndComparesWithExpectedForBilling("1111-111");
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"1111-111"},
                {"5264-345"},
                {"9998-999"},
                {"9989-000"},
                {"1000-000"}
        };
    }

        @Test(dataProvider = "indexes")
        @Flaky
        @Owner(value = "Chelombitko")
        @Description(value = "Test checking the validity of indexes if Billing and Shipping for Portugal are separated. PositiveCase")
        public void testCheckingValidityIndexesIfBillingAndShippingAreSeparated_PT(String indexes) {
            new CartAddress_page_Logic()
                    .fillingPostalCodeFieldJSForShipping(indexes)
                    .fillingPostalCodeFieldJSForBilling(indexes)
                    .nextBtnClick();
            checkingContainsUrl("basket/payments");
            new CartPayments_page_Logic().clickBtnReturnTheAddressPage();
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}
