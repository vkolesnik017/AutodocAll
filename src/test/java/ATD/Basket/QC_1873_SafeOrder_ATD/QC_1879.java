package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Product_page_Logic;
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

public class QC_1879 {

    private String mail = "QC_1879_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "CH,NO", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks absence of SO block on language versions where it is not included.")
    public void testAbsenceOfSO_BlockOnLanguageVersionsWhereITIsNotIncluded(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkAbsenceSafeOrderBlock()
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFields(shop)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkAbsenceSafeOrderBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}