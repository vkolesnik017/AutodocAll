package ATD.LKW_trucks.QC_18_SideBarBlocksOfParentCategoriesAndLinkingChildCategory;

import ATD.LKW_Parent_Category_page_Logic;
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

public class QC_1662 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check correctness displayed child categories in sidebar")
    public void testCheckCorrectnessDisplayedChildCategoriesInSidebar(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfCatalogBlockInSidebar().checkCorrectnessDisplayingChildCategory();
    }

        @AfterMethod
        public void close() {
            closeWebDriver();
        }
    }
