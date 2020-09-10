package MOTO.QC_336_BreadCrumbsBlock;

import ATD.Moto_Product_page_Logic;
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

public class QC_343_StructureOfBreadCrumbsBlockAtProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at product page")
    public void testChecksStructureOfBreadCrumbsBlockAtProductPage(String route) throws SQLException {
        openPage(route);

        new Moto_Product_page_Logic().checkSizeOfBreadCrumbsLinks(4).checkFirstLinkOfBreadCrumbs()
                .checkSecondLinkOfBreadCrumbs("Filter")
                .checkThirdLinkOfBreadCrumbs("Ölfilter")
                .checkFourthLinkOfBreadCrumbs("HifloFiltro Ölfilter");
    }

    @DataProvider(name = "routesProductPage", parallel = true)
    Object[] dataProviderProductPage() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product2");
    }

    @Test(dataProvider = "routesProductPage")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at second product page")
    public void testChecksStructureOfBreadCrumbsBlockAtSecondProductPage(String route) throws SQLException {
        openPage(route);

        new Moto_Product_page_Logic().checkSizeOfBreadCrumbsLinks(2).checkFirstLinkOfBreadCrumbs()
                .checkSecondLinkOfBreadCrumbsWithOutSubRoute("CONTITECH Keilrippenriemen");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
