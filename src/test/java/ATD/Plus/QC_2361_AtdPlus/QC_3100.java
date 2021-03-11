package ATD.Plus.QC_2361_AtdPlus;

import AWS.OrderAdd_page_aws;
import AWS.Order_aws;
import AWS.ProfilerPage_aws;
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
import static AWS.ProfilerPage_aws.profilerPage_aws;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3100 {


    private String mail = "qc_3100_plusProAutotest@mailinator.com";
    private String idCustomer = "28761050";
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
    @Description(value = "Test Checking the absence of a 20% discount for a user with ATD PRO when ordering FR -> DE from the AWS")
    public void testCheckAbsenceDiscount20PercentForUserWithPlusPROFromAWS(String route) {
        openPage(route);
        new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(idCustomer)
                .chooseSkinInSelector("auto-doc.fr (FR)")
                .selectedPaymentMethod("Vorkasse")
                .selectedDeliveryMethod("Standardversand");
        new OrderAdd_page_aws().addProduct("Z306")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("Z306")
                .clickSaveOrderBtn();
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
                .checkStandardMultiplier("1.02");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
