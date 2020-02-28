package ATD.Tyres.QC_1038_CreatingTyresOrder;



import ATD.*;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_1039_CreatingTyresOrderShopMatchDeliveryCountry {
    private String emailDE = "qc_1039_autotestDE@mailinator.com";
    private String passwordDE = "password";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks order with tyres where shop match delivery country")
    public void testTyresOrderShopMatchDeliveryCountry() {
        openPage("https://autodoc.de/reifen/search?Width=120&CrossSections=70&R_ZR=r&Size=17&type=motorrad");
        String orderNumber = new TyresListing_page_Logic().addFirstProductAndGoToCart()
                                     .nextButtonClick()
                                     .signIn(emailDE, passwordDE)
                                     .nextBtnClick()
                                     .chooseVorkasse()
                                     .nextBtnClick()
                                     .checkDeliveryPriceAlldata("0,00")
                                     .nextBtnClick()
                                     .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                                  .checkDeliveryPriceOrderAWS("0");
    }
}
