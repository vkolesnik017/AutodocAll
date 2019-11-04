package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.SetUp;
import ATD.Sponsorship_static_page;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_353_StaticPage_sponsorship {
    private Main_page mainPage = new Main_page();
    private Sponsorship_static_page sponsorshipStaticPage = new Sponsorship_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    public void checkSponsorshipPgeElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickSponsorship();
        sponsorshipStaticPage.sponsorsHeader().shouldBe(visible);
        sponsorshipStaticPage.raceGallery().shouldBe(visible);
        sponsorshipStaticPage.mapText().shouldBe(visible);
        sponsorshipStaticPage.sendShipForm().shouldBe(visible);
        sponsorshipStaticPage.submitShipDataButton().shouldBe(visible);
        sponsorshipStaticPage.emailError().shouldBe(visible);
        close();
    }
}
