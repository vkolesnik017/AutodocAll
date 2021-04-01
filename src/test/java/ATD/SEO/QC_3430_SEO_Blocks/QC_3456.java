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

public class QC_3456 {
    List<String> expectedTopCategoriesLinks = Arrays.asList("Pkwteile für E-Klasse", "Pkwteile für A-Klasse", "Pkwteile für M-Klasse", "Pkwteile für VITO", "Pkwteile für SPRINTER", "Pkwteile für B-Klasse",
            "Bremsscheiben für MERCEDES-BENZ C-Klasse", "Kühler für MERCEDES-BENZ C-Klasse", "Stoßdämpfer für MERCEDES-BENZ C-Klasse", "Scheibenwischer für MERCEDES-BENZ C-Klasse",
            "Zündkerzen für MERCEDES-BENZ C-Klasse", "Ölfilter für MERCEDES-BENZ C-Klasse", "Blinker für MERCEDES-BENZ C-Klasse", "Radlager für MERCEDES-BENZ C-Klasse", "Spurstangenkopf für MERCEDES-BENZ C-Klasse",
            "Kraftstoffpumpe für MERCEDES-BENZ C-Klasse", "Scheinwerfer für MERCEDES-BENZ C-Klasse", "Luftfilter für MERCEDES-BENZ C-Klasse");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "group_list_drive2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Group list drive  route")
    public void testChecksSeoBlockOnGroupListDriveRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
