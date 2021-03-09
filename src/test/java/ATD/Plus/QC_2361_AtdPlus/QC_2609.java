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

public class QC_2609 {

    private String email = "qc_2609_autotest@mailinator.com";
    private Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "EE,SI,LV,LT", "main");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking for the absence of SO in the Autodoc Plus section on language versions where it is not included")
    public void testCheckingCheckingForAbsenceSOInAutodocPlusSection(String route) {
        openPage(route);


        mainPage.clickOnAutodocPlusInHeader()
                .presenceOfServicePackages()
                .absenceOfSoOption()
                .absenceOfSoImageBlock();
        mainPage.loginFromHeader(email);
        mainPage.clickOnMainLogo()
                .profileBtnClickInHeader()
                .goToPageAutodocPlus()
                .absenceOfSoOption();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
