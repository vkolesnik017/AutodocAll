package MOTO.QC_336_BreadCrumbsBlock;

import ATD.Moto_Category_maker_page_Logic;
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

public class QC_345_StructureOfBreadCrumbsBlockAtCategoryAndBrand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks structure of bread crumbs block at category and brand")
    public void testChecksStructureOfBreadCrumbsBlockAtCategoryAndBrand(String route) throws SQLException {
        openPage(route);

        new Moto_Category_maker_page_Logic().checkBreadCrumbsBlock();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
