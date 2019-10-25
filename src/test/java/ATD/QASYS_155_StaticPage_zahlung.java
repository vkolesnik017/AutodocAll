package ATD;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
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
        return new CommonMethods().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkZahlungPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickZahlung();
        zahlungStaticPage.logo().shouldBe(visible);
        zahlungStaticPage.title().shouldBe(visible);
        zahlungStaticPage.checkMainBlock().shouldBe(visible);
        close();
    }
}
