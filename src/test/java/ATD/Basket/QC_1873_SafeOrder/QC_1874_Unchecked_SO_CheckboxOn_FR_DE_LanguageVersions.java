package ATD.Basket.QC_1873_SafeOrder;

import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1874_Unchecked_SO_CheckboxOn_FR_DE_LanguageVersions {

    private String mail = "QC_1874_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE,FR", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks Unchecked SO checkbox on FR / DE language versions")
    public void testUnchecked_SO_CheckboxOn_FR_DE_LanguageVersions(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String orderNumber = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsAndFirmForShipping(shop, "12345", "autotest", "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, "FR", "autotest")
                .chooseVorkasse()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkThatStatusSafeOrderIsOff()
                .reSaveOrder()
                .checkThatStatusSafeOrderIsOff();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
