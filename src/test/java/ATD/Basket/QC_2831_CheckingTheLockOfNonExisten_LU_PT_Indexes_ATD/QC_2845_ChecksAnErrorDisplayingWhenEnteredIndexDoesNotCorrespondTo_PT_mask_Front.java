package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2845_ChecksAnErrorDisplayingWhenEnteredIndexDoesNotCorrespondTo_PT_mask_Front {

    private String mail = "QC_2845_autotestATD@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks an error displaying when entered index does not correspond to Portugal mask")

    public void testChecksAnErrorDisplayingWhenEnteredIndexDoesNotCorrespondTo_PT_mask() throws SQLException {
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
                .getZipMasksAndComparesWithExpectedForBilling("1111-111")
                .fillingPostalCodeFieldJSForShipping("1111-1")
                .fillingPostalCodeFieldJSForBilling("1111-1")
                .nextBtnClick();
        cartAddress_page_logic.checkPresenceElement(cartAddress_page_logic.errorTooltipForShipping())
                .checkPresenceElement(cartAddress_page_logic.errorTooltipForBilling());
        checkingContainsUrl("basket/address");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
