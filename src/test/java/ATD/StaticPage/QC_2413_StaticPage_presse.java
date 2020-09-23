package ATD.StaticPage;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2413_StaticPage_presse {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks elements and functionality on the Presse page")
    public void checkPressePageElements(String route) {
        openPage(route);
        new Main_page_Logic().goToPressePage()
                .openTheArticleBlock()
                .checkingTheTitles();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
