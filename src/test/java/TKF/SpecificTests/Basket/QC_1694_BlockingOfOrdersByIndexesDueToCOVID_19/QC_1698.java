package TKF.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import Common.SetUp;
import TKF.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_1698 {

    private String email = "qc_1698_autotestCOVID19@mailinator.com";
    private String password = "atdtest";

    private String plzIT = "40059";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("TKF").setUpShopsWithSubroute("prod", "DE", "main", "product");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test check of blocking for split billing and shipping")
    public void testCheckBlockingForSplitBillingAndShipping(String route) {
        open(route);
        new Product_page_Logic().addProductToCart()
                .cartClick()
                .nextBtnClick()
                .signIn(email, password)
                .checkBlockingPLZForCountry("IT", plzIT, "IT", "12345");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}