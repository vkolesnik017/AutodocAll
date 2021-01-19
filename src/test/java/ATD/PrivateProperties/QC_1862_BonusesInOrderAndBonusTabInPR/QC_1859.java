package ATD.PrivateProperties.QC_1862_BonusesInOrderAndBonusTabInPR;

import ATD.*;
import AWS.Order_aws;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1859 {

    private String mail = "QC_1859_autotest@mailinator.com", orderNumber;

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .fillFieldTelNumForShipping("100+001")
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .nextBtnClick();
        orderNumber = new Payment_handler_page_Logic().getOrderNumber();
        close();
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderAllShops() {
        return new SetUp("ATD").setUpShop("prod", "AT,BG,BE,CZ,DE,DK,EE,ES,FI,FR,EN,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK");
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "The test checks the translation of the “Bonus” plate in the order header")
    public void testReconciliationOfCurrencies(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToMyOrdersPage()
                .checkTranslationBonusTooltip(shop)
                .logOutClick();
    }

    @AfterClass
    private void tearDown() {
        new Order_aws(orderNumber).openOrderInAwsWithLogin()
                .checkCurrentStatusInOrder("Neue Bestellung")
                .reSaveOrder()
                .checkCurrentStatusInOrder("Testbestellungen");
        close();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
