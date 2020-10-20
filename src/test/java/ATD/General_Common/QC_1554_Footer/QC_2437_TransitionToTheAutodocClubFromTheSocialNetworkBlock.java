package ATD.General_Common.QC_1554_Footer;

import ATD.Main_page_Logic;
import Common.DataBase;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2437_TransitionToTheAutodocClubFromTheSocialNetworkBlock {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShopsWithMainRoute("prod", "AT,BG,BE,CZ,DE,DK,EE,ES,FI,FR,EN,GR,HU,IT,LD,LT,LV,NL,NO,PL,PT,RO,SE,SI,SK","main");
    }

    @Flaky
    @Owner(value = "LavrynenkoOlha")
    @Test(dataProvider = "route")
    @Description(value = "Test check the transition to the Autodoc club in the Social Network Block")
    public void checkingTransitionInTheSocialNetworksBlock(String route) throws SQLException {
        openPage(route);
        String shop = getCurrentShopFromJSVarInHTML();
        new Main_page_Logic().checkingTransitionToTheAutodocClub();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName(shop, "club_main"));
        back();
        new Main_page_Logic().checkingTransitionToTheAutodocClubLink();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName(shop, "club_main"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
