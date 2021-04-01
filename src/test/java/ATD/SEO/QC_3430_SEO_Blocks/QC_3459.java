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

public class QC_3459 {
    List<String> expectedTopCategoriesLinks = Arrays.asList("Teile für MERCEDES-BENZ E-Klasse günstig kaufen", "Teile für MERCEDES-BENZ A-Klasse günstig kaufen", "Teile für MERCEDES-BENZ B-Klasse günstig kaufen",
            "Teile für MERCEDES-BENZ SPRINTER günstig kaufen",
            "MERCEDES-BENZ E-Klasse Limousine (W211)", "MERCEDES-BENZ A-Klasse (W169)", "MERCEDES-BENZ B-Klasse (W245)", "MERCEDES-BENZ A-Klasse (W168)",
            "MERCEDES-BENZ ML-Klasse (W163) ML 270 CDI 2.7 (163.113)", "MERCEDES-BENZ A-Klasse (W169) A 180 CDI 2.0 (169.007, 169.307)", "MERCEDES-BENZ B-Klasse (W245) B 180 CDI 2.0 (245.207)",
            "MERCEDES-BENZ E-Klasse Limousine (W211) E 220 CDI 2.2 (211.006)");


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "model_maker_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks SEO block on Model maker list hp route")
    public void testChecksSeoBlockOnModelMakerListHpRoute(String route) throws IOException {
        openPage(route);
        new Model_maker_list_page_Logic().checkTitlesOfTopCarLinks(expectedTopCategoriesLinks)
                .checkTransitionOfTopAutoLinks();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
