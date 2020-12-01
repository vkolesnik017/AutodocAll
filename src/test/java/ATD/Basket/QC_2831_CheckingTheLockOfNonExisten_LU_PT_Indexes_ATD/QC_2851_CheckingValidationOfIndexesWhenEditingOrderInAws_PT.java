package ATD.Basket.QC_2831_CheckingTheLockOfNonExisten_LU_PT_Indexes_ATD;

import ATD.*;
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

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2851_CheckingValidationOfIndexesWhenEditingOrderInAws_PT {

    private String mail = "QC_2851_autotestATD@mailinator.com";
    private String orderNumber;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "PT", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("1111-111")
                .chooseDeliveryCountryForShipping("PT")
                .fillFieldTelNumForShipping("200+002")
                .fillFieldIdCompanyShipping("autotest")
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
                {"9999-001"},
                {"9999-550"},
                {"9999-999"},
                {"9999-000"},
                {"9999-998"}
        };
    }

    @Test(dataProvider = "indexes")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking validation of indexes when editing order in AWS for Portugal ")
    public void testCheckingValidationOfIndexesWhenEditingOrderInAws_PT(String indexes) {
        new Order_aws().fillingFieldsPostalCodInBilling(indexes)
                .fillingFieldsPostalCodInShipping(indexes)
                .reSaveOrder()
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForBilling())
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForShipping());
    }

    @AfterClass
    private void close() {
        new Order_aws().fillingFieldsPostalCodInBilling("1111-111")
                .fillingFieldsPostalCodInShipping("1111-111")
                .reSaveOrder();
        closeWebDriver();
    }
}
