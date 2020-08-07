package PKW.ACC.QC_1417_MainChemistry;

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
import static PKW.CommonMethods.checkingContainsUrl;
import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_1418_BreadCrumbsOnMainPageChemistry {

    private Index_chemicals_page_Logic index_chemicals_page_logic = new Index_chemicals_page_Logic();

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
    @Description(value = "Test checks transition for two breadcrumbs.")
    public void testCheckTransitionForTwoBreadCrumbs(String route) {
        openPage(route);
        index_chemicals_page_logic.checkingPresenceBlockBreadCrumbs()
                .clickOnFirstBreadCrumb();
        checkingContainsUrl("ersatzteile");
        back();
        index_chemicals_page_logic.checkingPresenceAndNotClickableSecondCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
