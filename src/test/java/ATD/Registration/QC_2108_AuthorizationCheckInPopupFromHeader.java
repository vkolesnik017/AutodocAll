package ATD.Registration;

import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2108_AuthorizationCheckInPopupFromHeader {

    private String mail = "QC_2108_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Owner(value = "Sergey_QA")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Checking Authorization (login) In Popup From Header")
    public void testAuthorizationCheckInPopupFromHeader(String route) {
        openPage(route);
        new Main_page_Logic().clickCheckboxRememberMeFromLoginPopup(mail);
        waitingWhileLinkBecomeExpected("https://www.autodoc.de/profile/services/plus-service");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
