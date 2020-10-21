package ATD.Basket.QC_1873_SafeOrder;

import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_2346_CheckingDeliveryCostWhenTurningSO_Off_On {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "mail", parallel = true)
    Object[] dataProvider() {
        return new Object[][]{
                {"QC_2346_autotest@mailinator.com"},
                {"QC_2346_plusProAutotest@mailinator.com"}
        };
    }

    @Test(dataProvider = "mail")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks delivery costs when SO is turned off / on")
    public void testCheckingDeliveryCostWhenTurningSO_Off_On(String mail) throws Exception {
        float deliveryLimit = new Versand_static_page_Logic().getDeliveryPriceForUserWithSubscriptionPlusPro("Polen", mail);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product43"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("1111")
                .chooseDeliveryCountryForShipping("PL")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .clickSafeOrderCheckbox()
                .checkRegularDeliveryPrice(deliveryLimit)
                .clickBtnReturnToCartPage()
                .checkPresenceSOInSummeryBlock()
                .clickSafeOrderCheckbox()
                .checkAbsenceSOInSummeryBlock()
                .clickBtnNextAndTransitionOnAddressPage()
                .nextBtnClick()
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .checkRegularDeliveryPrice(deliveryLimit);
    }


    private String mail = "QC_2346_autotest@mailinator.com";

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks delivery costs in Island when SO is turned off / on")
    public void testCheckingDeliveryCostInIslandWhenTurningSO_Off_On() throws Exception {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product43"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillingPostalCodeFieldJSForShipping("37005")
                .chooseDeliveryCountryForShipping("GR")
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .clickSafeOrderCheckbox()
                .checkRegularDeliveryPrice("19,00")
                .clickBtnReturnToCartPage()
                .checkPresenceSOInSummeryBlock()
                .clickSafeOrderCheckbox()
                .checkAbsenceSOInSummeryBlock()
                .clickBtnNextAndTransitionOnAddressPage()
                .nextBtnClick()
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .checkRegularDeliveryPrice("19,00");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}