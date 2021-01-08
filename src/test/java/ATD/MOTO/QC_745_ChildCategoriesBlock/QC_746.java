package ATD.MOTO.QC_745_ChildCategoriesBlock;

import ATD.Moto_Parent_Category_maker_page_Logic;
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

public class QC_746 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category");

    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of child categories block")
    public void testChecksHeadlineOfChildCategoriesBlock(String route)  {
        openPage(route);

        new Moto_Parent_Category_page_Logic()
                .visibilityOfHeadlineAtChildCategoryBlock("Wählen Sie die benötigten Motorrad Filter Ersatzteile aus");
    }


    @DataProvider(name = "routesCategoryMaker", parallel = true)
    Object[] dataProviderCategoryMaker() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_parent_category_maker2");

    }

    @Test(dataProvider = "routesCategoryMaker")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks headline of child categories block")
    public void testChecksHeadlineOfChildCategoriesBlockCategoryMaker(String route)  {
        openPage(route);

        new Moto_Parent_Category_maker_page_Logic()
                .visibilityOfHeadlineAtChildCategoryBlock("Wählen Sie die benötigten BMW Filter Ersatzteile aus");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
