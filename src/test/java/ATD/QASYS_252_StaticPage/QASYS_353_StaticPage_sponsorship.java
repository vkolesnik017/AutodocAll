package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.Main_page_Logic;
import ATD.SetUp;
import ATD.Sponsorship_static_page;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_353_StaticPage_sponsorship {
    private Sponsorship_static_page sponsorshipStaticPage = new Sponsorship_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = false) //disabled in SITES-7313
    @Owner(value = "Oleg Romanyuta")
    @Flaky
    @Description(value = "Test checks elements on sponsorship page")
    public void checkSponsorshipPgeElements(String route) {
        openPage(route);
        closeCookiesFooterMessage();
        new Main_page_Logic().clickSponsorship();
        sponsorshipStaticPage.sponsorsHeader().shouldBe(visible);
        sponsorshipStaticPage.raceGallery().shouldBe(visible);
        sponsorshipStaticPage.mapText().shouldBe(visible);
        sponsorshipStaticPage.sendShipForm().shouldBe(visible);
        sponsorshipStaticPage.submitShipDataButton().click();
        sponsorshipStaticPage.emailError().shouldBe(visible);
    }
}
