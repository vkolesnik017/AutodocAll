package ATD.Basket.QC_1932_RouteAddress_ATD;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1934 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routeEN", parallel = true)
    Object[] dataProviderEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "EN", "main", "product32");
    }

    @Test(dataProvider = "routeEN")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the appearance fiscal code field for EN shop")
    public void testInputTIN_ForEnShop(String routeEN) throws SQLException {
        String mailEN = "QC_1933_autotestATD@mailinator.com";
        openPage(routeEN);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailEN, password)
                .chooseDeliveryCountryForShipping("RO")
                .fillingPostalCodeFieldJSForShipping("12345")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryForShipping("PT")
                .fillingPostalCodeField("1234567")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryForShipping("IT")
                .fillingPostalCodeFieldJSForShipping("12345")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick();
        checkingContainsUrl("/basket/payments");
    }


    @DataProvider(name = "routeES", parallel = true)
    Object[] dataProviderES() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "ES", "main", "product32");
    }

    @Test(dataProvider = "routeES")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the appearance fiscal code field for ES shop")
    public void testInputTIN_ForEsShop(String routeES) throws SQLException {
        String mailES = "QC_1933_autotestATDES@mailinator.com";
        openPage(routeES);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mailES, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillingPostalCodeFieldJSForShipping("35111")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .fillingPostalCodeFieldJSForShipping("38111")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .fillingPostalCodeFieldJSForShipping("52111")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .fillingPostalCodeFieldJSForShipping("51111")
                .checkPresenceFiscalCodBlockInShippingForm()
                .clickCheckboxForOpenFiscalCodeField()
                .clickCheckboxForClosedFiscalCodeField()
                .clickCheckboxForOpenFiscalCodeField()
                .nextBtnClick();
        checkingContainsUrl("/basket/payments");
    }


    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}