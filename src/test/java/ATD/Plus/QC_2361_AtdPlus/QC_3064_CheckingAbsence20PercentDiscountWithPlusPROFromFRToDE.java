package ATD.Plus.QC_2361_AtdPlus;

import ATD.CartAllData_page_Logic;
import ATD.Cart_page_Logic;
import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import AWS.Customer_view_aws;
import AWS.Order_aws;
import AWS.ProfilerPage_aws;
import Common.DataBase;
import Common.Merchant_page;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ATD.CommonMethods.*;
import static ATD.CommonMethods.openPage;
import static AWS.ProfilerPage_aws.profilerPage_aws;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3064_CheckingAbsence20PercentDiscountWithPlusPROFromFRToDE {

    private String mail = "qc_3064_plusProAutotest@mailinator.com";
    private DataBase db = new DataBase("ATD");
    private Main_page_Logic mainPageLogic = new Main_page_Logic();
    private Product_page_Logic productPageLogic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();
    private Order_aws orderAws = new Order_aws();
    private String userID = "19548493";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "FR");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking that there is no 20% discount for a user with ATD PRO when ordering FR -> DE and there is no promotion.")
    public void testCheckAbsence20PercentDiscountWithPlusPROFromFRToDE(String route) throws Exception {
        openPage(route);
        mainPageLogic.loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "FR", "main", "product2"));
        productPageLogic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("FR", "CreditCard_be2bill")
                .nextBtnClick();
        float priceProductAllData = getPriceFromElement(cartAllDataPageLogic.productPrice());
        cartAllDataPageLogic.nextBtnClick();
        new Merchant_page().clickByLogoAutodoc();
        checkingContainsUrl("fr");
        mainPageLogic.cartClick();
        new Cart_page_Logic().deleteGoodFromCartPage();
        new Customer_view_aws().openCustomerPersonalArea(userID)
                .checkPresenceOrderHistoryBlock()
                .checkAndOpenOrderWithExpectedData()
                .checkCurrentStatusInOrder("Abgebrochene");
        String sumDiscount = orderAws.getSumDiscountMoney();
        String percentDiscount = orderAws.getDiscountInPercent();
        Assert.assertEquals(sumDiscount, "0");
        Assert.assertEquals(percentDiscount, "0");
        String articleId = orderAws.getArticleId();
        String orderId = orderAws.getOrderIdOfOrder();
        orderAws.reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        openPage(profilerPage_aws);
        new ProfilerPage_aws().fillingFieldsOrderIdAndArticleId(orderId, articleId)
                .checkStandardMultiplier("1.02")
                .checkTotalPrice(priceProductAllData);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
