package PKW.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import PKW.Product_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_1697_CheckOfNotBlockingOfIndexInTheBillingBlock {

    private String email = "qc_1697_autotestCOVID19@mailinator.com";
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
    @Description(value = "Test check of not blocking of index in the billing block")
    public void testCheckNotBlockingIndexInBillingBlock(String route) {
        open(route);
        new Product_page_Logic().closeBtnOFPopupReviewIfYes()
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(email, password)
                .chooseDeliveryCountryAndFillingPostalCode("IT", "12345", "IT", plzIT)
                .nextBtnClick();
        checkingContainsUrl("https://www.pkwteile.de/basket/payments");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}

