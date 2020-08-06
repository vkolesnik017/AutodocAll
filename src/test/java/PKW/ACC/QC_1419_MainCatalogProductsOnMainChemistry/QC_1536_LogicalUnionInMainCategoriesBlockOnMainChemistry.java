package PKW.ACC.QC_1419_MainCatalogProductsOnMainChemistry;

import PKW.Index_chemicals_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1536_LogicalUnionInMainCategoriesBlockOnMainChemistry {

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
    @Description(value = "Test checks presence categories block in Logical union.")
    public void testCheckingPresenceCategoriesBlockInLogicalUnion(String route) {
        openPage(route);
        new Index_chemicals_page_Logic().checkingPresenceCategoriesBlockInLogicalUnion();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
