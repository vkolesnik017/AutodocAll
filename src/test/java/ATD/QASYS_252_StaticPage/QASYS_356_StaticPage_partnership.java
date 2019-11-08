package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.Partnership_static_page;
import ATD.SetUp;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;


public class QASYS_356_StaticPage_partnership {
    private Main_page mainPage = new Main_page();
    private Partnership_static_page partnershipStaticPage = new Partnership_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Flaky
    @Owner(value = "Oleg Romanyuta")
    @Test(dataProvider = "route")
    public void checkPartnershipPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickPartnership();
        partnershipStaticPage.logo().shouldBe(visible);
        partnershipStaticPage.title().shouldBe(visible);
        partnershipStaticPage.logo2().shouldBe(visible);
        partnershipStaticPage.textPartner().shouldBe(visible);
        partnershipStaticPage.textPartner2().shouldBe(visible);
        partnershipStaticPage.textPartner3().shouldBe(visible);
        partnershipStaticPage.offerTitle().shouldBe(visible);
        partnershipStaticPage.offerBlock1().shouldBe(visible);
        partnershipStaticPage.offerImage1().shouldBe(visible);
        partnershipStaticPage.offerBlock2().shouldBe(visible);
        partnershipStaticPage.offerImage2().shouldBe(visible);
        partnershipStaticPage.mapText().shouldBe(visible);
        partnershipStaticPage.sendShipForm().shouldBe(visible);
        partnershipStaticPage.submitShipDataButton().click();
        partnershipStaticPage.emailError().shouldBe(visible);
    }
}
