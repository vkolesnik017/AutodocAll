package ATD.Orders_AWS_Delivery.QC_3302_CheckingTheLockOfNonExisten_LU_PT_Indexes_AWS_ATD;

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

public class QC_2860 {

    private String mail = "QC_2860_autotestATD@mailinator.com";
    private String orderNumber;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "PT", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("1111-111")
                .chooseDeliveryCountryForShipping("PT")
                .fillFieldTelNumForShipping("200+002")
                .fillFieldFirmNameForShipping("autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded("PT", "PT", "autotest")
                .clickOnTheDesiredPaymentMethod("PT", "Bank")
                .nextBtnClick()
                .nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin();
    }

    @DataProvider(name = "indexes")
    Object[] dataProviderProducts() {
        return new Object[][]{
                {"1111-111"},
                {"5264-345"},
                {"9998-999"},
                {"9989-000"},
                {"1000-000"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking validation of indexes when editing order in AWS for Portugal. Positive Case")
    public void testCheckingValidationOfIndexesWhenEditingOrderInAws_PT_PositiveCase(String indexes) {
        new Order_aws().fillingFieldsPostalCodInBilling(indexes)
                .fillingFieldsPostalCodInShipping(indexes)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen")
                .checkAbsenceExpectedElement(new Order_aws().errorIconInFieldPostalConForBilling())
                .checkAbsenceExpectedElement(new Order_aws().errorIconInFieldPostalConForShipping())
                .checkPostalCodInBillingBlock(indexes)
                .checkPostalCodInShippingBlock(indexes);
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}
