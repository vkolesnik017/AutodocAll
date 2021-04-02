package ATD.SEO.QC_3430_SEO_Blocks;

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

public class QC_3431 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Bremsanlage für VW", "Bremsanlage für OPEL", "Bremsanlage für BMW", "Bremsanlage für AUDI", "Bremsanlage für FORD", "Bremsanlage für RENAULT",
            "Ersatzteile für Auto Webshop AutoDoc", "MERCEDES-BENZ Ersatzteile für Auto");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
       // return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker");  // ПРОСТАВИТЬ НУЖНЫЙ РУТ ИЗ БД
        return new Object[][] {{"https://www.autodoc.de/autoteile/bremsanlage/mercedes-benz"}};
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Сategory maker route")
    public void testChecksSeoBlockOnСategoryMakerRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
