package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;

import ATD.Product_page_Logic;
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

public class QC_3217_StructureOfBreadcrumbsOnProductPage {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "main", "product63");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check structure of bread crumbs on product page")
    public void testCheckStructureOfBreadcrumbsOnProductPage(String route) throws SQLException {
        openPage(route);
        new Product_page_Logic().sizeOfBreadCrumbsLinks(4)
                .closeMarkeFieldToolTip()
                .checkTransitionsOfBreadcrumbsLinks("Öle & Flüssigkeiten", "Motoröl");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
