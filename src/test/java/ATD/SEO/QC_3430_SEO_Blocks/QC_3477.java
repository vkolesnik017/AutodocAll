package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Category_maker_page_Logic;
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

public class QC_3477 {
    List<String> expectedAutoLinks = Arrays.asList("Motoröl für VW", "Motoröl für MERCEDES-BENZ", "Motoröl für BMW", "Motoröl für AUDI", "Motoröl für FORD", "Motoröl für RENAULT");
    List<String> expectedCarPartsForLinks = Arrays.asList("Teile für Auto Onlinestore AutoDoc", "OPEL Ersatzteile für Auto");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_maker8");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block In Motoroil_maker route")
    public void testChecksSeoBlockInMotoroilMakerRoute(String route) throws IOException {
        openPage(route);
        new Category_maker_page_Logic().checkTitlesOfTopAutoLinks(expectedAutoLinks)
                .checkTransitionOfTopAutoLinks()
                .checkTitlesOfCarPartsForLinks(expectedCarPartsForLinks)
                .checkTransitionOfCarPartsForLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
