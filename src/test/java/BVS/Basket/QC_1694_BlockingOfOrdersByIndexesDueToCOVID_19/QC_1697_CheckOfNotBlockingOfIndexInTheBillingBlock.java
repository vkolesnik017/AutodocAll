package BVS.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import BVS.Product_page_Logic;
import BVS.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static BVS.CommonMethods.checkingContainsUrl;
import static BVS.SetUp.setUpBrowser;
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
                .chooseDeliveryCountryAndFillingPostalCode("IT", "12345", "IT", plzIT)
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryAndFillingPostalCode("ES", "12345","ES", plzES)
                .nextBtnClick()
                .clickBtnReturnTheAddressPage()
                .chooseDeliveryCountryAndFillingPostalCode("PT", "1234-567", "PT", plzPT)
                .nextBtnClick();
        checkingContainsUrl("https://www.motordoctor.de/basket/payments");

    }
}