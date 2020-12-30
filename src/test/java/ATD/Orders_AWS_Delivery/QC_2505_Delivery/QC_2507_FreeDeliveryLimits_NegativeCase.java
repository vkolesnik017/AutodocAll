package ATD.Orders_AWS_Delivery.QC_2505_Delivery;

import ATD.CartAllData_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
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

public class QC_2507_FreeDeliveryLimits_NegativeCase {

    String mail = "QC_2507_autotest@mailinator.com";

    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "AT");
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "shop")
    @Flaky
    @Description(value = "Free Delivery limits (Negative case)")
    public void testFreeDeliveryLimits_NegativeCase(String shop) throws SQLException {
        openPage(shop);
        String currentShop = getCurrentShopFromJSVarInHTML();
        Float deliveryLimit = new Main_page_Logic().clickVersand().getDeliveryLimitFromText();
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "AT", "main", "product44"));
        float deliveryPrice = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("DE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(currentShop, "Bank")
                .nextBtnClick()
                .checkNoFreeDelivery(deliveryLimit)
                .getRegularDeliveryPrice();
        float safeOrderPrice = cartAllData_page_logic.getSafeOrderPrice();
        float totalDeliveryPriceAndSafeOrder = cartAllData_page_logic.getTotalDeliveryPriceAndSafeOrder(deliveryPrice, safeOrderPrice);
        String orderNumber = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkSumDeliveryInOrder(totalDeliveryPriceAndSafeOrder)
                .reSaveOrder()
                .checkSumDeliveryInOrder(totalDeliveryPriceAndSafeOrder);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
