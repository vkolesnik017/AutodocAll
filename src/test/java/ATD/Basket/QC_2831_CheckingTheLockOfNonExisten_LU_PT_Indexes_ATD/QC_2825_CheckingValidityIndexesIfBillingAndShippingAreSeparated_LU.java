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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2825_CheckingValidityIndexesIfBillingAndShippingAreSeparated_LU {

    private String mail = "QC_2825_autotestATD@mailinator.com";

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "LD","main","product41"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("LU")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("LU")
                .getZipMasksAndComparesWithExpectedForShipping("1111")
                .getZipMasksAndComparesWithExpectedForBilling("1111");
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"1001"},
                {"9999"},
                {"5238"},
                {"9998"},
                {"1111"},
                {"1000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the validity of indices if Billing and Shipping for Portugal are separated")
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
