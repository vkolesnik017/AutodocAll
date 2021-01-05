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

public class QC_3072 {

    private Float safeOrderPrice, allDataSafeOrderPriceFromBlock, allDataSafeOrderPriceFromSummary;
    private DataBase db = new DataBase("ATD");
    private String mail = "qc_3072_plusProAutotest@mailinator.com";
    private CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();
    private Order_aws orderAws = new Order_aws();


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
    @Description(value = "Test Checking the 50% discount on SO for a user with the Pro MVP2 package when ordering from the front")
    public void testCheckDiscount50PercentOnSOWithProMVP2OrderFront(String route) throws Exception {
        openPage(route);
        new Main_page_Logic().loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        new Profile_plus_page_Logic().checkPresenceClientID();
        safeOrderPrice = new Versand_static_page_Logic().getSafeOrderPriceWithAnyDiscountAndSubscription(mail);
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product2"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick();
        cartAllDataPageLogic.clickSafeOrderCheckbox();
        allDataSafeOrderPriceFromBlock = cartAllDataPageLogic.getSafeOrderPriceFromSOBlock();
        allDataSafeOrderPriceFromSummary = cartAllDataPageLogic.getSafeOrderPrice();
        float roundSafeOrderPrice = roundOfTheCost(safeOrderPrice, allDataSafeOrderPriceFromBlock);
        Assert.assertEquals(roundSafeOrderPrice, allDataSafeOrderPriceFromBlock);
        Assert.assertEquals(roundSafeOrderPrice, allDataSafeOrderPriceFromSummary);
        cartAllDataPageLogic.nextBtnClick();
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNum).openOrderInAwsWithLogin();
        float awsSafeOrderPrice = orderAws.getSafeOrderPrice();
        Assert.assertEquals(roundSafeOrderPrice, awsSafeOrderPrice);
        orderAws.reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
