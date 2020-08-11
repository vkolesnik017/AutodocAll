package PKW.StaticPage;

import PKW.Main_page_Logic;
import PKW.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static PKW.CommonMethods.openPage;
import static PKW.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1609_StaticPage_Uber_uns {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "Test checks elements on the Uber Uns page")
    public void testStaticPage_Uber_uns (String route) {
        openPage(route);
        new Main_page_Logic().clickFooterUberUnsLink()
                .checkElementsOnThePage();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
