package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_name_parent_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3445 {

    List<String> expectedTitlesOfSeoBlock = Arrays.asList("Konstruktion des Bremssystems", "Tipps für die Wartung", "Anzeichen für Defekte", "Ursachen für Defekte", "Autodoc.de: ausschließlich qualitative Autoteile");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name_parent");  // ПРОСТАВИТЬ НУЖНЫЙ РУТ ИЗ БД
        return new Object[][]{{"https://www.autodoc.de/autoteile/bremsanlage"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category name route")
    public void testChecksSeoBlockOnCategoryNameRoute(String route) {
        openPage(route);

        new Category_name_parent_page_Logic()
                .displayHeadlineOfSeoBlock("Kfz-Bremsen Ersatzteile — Alle wichtigen Informationen zur Technik, Montage, Diagnose und Lebensdauer")
                .checkHeadlinesOfSeoText(expectedTitlesOfSeoBlock);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
