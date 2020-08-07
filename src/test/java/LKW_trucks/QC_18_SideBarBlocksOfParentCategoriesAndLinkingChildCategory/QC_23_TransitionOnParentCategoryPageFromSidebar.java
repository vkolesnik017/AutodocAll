package LKW_trucks.QC_18_SideBarBlocksOfParentCategoriesAndLinkingChildCategory;

import ATD.LKW_Parent_Category_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_23_TransitionOnParentCategoryPageFromSidebar {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_parent_category,lkw_category,lkw_category_maker,lkw_category_brand,lkw_category_maker_brand,lkw_category_car_list14,lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition on Parent category page from child category in Sidebar")
    public void testChecksTransitionOnParentCategoryPage(String route) {
        openPage(route);
        new LKW_Parent_Category_page_Logic().presenceOfAllParentCategoriesInSideBar()
                .goToParentCategoryPage()
                .checkSuccessfullyLKWParentCategoryPageLoading("https://lkwteile.autodoc.de/ersatzteile/abgasanlage");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
