package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.Index_accessories_group_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2775 {

    private Index_accessories_group_page_Logic indexAccessoriesGroupPageLogic = new Index_accessories_group_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "index_accessories_group");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks presence and work of the breadcrumbs block.")
    public void testCheckingPresenceAndWorkBreadCrumbsBlock(String route) {
        openPage(route);
        indexAccessoriesGroupPageLogic.checkingPresenceBreadCrumbsBlock()
        .clickFirstBreadCrumb();
        checkingContainsUrl("autoteile");
        back();
        indexAccessoriesGroupPageLogic.clickSecondBreadCrumb();
        checkingContainsUrl("autozubehoer");
        back();
        indexAccessoriesGroupPageLogic.checkingPresenceAndNotClickableThirdBreadCrumb();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
