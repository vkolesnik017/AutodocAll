package ATD.Search.QC_548_SearchTooltips;

import ATD.LKW_main_page_Logic;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2541 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the sorting of values in the search string by the entered value")
    public void testChecksIfHintsInTheSearchFieldMatchByValue(String route) {
        open(route);
        new Main_page_Logic().checksIfHintsInTheSearchFieldMatchByValue();
    }


    @DataProvider(name = "routeLkw")
    Object[] dataProviderLkw() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "DE", "lkw_main");
    }

    @Test(dataProvider = "routeLkw")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the sorting of values in the search string by the entered value")
    public void testLkw_ChecksIfHintsInTheSearchFieldMatchByValue(String route) {
        open(route);
        new LKW_main_page_Logic().checksIfHintsInTheSearchFieldMatchByValue();
    }


    @DataProvider(name = "routeMoto")
    Object[] dataProviderMoto() {
        return new SetUp("ATD").setUpShopsWithMainRoute("subprod", "DE", "moto_main");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the sorting of values in the search string by the entered value")
    public void testMoto_ChecksIfHintsInTheSearchFieldMatchByValue(String route) {
        open(route);
        new Main_page_Logic().checksIfHintsInTheSearchFieldMatchByValue();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
