package ATD.MOTO.QC_336_BreadCrumbsBlock;

import Common.DataBase;
import ATD.Moto_Catalog_model_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_339_StructureOfBreadCrumbsBlockAtCatalogModels {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog_model2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at catalog models route")
    public void testChecksStructureOfBreadCrumbsBlockAtCatalogModels(String route) throws SQLException {
        openPage(route);

        new Moto_Catalog_model_page_Logic().checkBreadCrumbsBlock();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_categories_maker3"));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
