package ATD.Basket.QC_1486_Island;

import ATD.CartAllData_page_Logic;
import ATD.Search_page_Logic;
import Common.SetUp;
import ATD.Versand_static_page_Logic;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_1492_ChecksVerificationIslandsAndFirm_BillingIsDivided_DifferentCountries_NegativeCase {

    private String email = "QC_1492_autotest@autodoc.si", orderNumber;
    private Float totalPrice, totalPriceAWSOrder, totalPriceInEmail;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks verification of islands + Firm, Different Countries, billing is divided (Negative case)")
    public void testChecksVerificationIslandsAndFirmAndDifferentCountries(String route) throws Exception {
        String deliveryPriceToBEalldata = new Versand_static_page_Logic().getDeliveryPrice("Belgien");
        Float deliveryPriceToBEaws = new Versand_static_page_Logic().getDeliveryPriceForAWS("Belgien");
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        clickOfBuyBtnForAllPages();
        totalPrice = new Search_page_Logic().closePopupOtherCategoryIfYes()
                .cartClick().nextButtonClick()
                .signIn(email, password)
                .fillAllFieldsAndFirmForShipping("BE", "1070", "SPRL Brasserie Cantillon", "Anderlecht")
                .fillFieldIdCompanyShipping("0402065988")
                .clickCheckboxForOpenBilling()
                .chooseDeliveryCountryForBilling("FR")
                .fillingPostalCodeFieldJSForBilling("20000")
                .nextBtnClick()
                .checkPresenceOfPayPalMethod()
                .chooseVorkasse().nextBtnClick()
                .checkAbsenceOfVatPercentage()
                .checkRegularDeliveryPriceAllData(deliveryPriceToBEalldata)
                .checkPresenceSafeOrderBlock()
                .getTotalPriceAllDataPage(shop);
        orderNumber = new CartAllData_page_Logic().nextBtnClick().getOrderNumber();
        Order_aws order_aws = new Order_aws(orderNumber);
        totalPriceAWSOrder = order_aws.openOrderInAwsWithLogin()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS(deliveryPriceToBEaws)
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);
        totalPriceAWSOrder = order_aws.reSaveOrder()
                .checkVatStatusInOrder("Ohne Mwst")
                .checkDeliveryPriceOrderAWS(deliveryPriceToBEaws)
                .getTotalPriceOrderAWS();
        Assert.assertEquals(totalPrice, totalPriceAWSOrder);

        totalPriceInEmail = new WebMail().openMail("QC_1492_autotest@autodoc.si", passwordForMail)
                .checkAndOpenLetterWithOrderNumber(orderNumber)
                .checkRegularDeliveryPriceInEmail(deliveryPriceToBEalldata)
                .checkAbsenceVatPercentageInEmail()
                .getTotalPriceInEmail();
        Assert.assertEquals(totalPrice, totalPriceInEmail);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
