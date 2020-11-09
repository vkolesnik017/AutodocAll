package ATD.Plus.QC_2361_AtdPlus;

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

public class QC_2612_CheckingForAbsenceSOInAutodocPlusSection {
    private String email = "QC_2612_autotest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "EE,SI,LV,LT", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking for the absence of SO in the Autodoc Plus section in the LC on language versions where it is not included")
    public void testCheckingCheckingForAbsenceSOInAutodocPlusSection(String route) {
        openPage(route);
        new Main_page_Logic().loginFromHeader(email).goToPageAutodocPlus().presenceReadyToSendSticker().presencePriorityOrderStatus(1).absenceSOBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
