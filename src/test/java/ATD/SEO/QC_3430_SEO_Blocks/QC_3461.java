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

public class QC_3461 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Bremsscheiben für MERCEDES-BENZ C-Klasse Limousine (W203)", "Kühler für MERCEDES-BENZ C-Klasse Limousine (W203)",
            "Stoßdämpfer für MERCEDES-BENZ C-Klasse Limousine (W203)", "Scheibenwischer für MERCEDES-BENZ C-Klasse Limousine (W203)", "Zündkerzen für MERCEDES-BENZ C-Klasse Limousine (W203)",
            "Ölfilter für MERCEDES-BENZ C-Klasse Limousine (W203)", "Blinker für MERCEDES-BENZ C-Klasse Limousine (W203)", "Radlager für MERCEDES-BENZ C-Klasse Limousine (W203)",
            "Spurstangenkopf für MERCEDES-BENZ C-Klasse Limousine (W203)", "Kraftstoffpumpe für MERCEDES-BENZ C-Klasse Limousine (W203)", "Scheinwerfer für MERCEDES-BENZ C-Klasse Limousine (W203)", "Luftfilter für MERCEDES-BENZ C-Klasse Limousine (W203)");
    List<String> expectedTopCarLinks = Arrays.asList("Fahrzeugteile für VW", "Fahrzeugteile für OPEL", "Fahrzeugteile für BMW", "Fahrzeugteile für AUDI", "Fahrzeugteile für FORD", "Fahrzeugteile für RENAULT");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "model_maker_list_year2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Model maker list year route")
    public void testChecksSeoBlockModelMakerListYearRoute(String route) throws IOException {
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
