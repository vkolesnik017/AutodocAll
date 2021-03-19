package ATD.GDRP;

import ATD.CartAddress_page_Logic;
import ATD.Product_page_Logic;
import AWS.Customer_view_aws;
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

public class QC_3395 {

    private Product_page_Logic productPageLogic = new Product_page_Logic();
    private Customer_view_aws customerViewAws = new Customer_view_aws();
    private DataBase db = new DataBase("ATD");
    private String orderId;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the call consent checkbox in the shopping cart")
    public void testCheckCallConsentCheckboxInShoppingCart() throws SQLException {
        String mail = "qc_3395_autotest@gmail.com";
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product"));
        orderId = productPageLogic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("200+002")
                .clickCheckBoxConsentToCall()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();
        customerViewAws.openCustomerView("29500028")
                .checkStatusOfLastLog();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product"));
        productPageLogic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .clickBtnNextAndTransitionOnAddressPage()
                .checkAbsenceConsentToCall();
        customerViewAws.openCustomerViewWithoutLogin("29500028")
                .cancelConsentToCall();
        openPage(db.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product"));
        productPageLogic.cartClick();
        new CartAddress_page_Logic().checkPresenceConsentToCall();
    }

    @AfterMethod
    private void close() {
        Order_aws order_aws = new Order_aws(orderId);
        order_aws.openOrderInAwsWithoutLogin()
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        closeWebDriver();
    }
}
