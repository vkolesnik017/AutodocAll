package ATD.Plus.QC_2361_AtdPlus;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.roundOfTheCost;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2910_CheckingDiscountOnDeliveryForUserWithAtdProPackage {

    private Float  deliveryPrice, allDataDeliveryPrice;
    private DataBase db = new DataBase("ATD");
    private String mail = "qc_2910_plusProautotest@mailinator.com";
    private CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the discount on delivery to the country of the shop for the user with the ATD + PRO package")
    public void testCheckingDiscountOnDeliveryForUserWithAtdProPackage(String route) throws Exception {
        openPage(route);
        new Main_page_Logic().loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPriceForCurrentCountryForUserWithSubscriptionPlusPro("DE", mail);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick();
        allDataDeliveryPrice = cartAllDataPageLogic.getRegularDeliveryPrice();
        float roundDeliveryPrice = roundOfTheCost(deliveryPrice,allDataDeliveryPrice );
        Assert.assertEquals(roundDeliveryPrice, allDataDeliveryPrice);
        cartAllDataPageLogic.nextBtnClick();
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNum).openOrderInAwsWithLogin()
                .checkSumDeliveryInOrder(roundDeliveryPrice)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
