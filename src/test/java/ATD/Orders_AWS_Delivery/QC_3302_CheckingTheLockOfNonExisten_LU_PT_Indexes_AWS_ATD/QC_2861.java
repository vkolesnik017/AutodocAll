package ATD.Orders_AWS_Delivery.QC_3302_CheckingTheLockOfNonExisten_LU_PT_Indexes_AWS_ATD;

import ATD.Payment_handler_page_Logic;
import ATD.Product_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2861 {

    private String mail = "QC_2861_autotestATD@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks an error displaying when entered index does not correspond to Luxembourg mask (in AWS)")

    public void testChecksAnErrorDisplayingWhenEnteredIndexDoesNotCorrespondTo_LU_mask_AWS() throws SQLException {
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
        String orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .fillingFieldsPostalCodInBilling("99")
                .fillingFieldsPostalCodInShipping("99")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForBilling())
                .checkPresenceExpectedElement(new Order_aws().errorIconInFieldPostalConForShipping());
    }

    @AfterMethod
    private void close() {
        new Order_aws().fillingFieldsPostalCodInBilling("1111-111")
                .fillingFieldsPostalCodInShipping("1111-111")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        closeWebDriver();
    }
}
