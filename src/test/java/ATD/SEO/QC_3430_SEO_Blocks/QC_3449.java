package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_model_page_Logic;
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

public class QC_3449 {

    List<String> expectedTopCarLinks = Arrays.asList("E-Klasse", "A-Klasse", "B-Klasse", "S-Klasse", "SPRINTER");
    List<String> expectedTopCategories = Arrays.asList("Motorkühlung", "Zündanlage & Glühanlage", "Elektrik", "Federung / Dämpfung", "Lenkung", "Radaufhängung & Lenker", "Antriebswellen & Gelenke", "Anhängerkupplung", "Riemen, Ketten, Rollen",
            "Scheibenreinigung", "Kupplung", "Dichtungen und Dichtringe", "Getriebe", "Heizung / Lüftung", "Klimaanlage");
    List<String> expectedTopModels = Arrays.asList("Original Fahrzeugteile MERCEDES-BENZ online", "MERCEDES-BENZ C-Klasse Kfzteile", "Mercedes W203");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_model3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on category model route")
    public void testChecksSeoBlockOnCategoryModelRoute(String route) throws IOException {
        openPage(route);

        new Category_model_page_Logic()
                .checkTitlesOfTopCarLinks(expectedTopCarLinks)
                .checkTitlesOfTopCategoriesLinks(expectedTopCategories)
                .checkTitlesOfTopModelLinks(expectedTopModels);
        new Model_maker_list_page_Logic().checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
