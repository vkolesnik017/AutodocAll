package ATD.SEO.QC_3430_SEO_Blocks;

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

public class QC_3472 {
    List<String> expectedAutoLinks = Arrays.asList("LKW Ansaugkrümmerdichtung MAN", "LKW Ansaugkrümmerdichtung RENAULT TRUCKS");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("test", "DE", "lkw_main", "lkw_category_maker6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on LKW_category_maker route")
    public void testChecksSeoBlockLKWCategoryMakerRoute(String route) throws IOException {
        openPage(route);
        new LKW_Category_maker_Logic().checkTitlesOfTopAutoLinks(expectedAutoLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
