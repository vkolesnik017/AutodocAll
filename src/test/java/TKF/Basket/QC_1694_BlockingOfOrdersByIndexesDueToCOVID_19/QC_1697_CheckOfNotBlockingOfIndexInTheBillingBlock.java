package TKF.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import TKF.Product_page_Logic;
import TKF.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static TKF.CommonMethods.checkingContainsUrl;
import static TKF.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1697_CheckOfNotBlockingOfIndexInTheBillingBlock {

    private String email = "qc_1697_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "00017";
    private String plzES = "10900";

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
    @Description(value = "Test check of not blocking of index in the billing block")
    public void testCheckNotBlockingIndexInBillingBlock(String route) {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextBtnClick()
                .signIn(email, password)
                .chooseDeliveryCountryAndFillingPostalCode("IT", "12345", "IT", plzIT)
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryAndFillingPostalCode("ES", "12345", "ES", plzES)
                .nextBtnClick();
        checkingContainsUrl("https://www.autotex.de/basket/payments");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}