package Direkt.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

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

import static Direkt.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_1698_CheckOfBlockingForSplitBillingAndShipping {

    private String email = "qc_1698_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "40059";

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
    @Description(value = "Test check of blocking for split billing and shipping")
    public void testCheckBlockingForSplitBillingAndShipping(String route) {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .checkBlockingPLZForCountry("IT", "12345", "IT", plzIT);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}