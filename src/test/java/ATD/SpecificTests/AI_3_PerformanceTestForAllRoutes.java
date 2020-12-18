package ATD.SpecificTests;

import Common.*;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class AI_3_PerformanceTestForAllRoutes {
    PageLoadTime pageLoadTime = new PageLoadTime();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = false)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "routes")
    @Owner(value = "LavrynenkoOlha")
    @Description(value = "The test shows the page load time, write result in Excel and reports by email and Slack, when the average page load time is exceeded")
    public void testCheckingTheLoadPageTime(String route) throws Exception {
        openPage(route);
        pageLoadTime.pageLoadingTime(1, 30,"https://hooks.slack.com/services/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");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
