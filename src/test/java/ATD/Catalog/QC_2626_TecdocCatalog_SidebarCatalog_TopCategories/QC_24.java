package ATD.Catalog.QC_2626_TecdocCatalog_SidebarCatalog_TopCategories;

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

public class QC_24 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks child categories block in Sidebar")
    public void testChecksChildCategoriesBlockInsideBar(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfElementsChildCategoriesBlockInSideBar();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
