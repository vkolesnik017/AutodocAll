package BVS.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import BVS.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static BVS.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1697 {

    private String email = "qc_1697_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "40059";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("BVS").setUpShopsWithSubroute("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check of not blocking of index in the billing block")
    public void testCheckNotBlockingIndexInBillingBlock(String route) {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .chooseDeliveryCountryAndFillingPostalCode("IT", "12345", "IT", plzIT)
                .nextBtnClick();
        checkingContainsUrl("https://www.motordoctor.de/basket/payments");

    }
}