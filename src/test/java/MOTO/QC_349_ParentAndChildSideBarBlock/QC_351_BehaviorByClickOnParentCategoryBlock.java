package MOTO.QC_349_ParentAndChildSideBarBlock;

import ATD.DataBase;
import ATD.Moto_Parent_Category_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_351_BehaviorByClickOnParentCategoryBlock {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category,moto_category,moto_categories_maker2,moto_parent_category_maker2,moto_category_car_list_model2,moto_category_car_list2,moto_category_maker");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks behavior by click on parent category block")
    public void testChecksBehaviorByClickOnParentCategoryBlock(String route) throws SQLException {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .openParentCategoryCatalogInSidebar()
                .selectParentCategoryInSideBar(1);
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "moto_parent_category2"));
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
