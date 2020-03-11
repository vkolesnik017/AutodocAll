package LKW_trucks;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;

public class QC_134_TopParentAndChildCategoriesBlocks {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks elements in Top parent and child category blocks")
    public void testChecksTopParentAndChildCategoryBlocksElements(String route) {
        openPage(route);

        new LKW_Categories_maker_page_Logic()
                .checkingOfElementsInParentAndChildBlocks()
                .selectChildCategoryInTopCategoryBlock()
                .checkSuccessfullyCategoryMakerPageLoading();
    }
}