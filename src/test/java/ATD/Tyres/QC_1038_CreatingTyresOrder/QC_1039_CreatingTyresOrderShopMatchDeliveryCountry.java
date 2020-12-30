package ATD.Tyres.QC_1038_CreatingTyresOrder;

import ATD.*;
import AWS.Order_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1039_CreatingTyresOrderShopMatchDeliveryCountry {
    private String emailDE = "qc_1039_autotestDE@mailinator.com";
    private String passwordDE = "password";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form,tyre_form2,tyre_form3,tyre_form4");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks order with tyres where shop match delivery country")
    public void testTyresOrderShopMatchDeliveryCountry(String route) {
        openPage(route);
        String orderNumber = new TyresListing_page_Logic().addFirstProductAndGoToCart()
                                     .nextButtonClick()
                                     .signIn(emailDE, passwordDE)
                                     .nextBtnClick()
                                     .chooseVorkasse()
                                     .nextBtnClick()
                                     .checkFreeDeliveryPriceAllData("0,00")
                                     .nextBtnClick()
                                     .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                                  .checkDeliveryPriceOrderAWS("0");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}