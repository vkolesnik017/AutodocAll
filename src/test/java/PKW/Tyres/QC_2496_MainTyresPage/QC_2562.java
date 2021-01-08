package PKW.Tyres.QC_2496_MainTyresPage;

import PKW.Tyres_page_Logic;
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

public class QC_2562 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new Common.SetUp("PKW").setUpShopWithSubroutes("subprod", "DE", "main_tyres", "tyres,tyres2");  //,tyres3
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking a selection of products in the TOP tires block")
    public void testCheckLinkingBlockByBrands(String route) {
        openPage(route);
        new Tyres_page_Logic().presenceOfTopProductBlock().checkUniquenessOfBrandsInTopProductBlock(3).checkUniqueSizeInTopProductBlock(3);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
