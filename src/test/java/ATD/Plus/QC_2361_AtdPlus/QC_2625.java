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

public class QC_2625 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "DE", "main");
    }


    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checking buttons of landing Autodoc plus page")
    public void testCheckButtonsOfAutodocPlusPage(String route) {
        openPage(route);
        new Main_page_Logic().clickOnAutodocPlusInHeader()
                .setMonthlyPrice()
                .clickOnBtnMoreOfGeneralConditionBlock()
                .presenceOfCancellationForm(true)
                .clickOnBtnMoreOfGeneralConditionBlock()
                .presenceOfCancellationForm(false)
                .clickOnActiveBasicPackage()
                .presenceOfRegistrationPopUp();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
