package ATD.Orders_AWS_Delivery.QC_3349_VAT_Ireland;

import ATD.Product_page_Logic;
import AWS.Order_aws;
import AWS.PageVAT_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.File.assertThatPdfContainsText;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2859 {

    private String mail = "QC_2859autotest@autodoc.si";
    private String vatForIE, orderNumber;
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
    @Description(value = "Checking VAT when changing the country of delivery to ABC")
    public void testCheckVatWhenChangingTheCountryOfDeliveryToAWS(String route) throws SQLException, IOException {
        openPage(route);
        orderNumber = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping("DE")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .nextBtnClick()
                .getOrderNumber();

        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .choosesDeliveryCountry("Ireland")
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
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
