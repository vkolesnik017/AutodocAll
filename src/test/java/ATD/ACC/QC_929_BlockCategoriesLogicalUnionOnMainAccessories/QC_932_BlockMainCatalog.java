package ATD.ACC.QC_929_BlockCategoriesLogicalUnionOnMainAccessories;

import ATD.Index_accessories_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_932_BlockMainCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test checks presence block with main catalog categories")
    public void testCheckingPresenceBlockMainCatalogCategories(String route) {
        openPage(route);
        new Index_accessories_page_Logic().checkingPresenceBlockMainCatalogCategories();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
