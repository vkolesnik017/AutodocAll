package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Product_page_Logic;
import ATD.Versand_static_page_Logic;
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
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2733 {

    private DataBase bd = new DataBase("ATD");
    private Product_page_Logic product_page_logic = new Product_page_Logic();
    private String soPrise;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "DE", "main", "staticVersand"));
        soPrise = new Versand_static_page_Logic().getSafeOrderPrice();
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() {
        return new Object[][]{
                {"EE"},
                {"LV"},
                {"LT"},
                {"SI"},
                {"EN"}
        };
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checking for no free SO with ATD + MVP1 package")
    public void testCheckingForNoFreeSO_withATD_plusMVP1package(String shop) throws SQLException {
        String mail = "qc_2733_autotestmvp1@mailinator.com";
        openPage(bd.getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        String orderID = product_page_logic.addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .chooseDeliveryCountryForShipping(shop)
                .fillFieldTelNumForShipping("200+002")
                .nextBtnClick()
                .clickOnTheDesiredPaymentMethod("DE", "Bank")
                .nextBtnClick()
                .checkThatSafeOrderCheckboxIsNotSelected()
                .clickSafeOrderCheckbox()
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
