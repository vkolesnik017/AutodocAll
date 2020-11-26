package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2829_CheckingDisplayOfCorrectMessageInTooltipAfterEnteringNegativeIndexForLU {

    private String mail = "QC_2829_autotestATD@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "LD", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking the display of the correct message in the tooltip after entering the prohibited index for Luxembourg")
    public void testCheckDisplayOfCorrectMessageInTooltipAfterEnteringNegativeIndex_LU(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("LU")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("LU")
                .getZipMasksAndComparesWithExpectedForShipping("1111")
                .getZipMasksAndComparesWithExpectedForBilling("1111")
                .fillingPostalCodeFieldJSForShipping("0000")
                .fillingPostalCodeFieldJSForBilling("0000")
                .nextBtnClick();
        cartAddress_page_logic.checkCorrectTextInErrorInErrorTooltipForPostalCod(cartAddress_page_logic.errorTooltipForShipping(),
                "Diese Postleitzahl existiert nicht. Bitte geben Sie eine gültige Postleitzahl ein.");
        cartAddress_page_logic.checkCorrectTextInErrorInErrorTooltipForPostalCod(cartAddress_page_logic.errorTooltipForBilling(),
                "Diese Postleitzahl existiert nicht. Bitte geben Sie eine gültige Postleitzahl ein.");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
