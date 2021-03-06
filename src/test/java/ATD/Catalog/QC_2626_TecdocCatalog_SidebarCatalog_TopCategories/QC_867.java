package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

import Common.CommonMethods;
import Common.DataBase;
import ATD.Moto_main_page_Logic;
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

public class QC_867 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to Catalog by click on Expand link ")
    public void testChecksTransitionToCatalog(String route) throws SQLException {
        CommonMethods commonMethods = new CommonMethods();
        DataBase db = new DataBase("ATD");
        openPage(route);

        new Moto_main_page_Logic()
                .clickOnMoreLinkAtParentCatalog();
        commonMethods.checkingContainsUrl(db.getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_categories"));

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
