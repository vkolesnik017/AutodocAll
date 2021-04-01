package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Model_maker_list_page_Logic;
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

public class QC_3451 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("GATES Keilrippenriemen für MERCEDES-BENZ", "GATES Keilrippenriemen für OPEL", "GATES Keilrippenriemen für BMW",
            "GATES Keilrippenriemen für AUDI", "GATES Keilrippenriemen für FORD", "GATES Keilrippenriemen für RENAULT");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker_brand8");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category maker brand route")
    public void testChecksSeoBlockOnCategoryMakerBrandRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic()
                .checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
