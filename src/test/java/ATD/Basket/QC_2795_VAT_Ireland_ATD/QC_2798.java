package ATD.Basket.QC_2795_VAT_Ireland_ATD;

import ATD.Product_page_Logic;
import AWS.Order_aws;
import AWS.PageVAT_aws;
import AWS.ProfilerPage_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static AWS.ProfilerPage_aws.profilerPage_aws;
import static Common.File.assertThatPdfContainsText;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2798 {

    private String mail = "QC_2798_autotest@autodoc.si";
    private String vatForIE, articleId, orderNumber;
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", true);
        vatForIE = new PageVAT_aws().getVatForIE();
        close();
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks VAT for Ireland")
    public void testChecksVatForIreland(String route) throws SQLException, IOException {
        openPage(route);
        orderNumber = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("IE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickBtnReturnTheCartPage()
                .checkTextContainingVatPercentage(vatForIE)
                .clickBtnNextAndTransitionOnAddressPage()
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .checkTextContainingVatPercentage(vatForIE)
                .nextBtnClick()
                .getOrderNumber();

        Order_aws order_aws = new Order_aws(orderNumber);
        articleId = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .openPopUpAccountsAndCheckVat(vatForIE)
                .closePopupAccounts()
                .clickReturnButton()
                .chooseReturnType("Возврат")
                .clickCheckBoxProductInPopupReturn()
                .clickCheckBoxDeliveryInPopupReturn()
                .clickPrintButtonInPopupReturn()
                .clickBtnClosePopUpReturn()
                .getArticleId();
        assertThatPdfContainsText("C:/Users/User/Downloads/_" + orderNumber + ".pdf", "MwSt. " + vatForIE + " %");


        openPage(profilerPage_aws);
        new ProfilerPage_aws().fillingFieldsOrderIdAndArticleId(orderNumber, articleId)
                .checkVatInTazFormula(new ProfilerPage_aws().taxFormula(), vatForIE);

        new WebMail().openMail(mail, passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNumber)
                .checkTextContainingVatPercentageInEmail(vatForIE);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
