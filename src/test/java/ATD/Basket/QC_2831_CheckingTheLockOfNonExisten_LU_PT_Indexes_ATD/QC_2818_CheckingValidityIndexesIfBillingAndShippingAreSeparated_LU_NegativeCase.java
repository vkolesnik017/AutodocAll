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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2818_CheckingValidityIndexesIfBillingAndShippingAreSeparated_LU_NegativeCase {

    private String mail = "QC_2818_autotestATD@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "LD", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("LD")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("LD")
                .getZipMasksAndComparesWithExpectedForShipping("1111")
                .getZipMasksAndComparesWithExpectedForBilling("1111");
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"0001"},
                {"0550"},
                {"0999"},
                {"0998"},
                {"0000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the validity of indexes if Billing and Shipping for Luxembourg are separated. PositiveCase")
    public void testCheckingValidityIndexesIfBillingAndShippingAreSeparated_LU(String indexes) {
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