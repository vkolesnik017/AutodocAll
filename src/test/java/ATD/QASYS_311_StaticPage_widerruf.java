package ATD;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.clickable;
import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_311_StaticPage_widerruf {
    private Main_page mainPage = new Main_page();
    private Widerruf_static_page widerrufStaticPage = new Widerruf_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkWiderrufPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickWiderruf();
        widerrufStaticPage.title().shouldBe(visible);
        widerrufStaticPage.logo().shouldBe(visible);
        widerrufStaticPage.mainBlock().shouldBe(visible);
        widerrufStaticPage.emailLink1().shouldBe(clickable);
        widerrufStaticPage.emailLink2().shouldBe(clickable);
        close();
    }
}
