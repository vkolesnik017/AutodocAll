package PKW.ACC.QC_1419_MainCatalogProductsOnMainChemistry;

import Common.SetUp;
import PKW.Index_chemicals_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1535_BlockWithCategories {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence main categories catalog block.")
    public void testCheckingPresenceMainCategoriesCatalogBlock(String route) {
        openPage(route);
        new Index_chemicals_page_Logic().checkingPresenceMainCategoriesCatalogBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
