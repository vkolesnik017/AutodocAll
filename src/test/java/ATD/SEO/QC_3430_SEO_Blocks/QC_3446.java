package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_name_page_Logic;
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

public class QC_3446 {

    List<String> expectedTitlesOfSummaryTable = Arrays.asList("Die meistverkauften Artikel:", "Von beliebten Herstellern:", "Für beliebte Automarken:", "Keilrippenriemen – Technische Daten:");
    List<String> expectedTitlesOfSeoBlock = Arrays.asList("Erfahre mehr über Keilrippenriemen — Die Technik, Fehlerdiagnose sowie Ursachen und Wechselintervalle", "Keilrippenriemenarten", "Der Aufbau des Keilrippenriemens",
            "Nutzungstipps des Keilrippenriemens", "Ursachen eines Versagens des Keilrippenriemens", "Anzeichen eines Versagens des Keilrippenriemens",
            "Ursachen eines Versagens des Keilrippenriemens", "Diagnose", "Austausch des Keilrippenriemens", "Warum kaufen Sie besten Autoteile von uns?");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name15");
        return new Object[][]{{"https://www.autodoc.de/autoteile/keilrippenriemen-10531"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Category name route")
    public void testChecksSeoBlockOnCategoryNameRoute(String route) {
        openPage(route);

        new Category_name_page_Logic().displayHeadlineOfSummaryTable()
                .checkTitlesOfSummaryTable(expectedTitlesOfSummaryTable)
                .checkHeadlinesOfSeoText(expectedTitlesOfSeoBlock);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
