package PKW.Payments;

import PKW.CartAllData_page_Logic;
import PKW.Product_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.DataBase.parseUserIdFromBD;
import static Common.DataBase.parseUserMailFromBD;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.*;

public class QC_2464_Bank_PKW {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = false)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopsWithSubroute("prod", "DE,AT,BG,CH,CZ,DK,ES,FI,FR,GR,HU,IT,NL,NO,PL,PT,RO,SE,EN", "main", "product9");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description("Test checks method of payment by Bank")
    public void testPaymentsMethodBank(String route) throws Exception {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String userData = new DataBase("PKW").getUserIdForPaymentsMethod("payments_userid_pkw", shop, "Bank");
        String userID = parseUserIdFromBD(userData);
        String mail = parseUserMailFromBD(userData);
        float totalPriceAllData = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checksForLabelOfBankPaymentMethod()
                .nextButtonClick()
                .signIn(mail, passwordForPayments)
                .chooseDeliveryCountryAndFillingFirmInput( shop, "autotest")
                .fillInCompanyIdFieldForCountryWhereIdNeeded(shop, shop, "autotest")
                .clickOnTheDesiredPaymentMethod(shop, "Bank")
                .nextBtnClick()
                .checksForLabelOfBankPaymentMethod()
                .getTotalPriceAllDataPage(shop);
        /*String requisitesText =*/ new CartAllData_page_Logic().nextBtnClick()
                .closePopupAfterOrder()
                .compareExpectedRequisitesWithActual(shop);

    }
}
