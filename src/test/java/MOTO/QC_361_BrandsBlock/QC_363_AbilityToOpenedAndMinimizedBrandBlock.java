package MOTO.QC_361_BrandsBlock;

import ATD.Moto_Category_maker_page_Logic;
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
import static com.codeborne.selenide.Selenide.close;

public class QC_363_AbilityToOpenedAndMinimizedBrandBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_maker3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks ability to opened/minimized brand block")
    public void testChecksAbilityToOpenedAndMinimizedBrandBlock(String route) {
        openPage(route);

        new Moto_Category_maker_page_Logic()
                .openBrandsBlock()
                .minimizedBrandsBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}