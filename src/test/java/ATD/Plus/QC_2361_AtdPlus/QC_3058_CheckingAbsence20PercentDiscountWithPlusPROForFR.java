package ATD.Plus.QC_2361_AtdPlus;


import ATD.*;
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
import static AWS.ProfilerPage_aws.profilerPage_aws;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3058_CheckingAbsence20PercentDiscountWithPlusPROForFR {

    private String mail = "qc_3058_plusProAutotest@mailinator.com";
    private DataBase db = new DataBase("ATD");
    private Main_page_Logic mainPageLogic = new Main_page_Logic();
    private Product_page_Logic productPageLogic = new Product_page_Logic();
    private CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();
    private Order_aws orderAws = new Order_aws();
    private String userID = "19507754";


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
    @Description(value = "Test Checking that there is no 20% discount for a user with ATD PRO when ordering FR -> FR and there is no promotion.")
    public void testCheckingAbsence20PercentDiscountForUserWithPlusPRO(String route) throws Exception {
        openPage(route);
        mainPageLogic.loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        new Profile_plus_page_Logic().checkPresenceClientID();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "FR", "main", "product2"));
        float priceProduct = productPageLogic.getProductPrice();
        productPageLogic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("FR", "CreditCard_be2bill")
                .nextBtnClick();
        float priceProductAllData = getPriceFromElement(cartAllDataPageLogic.productPrice());
        Assert.assertEquals(priceProduct, priceProductAllData);
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
                .checkTotalPrice(priceProduct);

    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
