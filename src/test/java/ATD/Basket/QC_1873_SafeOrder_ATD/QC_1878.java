package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1878 {

    private String mail = "QC_1878_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "DE,FR,EN,AT,ES,IT,NL,DK,FI,SE,PL,HU,CZ,BG,GR,RO,SK,BE,LD", "main", "product32");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks absence of SO block on language versions where it is not included.")
    public void testAbsenceOfSO_BlockOnLanguageVersionsWhereITIsNotIncluded(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        String safeOrderText = new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .checkNumberOfDaysInSafeOrderBlockForDeAndFrShops(shop, "200")
                .nextButtonClick()
                .signIn(mail, password)
                .fillAllFieldsForShipping(shop)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkPresenceSafeOrderBlock()
                .getTextInSafeOrderBlock();
        Assert.assertTrue(safeOrderText.contains("200"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
