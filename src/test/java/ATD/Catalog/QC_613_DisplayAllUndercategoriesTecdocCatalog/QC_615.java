package ATD.Catalog.QC_613_DisplayAllUndercategoriesTecdocCatalog;

import ATD.FaqHash_page_Logic;
import Common.SetUp;
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

public class QC_615 {

    private FaqHash_page_Logic faqPage = new FaqHash_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "faqHash");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check Displaying subcategories on the root of the Faq and the catalog without vehicle")
    public void testCheckPresenceSubcategoriesOnFaqRootAndCatalogWithoutVehicle(String route) throws Exception {
        openPage(route);
        List<String> notUsedCategories = Arrays.asList("Reifen", "Autozubehör", "Steuergeräte, Sensoren, Relais", "Reparatursätze", "Werkzeuge & Werkstattausrüstung", "Autopflege",
                "Rohre und Schläuche", "Beleuchtung", "Befestigungsmaterial");
        faqPage.compareSubCategoriesListWithAws(notUsedCategories);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
