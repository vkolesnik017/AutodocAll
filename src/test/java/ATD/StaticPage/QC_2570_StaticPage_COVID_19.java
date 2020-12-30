package ATD.StaticPage;

import ATD.COVID_Static_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2570_StaticPage_COVID_19 {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "static_page_atd_COVID");
    }

    @Test(dataProvider = "route")
    @Owner(value = "LavrynenkoOlha")
    @Flaky
    @Description(value = "Test checks elements on the COVID-19 page")
    public void checkCOVIDPageElements(String route) {
        openPage(route);
        new COVID_Static_page_Logic().checkTitleOnThePage()
                .checkingAnswersAndQuestions();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}

