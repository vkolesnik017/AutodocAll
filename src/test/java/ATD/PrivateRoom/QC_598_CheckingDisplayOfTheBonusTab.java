package ATD.PrivateRoom;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.title;

public class QC_598_CheckingDisplayOfTheBonusTab {

    private String mail = "QC_914_bonusTestATD@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks the display of the Bonus tab")
    public void testCheckingDisplayOfTheBonusTab(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Main_page_Logic().loginAndTransitionToProfilePlusPage(mail)
                .goToBonusSystemPage()
                .checkCurrencyAccruedBonusInTable(shop);
        checkingContainsUrl("/profile/bonus");
        String metaTitle = title();
        Assert.assertEquals(metaTitle, "Bonus");
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}