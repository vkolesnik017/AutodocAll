package ATD.Orders_AWS_Delivery.QC_3304_SafeOrder_AWS;

import ATD.Versand_static_page_Logic;
import AWS.OrderAdd_page_aws;
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

import static ATD.CommonMethods.getCurrentShopFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2731 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP1 package")
    public void testCheckingForNoFreeSO_withATD_plusMVP1package(String route) {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23264243";
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID(customerID)
                .selectShopForSkinATDFopSpecificCountry(shop)
                .choosingDeliveryCountryEqualToShop(shop)
                .selectedBankMethod(shop, "HypoVereinsbank")
                .selectedDeliveryMethod("Standardversand")
                .selectedStatusInSafeOrder("Включен")
                .addProduct("z306")
                .checkPresenceTableOfSuppliersAndClickBtnSelect()
                .checkArticleOfAddedProduct("Z306")
                .clickSaveOrderBtn();
        new Order_aws().checkSafeOrderPrice(soPrise)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
