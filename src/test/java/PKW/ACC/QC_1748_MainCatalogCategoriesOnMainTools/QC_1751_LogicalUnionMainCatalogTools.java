package PKW.ACC.QC_1748_MainCatalogCategoriesOnMainTools;

import Common.SetUp;
import PKW.Index_instruments_page_Logic;
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

public class QC_1751_LogicalUnionMainCatalogTools {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence categories block in logical union from main catalog.")
    public void testCheckingPresenceCategoriesInLogicalUnion(String route) {
        openPage(route);
        new Index_instruments_page_Logic().checkingPresenceCategoriesInLogicalUnion();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
