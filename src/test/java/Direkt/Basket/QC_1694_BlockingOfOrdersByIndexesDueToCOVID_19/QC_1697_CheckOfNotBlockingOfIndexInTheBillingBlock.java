package Direkt.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import Direkt.Product_page_Logic;
import Direkt.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Direkt.CommonMethods.checkingContainsUrl;
import static Direkt.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1697_CheckOfNotBlockingOfIndexInTheBillingBlock {

    private String email = "qc_1697_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "00017";
    private String plzES = "10900";
    private String plzPT = "3880-365";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopsWithSubroute("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks translation of error popup on address page")
    public void testCheckErrorTranslateOnAddressPage(String route) {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .chooseDeliveryCountryAndFillingPostalCode("IT", plzIT, "IT", "12345")
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryAndFillingPostalCode("ES", plzES, "ES", "12345")
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryAndFillingPostalCode("PT", plzPT, "PT", "1234-567")
                .nextBtnClick();
        checkingContainsUrl("https://www.autoteiledirekt.de/basket/payments.html");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}