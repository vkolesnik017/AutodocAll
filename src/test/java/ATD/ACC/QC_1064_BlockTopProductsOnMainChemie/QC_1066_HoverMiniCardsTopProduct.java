package ATD.ACC.QC_1064_BlockTopProductsOnMainChemie;

import ATD.Index_chemicals_page_Logic;
import Common.SetUp;
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

public class QC_1066_HoverMiniCardsTopProduct {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence popup with characteristics product after hover in block top-6")
    public void testCheckPresencePopupWithCharacteristicsProductInTop6Block(String route) {
        openPage(route);
        new Index_chemicals_page_Logic().checkingHoverPopupInTop6ProductBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
