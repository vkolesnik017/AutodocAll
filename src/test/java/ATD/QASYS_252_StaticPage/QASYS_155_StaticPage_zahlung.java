package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.SetUp;
import ATD.Zahlung_static_page;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_155_StaticPage_zahlung {
    private Main_page mainPage = new Main_page();
    private Zahlung_static_page zahlungStaticPage = new Zahlung_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Owner(value = "Oleg Romanyuta")
    @Test(dataProvider = "route")
    @Flaky
    public void checkZahlungPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickZahlung();
        zahlungStaticPage.logo().shouldBe(visible);
        zahlungStaticPage.title().shouldBe(visible);
        zahlungStaticPage.checkMainBlock().shouldBe(visible);
    }
}
