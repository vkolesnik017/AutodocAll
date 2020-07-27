package PKW.ACC.QC_1752_BlockTopCategoriesOnMainTools;

import PKW.Index_instruments_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_1754_QuantityCategoriesInTopCategoriesBlock {

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
    @Description(value = "Test checks quantity category in top categories block.")
    public void testCheckingQuantityCategoriesInTopCategoriesBlock(String route) {
        openPage(route);
        new Index_instruments_page_Logic().checkingQuantityCategoriesInTopCategoriesBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
