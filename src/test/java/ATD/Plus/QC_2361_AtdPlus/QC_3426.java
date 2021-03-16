package ATD.Plus.QC_2361_AtdPlus;

import ATD.Main_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3426 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the Autodoc Plus section buttons in the Personal Area of a user without a subscription")
    public void testCheckAutodocPlusSectionButton() throws SQLException {
        String mail = "qc_3426_autotest@mailinator.com";
        openPage(new DataBase("ATD").getFullRouteByRouteName("prod","DE", "main"));
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .checkPriceSwitchingOnSubscriptionPacks()
                .checkFunctionOfPackActivationButton();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}

