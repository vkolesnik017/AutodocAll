package ATD.SEO.QC_3430_SEO_Blocks;

import ATD.Categories_maker_page_Logic;
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

public class QC_3454 {

    List<String> expectedTopCategoriesLinks = Arrays.asList("MERCEDES-BENZ ML-Klasse (W163) ML 270 CDI 2.7 (163.113)", "MERCEDES-BENZ A-Klasse (W169) A 180 CDI 2.0 (169.007, 169.307)",
            "MERCEDES-BENZ B-Klasse (W245) B 180 CDI 2.0 (245.207)", "MERCEDES-BENZ E-Klasse Limousine (W211) E 220 CDI 2.2 (211.006)", "MERCEDES-BENZ C-Klasse Limousine (W203) C 220 CDI 2.2 (203.006)",
            "MERCEDES-BENZ ML-Klasse (W164) ML 320 CDI 3.0 4-matic (164.122)", "MERCEDES-BENZ A-Klasse (W168) A 140 1.4 (168.031, 168.131)");

    List<String> expectedTitlesOfSeoBlock = Arrays.asList("Teile für Ihren MERCEDES-BENZ", "Mercedes-Benz Autos", "Top Mercedes-Benz Modelle", "Vorteile von Mercedes-Benz Autos",
            "Die besten Autoteile für Mercedes-Benz");

    List<String> expectedTitleLinksOfSeoBlock = Arrays.asList("MERCEDES-BENZ C-Klasse Limousine (W203)", "MERCEDES-BENZ C-Klasse Limousine (W204)", "MERCEDES-BENZ E-Klasse Limousine (W211)",
            "MERCEDES-BENZ A-Klasse (W169)", "MERCEDES-BENZ B-Klasse (W245)", "MERCEDES-BENZ A-Klasse (W168)", "MERCEDES-BENZ C-Klasse T-modell (S204)",
            "MERCEDES-BENZ C-Klasse T-modell (S203)", "MERCEDES-BENZ E-Klasse Limousine (W212)", "MERCEDES-BENZ 190 (W201)");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Categories maker route")
    public void testChecksSeoBlockOnCategoriesMakerRoute(String route) throws IOException {
        openPage(route);

        new Categories_maker_page_Logic()
                .checkTitlesOfSeoBlock(expectedTitlesOfSeoBlock)
                .checkTitleLinksOfSeoBlock(expectedTitleLinksOfSeoBlock);
        new Model_maker_list_page_Logic()
                .checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
