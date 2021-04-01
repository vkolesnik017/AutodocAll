package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_group_brand_page_Logic;
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

public class QC_3452 {

    List<String> expectedTopModelsLinks = Arrays.asList("GATES Keilrippenriemen für PASSAT", "GATES Keilrippenriemen für POLO", "GATES Keilrippenriemen für TRANSPORTER", "GATES Keilrippenriemen für TOURAN",
            "GATES Keilrippenriemen für TIGUAN", "GATES Keilrippenriemen für CADDY");

    List<String> expectedTitlesOfEnginePartsBlock = Arrays.asList("Volkswagen GOLF Zahnriemensatz von GATES", "Volkswagen GOLF Wasserpumpe + Zahnriemensatz von GATES", "Volkswagen GOLF Zahnriemen von GATES",
            "Volkswagen GOLF Spannrolle, Zahnriemen von GATES", "Volkswagen GOLF Umlenk- / Führungsrolle, Zahnriemen von GATES", "Volkswagen GOLF Schwingungsdämpfer, Zahnriemen von GATES",
            "Volkswagen GOLF Riemenspanner, Keilrippenriemen von GATES", "Volkswagen GOLF Spannrolle von GATES");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_group_brand6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category group brand list route")
    public void testChecksSeoBlockOnCategoryGroupBrandRoute(String route) throws IOException {
        openPage(route);
        new Category_group_brand_page_Logic()
                .displayHeadlineOfOtherEnginePartsBlock("ANDERE MOTOR ERSATZTEILE VON GATES FÜR VW GOLF")
                .checkTitlesOfOtherEnginePartsBlock(expectedTitlesOfEnginePartsBlock);
        new Model_maker_list_page_Logic()
                .checkTitlesOfTopCarLinks(expectedTopModelsLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
