package ATD.Orders_AWS_Delivery.QC_2505_Delivery;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2506 {

    String mail = "QC_2506_autotest@mailinator.com";
    private Cart_page_Logic cart_page_logic = new Cart_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod","AT,BG,BE,CH,CZ,DE,DK,EE,ES,FI,FR,EN,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "shop")
    @Flaky
    @Description(value = "Free Delivery limits (Positive case)")
    public void testFreeDeliveryLimits_PositiveCase(String shop) throws SQLException {
        openPage(shop);
        String currentShop = getCurrentShopFromJSVarInHTML();
        Float deliveryLimit = new Main_page_Logic().clickVersand().getDeliveryLimitFromText();
        open(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", currentShop, "main", "product46"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndDefaultPostalCode("autotest", "autotest", "autotest", "autotest",
                        currentShop, "autotest", "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(currentShop, currentShop, "autotest")
                .clickOnTheDesiredPaymentMethod(currentShop, "Bank")
                .nextBtnClick()
                .makeAndCheckLimitPriceForFreeDelivery(deliveryLimit)
                .clickBtnReturnToCartPage();
        cart_page_logic.checkPresenceFreeDeliveryIcon();
        cart_page_logic.clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(currentShop, currentShop, "autotest");
        String orderNumber = new CartPayments_page_Logic().clickOnTheDesiredPaymentMethod(currentShop, "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkThatOrderDeliveryIsFree()
                .reSaveOrder()
                .checkThatOrderDeliveryIsFree();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
