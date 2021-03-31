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

public class QC_3457 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("Automobilteile für MERCEDES-BENZ E-Klasse billig", "Automobilteile für MERCEDES-BENZ A-Klasse billig", "Automobilteile für MERCEDES-BENZ M-Klasse billig",
            "Automobilteile für MERCEDES-BENZ VITO billig", "Automobilteile für MERCEDES-BENZ SPRINTER billig", "Automobilteile für MERCEDES-BENZ B-Klasse billig",
            "Bremsscheiben für C-Klasse preisgünstig", "Kühler für C-Klasse preisgünstig", "Stoßdämpfer für C-Klasse preisgünstig", "Scheibenwischer für C-Klasse preisgünstig",
            "Zündkerzen für C-Klasse preisgünstig", "Ölfilter für C-Klasse preisgünstig", "Blinker für C-Klasse preisgünstig", "Radlager für C-Klasse preisgünstig",
            "Spurstangenkopf für C-Klasse preisgünstig", "Kraftstoffpumpe für C-Klasse preisgünstig", "Scheinwerfer für C-Klasse preisgünstig", "Luftfilter für C-Klasse preisgünstig");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "group_list_body3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Group list body  route")
    public void testChecksSeoBlockOnGroupListBodyRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
