package ATD.Orders_AWS_Delivery.QC_2505_Delivery;

import ATD.CartAddress_page_Logic;
import ATD.CartAllData_page_Logic;
import ATD.Product_page_Logic;
import AWS.Delivery_prices_aws;
import AWS.Order_aws;
import Common.DataBase;;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_2508 {

    private String mail = "QC_2508_autotest@mailinator.com";
    private CartAllData_page_Logic cartAllData_page_logic = new CartAllData_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "country", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Object[][]{
                {"AD"}, {"AR"}, {"AU"}, {"AT"}, {"BE"}, {"BA"}, {"BR"}, {"CA"}, {"CL"}, {"CO"}, {"HR"}, {"CY"}, {"CZ"}, {"DK"},
                {"DO"}, {"EC"}, {"EG"}, {"EE"}, {"FI"}, {"DE"}, {"GR"}, {"NL"}, {"HU"}, {"IS"}, {"IN"}, {"IE"}, {"IL"}, {"UY"},
                {"IT"}, {"KE"}, {"LV"}, {"LI"}, {"LT"}, {"LU"}, {"MK"}, {"MT"}, {"MX"}, {"MD"}, {"MC"}, {"MZ"}, {"NZ"}, {"NO"},
                {"PA"}, {"PL"}, {"PT"}, {"PR"}, {"RO"}, {"SM"}, {"RS"}, {"SK"}, {"SI"}, {"ZA"}, {"ES"}, {"SE"}, {"CH"}, {"TR"},
                {"GB"}, {"US"}, {"ZM"}
        };
    }

    @Test(dataProvider = "country")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks delivery cost from front")
    public void testCheckDeliveryCost_Front(String country) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        String nameCountry = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeOrDefaultFieldJSForShipping("default", country)
                .chooseDeliveryCountryAndGetNameCountry(country);
        new CartAddress_page_Logic().nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick();

        executeJavaScript("window.open('about:blank', '-blank')");
        switchTo().window(1);
        float deliveryPrice = new Delivery_prices_aws().openAndLoginDeliveryPriceAwsPage()
                .getDeliveryPriceWithTranslationCountries(nameCountry);
        switchTo().window(0);

        float deliveryPriceAllData = cartAllData_page_logic.getRegularDeliveryPrice();
        Assert.assertEquals(deliveryPrice, deliveryPriceAllData);
        String orderNumber = cartAllData_page_logic.nextBtnClick()
                .getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        float deliveryPriceInOrder = order_aws.openOrderInAwsWithoutLogin()
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryPrice, deliveryPriceInOrder);
        float deliveryPriceInOrderAfterReset = order_aws.reSaveOrder()
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryPrice, deliveryPriceInOrderAfterReset);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
