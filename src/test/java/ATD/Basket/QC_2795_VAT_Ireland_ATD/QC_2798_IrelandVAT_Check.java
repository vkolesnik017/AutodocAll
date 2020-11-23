package ATD.Basket.QC_2795_VAT_Ireland_ATD;

import ATD.Product_page_Logic;
import AWS.Order_aws;
import AWS.PageVAT_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.File.readPdfContent;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2798_IrelandVAT_Check {

    private String mail = "QC_2798_autotest@mailinator.com";
    private String vatForIE;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
        vatForIE = new PageVAT_aws().getVatForIE();
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProductsPL() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product2");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks VAT for Ireland")
    public void testChecksVatForIreland(String route) throws SQLException, IOException {
        openPage(route);
        String orderNumber = new Product_page_Logic().addProductToCart()
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
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .reSaveOrder()
                .checkVatStatusInOrder("Mit MwSt " + vatForIE + "%")
                .openPopUpAccountsAndCheckVat(vatForIE)
                .closePopupAccounts()
                .clickReturnButton()
                .chooseReturnType("Возврат")
                .clickCheckBoxProductInPopupReturn()
                .clickCheckBoxDeliveryInPopupReturn()
                .clickPrintBtnInPopupReturn();
        switchTo().window(1);
        switchTo().defaultContent();
        String url = url();
        String pdfContent = readPdfContent(url);
        Assert.assertTrue(pdfContent.contains("MwSt. " + vatForIE + "%".replaceAll("\\W", "")));

    }

    }
