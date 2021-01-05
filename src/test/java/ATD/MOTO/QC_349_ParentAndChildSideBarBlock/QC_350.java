package ATD.MOTO.QC_349_ParentAndChildSideBarBlock;

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

public class QC_350 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category,moto_category,moto_categories_maker2,moto_parent_category_maker2,moto_category_car_list_model2,moto_category_car_list2,moto_category_maker");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of parent sidebar block")
    public void testChecksPresenceOfParentSideBarBlock(String route) {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .presenceOfParentCategoryCatalogInSidebar();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
