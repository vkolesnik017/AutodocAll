package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
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

public class QC_2728 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "EE,LV,LT,SI,EN", "main", "staticVersand");
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP1 package")
    public void testCheckingForNoFreeSO_withATD_MVP1(String route) throws SQLException {
        openPage(route);
        String mail = "qc_2728_autotestmvp1@mailinator.com";
        String shop = getCurrentShopFromJSVarInHTML();
        String soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
        openPage(bd.getFullRouteByRouteAndSubroute("prod", shop, "main", "product32"));
        String orderID = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsSelected()
                .checkSumSO(soPrise)
                .nextBtnClick()
                .getOrderNumber();
        new Order_aws(orderID).openOrderInAwsWithLogin()
                .checkSafeOrderPrice(soPrise)
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
