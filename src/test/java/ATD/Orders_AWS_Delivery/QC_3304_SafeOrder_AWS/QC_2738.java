package ATD.Orders_AWS_Delivery.QC_3304_SafeOrder_AWS;

import ATD.Versand_static_page_Logic;
import AWS.OrderAdd_page_aws;
import AWS.Order_aws;
import Common.DataBase;
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

public class QC_2738 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shopForBASIK", parallel = true)
    Object[] shopForBASIK() {
        return new Object[][]{
                {"EE"}, {"LV"}, {"LT"}, {"SI"}, {"EN"}
        };
    }
    @Test(dataProvider = "shopForBASIK")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 BASIK package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_BASIK_package(String shopForBASIK) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE","main", "staticVersand"));
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23742638";
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .choosingDeliveryCountryEqualToShop(shopForBASIK)
                .selectedPaymentMethod("HypoVereinsbank")
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
    Object[] shopForOPTIMUM() {
        return new Object[][]{
                {"EE"}, {"LV"}, {"LT"}, {"SI"}, {"EN"}
        };
    }
    @Test(dataProvider = "shopForOPTIMUM")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 OPTIMUM package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_OPTIMUM_package(String shopForOPTIMUM) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE","main", "staticVersand"));
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23742802";
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .choosingDeliveryCountryEqualToShop(shopForOPTIMUM)
                .selectedPaymentMethod("HypoVereinsbank")
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
    Object[] shopForTRIAL() {
        return new Object[][]{
                {"EE"}, {"LV"}, {"LT"}, {"SI"}, {"EN"}
        };
    }
    @Test(dataProvider = "shopForTRIAL")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 TRIAL package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_TRIAL_package(String shopForTRIAL) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE","main", "staticVersand"));
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23742984";
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .choosingDeliveryCountryEqualToShop(shopForTRIAL)
                .selectedPaymentMethod("HypoVereinsbank")
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
    Object[] shopForPRO() {
        return new Object[][]{
                {"EE"}, {"LV"}, {"LT"}, {"SI"}, {"EN"}
        };
    }
    @Test(dataProvider = "shopForPRO")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP2 PRO package")
    public void testCheckingForNoFreeSO_withATD_plusMVP2_PRO_package(String shopForPRO) throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE","main", "staticVersand"));
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        String customerID = "23743177";
        new OrderAdd_page_aws().openAddOrderPageWithLogin()
                .fillsInFieldCustomerID(customerID)
                .chooseSkinInSelector("autodoc.de (DE)")
                .choosingDeliveryCountryEqualToShop(shopForPRO)
                .selectedPaymentMethod("HypoVereinsbank")
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

