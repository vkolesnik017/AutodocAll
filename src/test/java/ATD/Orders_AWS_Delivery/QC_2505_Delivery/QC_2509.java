package ATD.Orders_AWS_Delivery.QC_2505_Delivery;

import AWS.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_2509 {

    private String customerID = "18709192", artNum = "50-307044-50";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "country", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Object[][]{
                {"Andorra"}, {"Argentina"}, {"Australia"}, {"Austria"}, {"Belgium"}, {"Bosnia and Herzegovina"}, {"Brazil"}, {"Canada"},
                {"Chile"}, {"Colombia"}, {"Croatia"}, {"Cyprus"}, {"Czech Republic"}, {"Denmark"}, {"Dominican Republic"}, {"Ecuador"},
                {"Egypt"}, {"Estonia"}, {"Finland"}, {"Germany"}, {"Greece"}, {"Holland"}, {"Hungary"}, {"Iceland"},
                {"Ireland"}, {"Israel"}, {"Italy"}, {"Kenya"}, {"Latvia"}, {"Liechtenstein"}, {"Lithuania"}, {"Luxembourg"}, {"Macedonia"},
                {"Malta"}, {"Mexico"}, {"Moldova, Republic of"}, {"Monaco"}, {"Mozambique"}, {"New Zealand"}, {"Norway"}, {"Panama"},
                {"Poland"}, {"Portugal"}, {"Puerto Rico"}, {"Romania"}, {"San Marino"}, {"Serbia"}, {"Slovakia"}, {"Slovenia"}, {"South Africa"},
                {"Spain"}, {"Sweden"}, {"Switzerland"}, {"Turkey"}, {"United Arab Emirates"}, {"United Kingdom"}, {"United States"}, {"Uruguay"},
                {"Zambia"}, {"India"}
        };
    }

    @Test(dataProvider = "country")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks delivery cost from AWS")
    public void testCheckDeliveryCost_Front(String country) throws SQLException {
        String countryName = new SearchOrders_page_aws().openSearchOrderPageWithLogin()
                .clickAddOrderBtn()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("pkwteile.de (DE)")
                .chooseDeliveryCountryAndGetNameCountry(country);

        executeJavaScript("window.open('about:blank', '-blank')");
        switchTo().window(1);
        openPage(new Delivery_prices_aws().delivery_prices_aws);

        float deliveryPrice = new Delivery_prices_aws().getDeliveryPriceWithoutTranslationCountries(countryName);
        switchTo().window(0);
        float deliveryPriceInOrder = new OrderAdd_page_aws().selectedPaymentMethod("Vorkasse")
                .selectedDeliveryMethod("Standardversand")
                .addProduct(artNum)
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct(artNum)
                .clickSaveOrderBtn()
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryPrice, deliveryPriceInOrder);
        float deliveryPriceInOrderAfterReset = new Order_aws().reSaveOrder()
                .getDeliveryCostInOrder();
        Assert.assertEquals(deliveryPrice, deliveryPriceInOrderAfterReset);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}