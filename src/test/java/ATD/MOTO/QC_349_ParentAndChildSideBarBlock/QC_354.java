package ATD.MOTO.QC_349_ParentAndChildSideBarBlock;

import ATD.Moto_Category_page_Logic;
import ATD.Moto_Parent_Category_page_Logic;
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

public class QC_354 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category,moto_category_maker,moto_category_car_list_model3,moto_category_car_list3");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the list of child categories in the sidebar of linking TOP child block ")
    public void testChecksListOfChildCategoriesInSidebar(String route) {
        openPage(route);

        new Moto_Category_page_Logic()
                .checkChildCategoriesLink();
    }

    @DataProvider(name = "routesParentCategory", parallel = true)
    Object[] dataProviderParentCategory() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category,moto_parent_category_maker2");

    }

    @Test(dataProvider = "routesParentCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks the list of child categories in the sidebar of linking TOP child block ")
    public void testChecksListOfChildCategoriesInSidebarParentCategory(String route) {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .checkChildCategoriesLink();
    }
    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
