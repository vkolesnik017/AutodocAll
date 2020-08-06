package MOTO.QC_336_BreadCrumbsBlock;

import ATD.Moto_Catalog_page_Logic;
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

public class QC_340_StructureOfBreadCrumbsBlockAtTecDocCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_catalog2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at TecDoc сatalog route")
    public void testChecksStructureOfBreadCrumbsBlockAtTecDocCatalog(String route) throws SQLException {
        openPage(route);

        new Moto_Catalog_page_Logic().checkBreadCrumbsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
