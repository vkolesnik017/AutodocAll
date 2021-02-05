package PKW.Basket.QC_2408_SafeOrder_PKW;

import AWS.Order_aws;
import Common.SetUp;
import PKW.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_2409 {

    private String mail = "QC_2409_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,FR", "main", "product12");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks Unchecked SO checkbox on FR / DE language versions")
    public void testUnchecked_SO_CheckboxOn_FR_DE_LanguageVersions(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String orderNumber = new Product_page_Logic().addProductToCart()
                .closeBtnOFPopupReviewIfYes()
                .cartClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryAndFillingFirmInput( shop, "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
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
