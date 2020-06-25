package PKW.ACC.QC_1020_BlockTopProductsOnMainACC;

import PKW.Index_accessories_page_Logic;
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

public class QC_1022_BlockTopProducts {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence block Top Products than work btn Previous and Next")
    public void testCheckingPresenceBlockTopProductsThanWorkBtnPrevAndNext(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPresenceBlockTopProductsThanWorkBtnPrevAndNext();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
