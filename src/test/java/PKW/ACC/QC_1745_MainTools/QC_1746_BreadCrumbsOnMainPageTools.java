package PKW.ACC.QC_1745_MainTools;

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
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.close;


public class QC_1746_BreadCrumbsOnMainPageTools {

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
    @Description(value = "Test Checking transition for two bread crumbs.")
    public void testCheckingTransitionForTwoBreadCrumbs(String route) {
        openPage(route);
        new Index_instruments_page_Logic().clickFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        new Index_instruments_page_Logic().checkingPresenceAndNotClickableSecondCrumb();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}