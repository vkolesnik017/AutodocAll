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
import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2923 {


    private Float  deliveryPrice, allDataDeliveryPrice;
    private DataBase db = new DataBase("ATD");
    private String mail = "qc_2923_plusProAutotest@mailinator.com";
    private CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();
    private Main_page_Logic mainPageLogic = new Main_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking Absence the discount on delivery to not EU countries for the user with the ATD + PRO package")
    public void testCheckingAbsenceDiscountOnDeliveryToNotEUCountriesWithPlusPRO(String route) throws Exception {
        openPage(route);
        mainPageLogic.loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        new Profile_plus_page_Logic().checkPresenceClientID();
        deliveryPrice = new Versand_static_page_Logic().getDeliveryPriceForAWS("Schweiz");
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
        Assert.assertEquals(deliveryPrice, allDataDeliveryPrice);
        cartAllDataPageLogic.nextBtnClick();
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNum).openOrderInAwsWithLogin()
                .checkSumDeliveryInOrder(deliveryPrice)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
