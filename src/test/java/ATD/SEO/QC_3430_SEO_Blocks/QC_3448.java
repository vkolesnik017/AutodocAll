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

public class QC_3448 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Bremsanlage für MERCEDES-BENZ E-Klasse", "Bremsanlage für MERCEDES-BENZ A-Klasse", "Bremsanlage für MERCEDES-BENZ B-Klasse",
            "Bremsanlage für MERCEDES-BENZ SPRINTER", "Bremsanlage für MERCEDES-BENZ S-Klasse",
            "Bremsanlage für VW", "Bremsanlage für OPEL", "Bremsanlage für BMW", "Bremsanlage für AUDI", "Bremsanlage für FORD",
            "Motorkühlung", "Zündanlage & Glühanlage", "Elektrik", "Federung / Dämpfung", "Lenkung", "Radaufhängung & Lenker", "Antriebswellen & Gelenke", "Anhängerkupplung", "Riemen, Ketten, Rollen",
            "Scheibenreinigung", "Kupplung", "Dichtungen und Dichtringe", "Getriebe", "Heizung / Lüftung", "Klimaanlage",
            "Kfz Teile MERCEDES-BENZ billig", "Auto-Teile C-Klasse");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_group4");

        return new Object[][]{{"https://www.autodoc.de/autoteile/bremsanlage/mercedes-benz/c-klasse"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category group route")
    public void testChecksSeoBlockOnCategoryGroupRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
