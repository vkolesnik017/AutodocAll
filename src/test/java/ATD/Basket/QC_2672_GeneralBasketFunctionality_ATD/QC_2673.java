package ATD.Basket.QC_2672_GeneralBasketFunctionality_ATD;

import ATD.*;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class QC_2673 {


    String mail = "QC_2673_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks basket steps")
    public void testBasketSteps(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .clickBtnReturnToBasket();
        new Cart_page_Logic().nextButtonClick()
                .signIn(mail, password)
                .checkStepsBasketOnPageCartAddress()
                .chooseDeliveryCountryForShipping("DE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickBtnReturnTheAddressPage();
        checkingContainsUrl("/basket/address");
        new CartAddress_page_Logic().nextBtnClick()
                .choosePayPal()
                .nextBtnClick();
        checkingContainsUrl("/basket/alldata");
        new CartAllData_page_Logic().clickBtnReturnToPaymentsPage();
        checkingContainsUrl("/basket/payments");
        new CartPayments_page_Logic().nextBtnClick()
                .clickBtnReturnToCartPage();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}