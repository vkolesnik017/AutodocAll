package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Model_maker_list_page_Logic;
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

public class QC_3447 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Bremsanlage für VW", "Bremsanlage für OPEL", "Bremsanlage für BMW", "Bremsanlage für AUDI", "Bremsanlage für FORD", "Bremsanlage für RENAULT",
            "Ersatzteile für Auto Webshop AutoDoc", "MERCEDES-BENZ Ersatzteile für Auto");

    List<String> expectedSecondTopCategoriesLinks = Arrays.asList("Keilrippenriemen für VW", "Keilrippenriemen für OPEL", "Keilrippenriemen für BMW", "Keilrippenriemen für AUDI", "Keilrippenriemen für FORD",
            "Keilrippenriemen für RENAULT",
            "Auto-Teile Verkaufsplattform AutoDoc", "MERCEDES-BENZ Auto-Ersatzteile");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker9");

        return new Object[][]{{"https://www.autodoc.de/autoteile/bremsanlage/mercedes-benz"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category maker route")
    public void testChecksSeoBlockOnCategoryMakerRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @DataProvider(name = "routesSecond", parallel = true)
    Object[] dataProviderSecond() throws SQLException {
        // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker");  // ПРОСТАВИТЬ НУЖНЫЙ РУТ ИЗ БД
        return new Object[][]{{"https://www.autodoc.de/autoteile/keilrippenriemen-10531/mercedes-benz"}};
    }

    @Test(dataProvider = "routesSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category maker second route")
    public void testChecksSeoBlockOnCategoryMakerRouteSecond(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedSecondTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
