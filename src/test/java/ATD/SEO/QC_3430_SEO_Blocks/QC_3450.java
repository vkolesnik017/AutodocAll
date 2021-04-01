package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_car_list_page_Logic;
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

public class QC_3450 {
    List<String> expectedTopCategoriesLinks = Arrays.asList("Keilrippenriemen für VW GOLF", "Keilrippenriemen für BMW 3er", "Keilrippenriemen für OPEL ASTRA", "Keilrippenriemen für AUDI A4",
            "Keilrippenriemen für VW PASSAT", "Keilrippenriemen für VW POLO",
            "Keilrippenriemen für C-Klasse Limousine (W204)", "Keilrippenriemen für C-Klasse T-modell (S204)", "Keilrippenriemen für C-Klasse T-modell (S203)", "Keilrippenriemen für C-Klasse Limousine (W202)",
            "Keilrippenriemen für C-Klasse T-modell (S205)",
            "Zündkerzen für MERCEDES-BENZ C-Klasse", "Bremsbeläge für MERCEDES-BENZ C-Klasse", "Ölfilter für MERCEDES-BENZ C-Klasse", "Scheibenwischer für MERCEDES-BENZ C-Klasse",
            "Bremsscheiben für MERCEDES-BENZ C-Klasse", "Luftfilter für MERCEDES-BENZ C-Klasse", "Innenraumfilter für MERCEDES-BENZ C-Klasse", "Kofferraum für MERCEDES-BENZ C-Klasse",
            "Heckklappendämpfer für MERCEDES-BENZ C-Klasse", "Radschrauben und Radmuttern für MERCEDES-BENZ C-Klasse");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list77");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category car list route")
    public void testChecksSeoBlockOnCategoryCarListRoute(String route) throws IOException {
        openPage(route);

        new Category_car_list_page_Logic()
                .checkTitlesOfTopCarLinks(expectedTopCategoriesLinks);
        new Model_maker_list_page_Logic().checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
