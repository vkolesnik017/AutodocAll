package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.LKW_Categories_maker_page_Logic;
import ATD.LKW_Category_maker_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3470 {
    List<String> expectedAutoLinks = Arrays.asList("LKW Ersatzteile für MAN", "LKW Ersatzteile für VOLVO", "LKW Ersatzteile für RENAULT TRUCKS", "LKW Ersatzteile für DAF", "LKW Ersatzteile für IVECO", "LKW Ersatzteile für SCANIA");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_categories_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on LKW_categories_maker route")
    public void testChecksSeoBlockOnLKWCategoriesMakerRoute(String route) throws SQLException, IOException {
        openPage(route);

        new LKW_Categories_maker_page_Logic().checkSeoTextBlock("lkw_categories_maker_SeoText");
        new LKW_Category_maker_Logic().checkTitlesOfTopAutoLinks(expectedAutoLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
