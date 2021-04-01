package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Model_maker_list_year_page_Logic;
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

public class QC_3460 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Bremsscheiben für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Kühler für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen",
            "Stoßdämpfer für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Scheibenwischer für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen",
            "Zündkerzen für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Ölfilter für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen",
            "Blinker für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Radlager für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Spurstangenkopf für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen",
            "Kraftstoffpumpe für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen", "Scheinwerfer für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen",
            "Luftfilter für MERCEDES-BENZ C-Klasse Limousine (W203) bestellen");
    List<String> expectedTopCarLinks = Arrays.asList("KFZ-Ersatzteile für VW", "KFZ-Ersatzteile für OPEL", "KFZ-Ersatzteile für BMW", "KFZ-Ersatzteile für AUDI", "KFZ-Ersatzteile für FORD", "KFZ-Ersatzteile für RENAULT");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "model_maker_list_hp3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Model maker list hp route")
    public void testChecksSeoBlockModelMakerListHpRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_year_page_Logic().checkTitlesOfTopCarLinks(expectedTopCarLinks)
                .checkTransitionOfTopAutoLinks()
                .checkTitlesOfTopCategoriesLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopCategoriesLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
