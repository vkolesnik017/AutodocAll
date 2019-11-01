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

public class QASYS_314_StaticPage_agb {
    private Main_page mainPage = new Main_page();
    private Agb_static_page agbStaticPage = new Agb_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkAgbPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickAgb();
        agbStaticPage.logo().shouldBe(visible);
        agbStaticPage.mainBlock().shouldBe(visible);
        agbStaticPage.mailLink().shouldBe(clickable);
        agbStaticPage.atdLink().shouldBe(clickable);
        agbStaticPage.zollLink().shouldBe(clickable);
        agbStaticPage.klarnaLink().shouldBe(clickable);
        agbStaticPage.klarnaLink2().shouldBe(clickable);
        agbStaticPage.euLink().shouldBe(clickable);
        close();
    }
}
