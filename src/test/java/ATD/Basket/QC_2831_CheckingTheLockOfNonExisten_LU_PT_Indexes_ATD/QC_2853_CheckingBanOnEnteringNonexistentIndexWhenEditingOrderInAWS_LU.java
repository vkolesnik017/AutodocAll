package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.Payment_handler_page_Logic;
import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2853_CheckingBanOnEnteringNonexistentIndexWhenEditingOrderInAWS_LU {

    private String mail = "QC_2853_autotestATD@mailinator.com";
    private Order_aws order_aws = new Order_aws();

    private String orderNumber;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "LD", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("1111")
                .chooseDeliveryCountryForShipping("LD")
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("LD", "Bank")
                .nextBtnClick()
                .nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin();
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"0001"},
                {"0550"},
                {"0999"},
                {"0998"},
                {"0000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking ban on entering non-existent index when editing order in aws for Luxembourg")
    public void testCheckingBAnOnEnteringNonexistentIndexWhenEditingOrderInAWS_LD(String indexes) {
        new Order_aws().fillingFieldsPostalCodInBilling(indexes)
                .fillingFieldsPostalCodInShipping("1111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(order_aws.errorIconInFieldPostalConForBilling())
                .checkAbsenceExpectedElement(order_aws.errorIconInFieldPostalConForShipping())
                .fillingFieldsPostalCodInBilling("1111")
                .fillingFieldsPostalCodInShipping(indexes)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(order_aws.errorIconInFieldPostalConForShipping())
                .checkAbsenceExpectedElement(order_aws.errorIconInFieldPostalConForBilling());
    }

    @AfterClass
    private void close() {
        new Order_aws().fillingFieldsPostalCodInBilling("1111")
                .fillingFieldsPostalCodInShipping("1111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        closeWebDriver();
    }
}
