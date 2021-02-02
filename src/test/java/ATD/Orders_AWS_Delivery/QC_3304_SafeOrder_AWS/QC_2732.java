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

public class QC_2732 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shopForBASIK", parallel = true)
    Object[] dataProviderBASIK() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "shopForBASIK")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 BASIK package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_BASIK_package(String shopForBASIK) {
        openPage(shopForBASIK);
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23333984";
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


    @DataProvider(name = "shopForOPTIMUM", parallel = true)
    Object[] dataProviderOPTIMUM() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "shopForOPTIMUM")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 OPTIMUM package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_OPTIMUM_package(String shopForOPTIMUM) {
        openPage(shopForOPTIMUM);
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23334154";
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


    @DataProvider(name = "shopForTRIAL", parallel = true)
    Object[] dataProviderTRIAL() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "shopForTRIAL")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 TRIAL package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_TRIAL_package(String shopForTRIAL) {
        openPage(shopForTRIAL);
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23334376";
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


    @DataProvider(name = "shopForPRO", parallel = true)
    Object[] dataProviderPRO() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "shopForPRO")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 PRO package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_PRO_package(String shopForPRO) {
        openPage(shopForPRO);
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23334552";
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
