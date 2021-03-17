package ATD.Breadcrumbs.QC_2683_BreadcrumbsBlock;


import ATD.Supplier_brand_line_page_Logic;
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
import static com.codeborne.selenide.Selenide.*;


public class QC_3087 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "supplier_brand_line");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test check structure of bread crumbs on root brand + line")
    public void testCheckStructureOfBreadcrumbsOnRootBrandAndLine(String route) {
        openPage(route);
        new Supplier_brand_line_page_Logic().checkStructureOfBreadCrumbs();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
