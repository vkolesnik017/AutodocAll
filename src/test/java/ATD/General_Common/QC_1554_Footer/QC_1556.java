package ATD.General_Common.QC_1554_Footer;

import ATD.Main_page_Logic;
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

public class QC_1556 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0",false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {

        //return new SetUp("ATD").setUpShop("prod", "DE");
        return new Object[][]{{"https://partners.autodoc.eu/en/signup"}};
    }

    @Flaky
    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Description(value = "Test transition the link and check url")
    public void checkingLinksInFooter(String route) throws SQLException {
        openPage(route);
        new Main_page_Logic().setValue();
    }
    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
