package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2817 {

    private String mail = "QC_2817_autotestATD@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "PT", "main", "product32"));
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
                {"0001-000"},
                {"0111-111"},
                {"0550-505"},
                {"0999-999"},
                {"0999-000"},
                {"0998-111"},
                {"0000-000"},
                {"9999-001"},
                {"9999-550"},
                {"9999-999"},
                {"9999-998"},
                {"9999-000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the validity of indexes if Billing and Shipping for Portugal are separated. PositiveCase")
    public void testCheckingValidityIndexesIfBillingAndShippingAreSeparated_PT(String indexes) {
        cartAddress_page_logic.fillingPostalCodeFieldJSForShipping(indexes)
                .fillingPostalCodeFieldJSForBilling(indexes)
                .nextBtnClick();
        cartAddress_page_logic.checkPresenceElement(cartAddress_page_logic.errorTooltipForShipping())
                .checkPresenceElement(cartAddress_page_logic.errorTooltipForBilling());
        checkingContainsUrl("basket/address");
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}
