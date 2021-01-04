package ATD.Plus.QC_2361_AtdPlus;

import ATD.Versand_static_page_Logic;
import AWS.OrderAdd_page_aws;
import AWS.Order_aws;
import AWS.SearchOrders_page_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.roundOfTheCost;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3074_CheckDiscount35PercentOnSOWithOptimumMVP2FromAWS {

    private Float safeOrderPrice, addSafeOrderPrice, orderAwsSafeOrderPrice;
    private String mail = "qc_3074_plusOptimalAutotest@mailinator.com";
    private String idCustomer = "19753807";


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
    @Description(value = "Test Checking the 35% discount on SO for a user with the Optimal MVP2 package when ordering from the AWS")
    public void testCheckDiscount35PercentOnSOWithOptimalMVP2FromAWS(String route) throws Exception {
        openPage(route);
        safeOrderPrice = new Versand_static_page_Logic().getSafeOrderPriceWithAnyDiscountAndSubscription(mail);
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(idCustomer)
                .chooseSkinInSelector("autodoc.de (DE)")
                .selectedPaymentMethod("Vorkasse")
                .selectedDeliveryMethod("Standardversand")
                .selectedStatusInSafeOrder("Включен");
        addSafeOrderPrice = new OrderAdd_page_aws().getSafeOrderCost();
        float roundSafeOrderPrice = roundOfTheCost(safeOrderPrice, addSafeOrderPrice);
        Assert.assertEquals(roundSafeOrderPrice, addSafeOrderPrice);
        new OrderAdd_page_aws().addProduct("Z306")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("Z306")
                .clickSaveOrderBtn();
        orderAwsSafeOrderPrice = new Order_aws().getSafeOrderPrice();
        Assert.assertEquals(roundSafeOrderPrice, orderAwsSafeOrderPrice);
        new Order_aws().reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
