package ATD.Plus.QC_2361_AtdPlus;

import ATD.*;
import AWS.Delivery_prices_aws;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import com.codeborne.selenide.Condition;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_2912 {

    private Float  deliveryPrice, allDataDeliveryPrice;
    private DataBase db = new DataBase("ATD");
    private String mail = "qc_2912_plusProAutotest@mailinator.com";
    private final CartAllData_page_Logic cartAllDataPageLogic = new CartAllData_page_Logic();
    private final Delivery_prices_aws deliveryPricesAws = new Delivery_prices_aws();
    private final Order_aws orderAws = new Order_aws();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DK");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the absence of a discount on delivery to the island for a user with the ATD + PRO package")
    public void testCheckingAbsenceDiscountOnDeliveryToIslandWithPlusPRO(String route) throws Exception {
        openPage(route);
        new Main_page_Logic().loginFromHeader(mail);
        checkingContainsUrl("profile/orders");
        deliveryPricesAws.openAndLoginDeliveryPriceAwsPage();
        deliveryPrice = deliveryPricesAws.getDeliveryPriceForIslandOrRegion("Bornholm");
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DK", "main", "product2"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DK", "Bank")
                .nextBtnClick();
        cartAllDataPageLogic.clickSafeOrderCheckbox()
                .checkThatSafeOrderCheckboxIsNotSelected();
        cartAllDataPageLogic.securityDeliveryPriceSO().waitUntil(Condition.not(Condition.visible), 5000);
        allDataDeliveryPrice = cartAllDataPageLogic.getRegularDeliveryPrice();
        Assert.assertEquals(deliveryPrice, allDataDeliveryPrice);
        cartAllDataPageLogic.nextBtnClick();
        String orderNum = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNum).openOrderInAwsWithoutLogin();
        float deliveryCostInOrder = orderAws.getDeliveryCostInOrder();
        float deliveryCostFromDeliveryBlock = orderAws.getDeliveryCostInOrderFromDeliveryBlock();
        Assert.assertEquals(deliveryPrice, deliveryCostInOrder);
        Assert.assertEquals(deliveryPrice, deliveryCostFromDeliveryBlock);
        orderAws.reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
