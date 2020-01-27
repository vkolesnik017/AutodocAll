package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.MobileApp_static_page;
import ATD.SetUp;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class QASYS_352_StaticPage_mobile_app {
    private Main_page mainPage = new Main_page();
    private MobileApp_static_page mobileAppStaticPage = new MobileApp_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Oleg Romanyuta")
    @Flaky
    @Description(value = "Test checks elements on mobile app page")
    public void checkMobileAppPageElements(String route) {
        openPage(route);
        closeCookiesFooterMessage();
        mainPage.clickMobileApp();
        mobileAppStaticPage.appLogo().shouldBe(visible);
        mobileAppStaticPage.appQR().shouldBe(visible);
        mobileAppStaticPage.appDiscount().shouldBe(visible);
        mobileAppStaticPage.appTitle().shouldBe(visible);
        mobileAppStaticPage.appSubscribeCheckbox().shouldBe(visible);
        mobileAppStaticPage.appSubmitEmailButton().click();
        mobileAppStaticPage.appWrongEmailError().shouldBe(visible);
        mobileAppStaticPage.appServicesList().shouldBe(visible);
        mobileAppStaticPage.appReviewsBlock().shouldBe(visible);
        mobileAppStaticPage.appLeftList().shouldBe(visible);
        mobileAppStaticPage.appRightList().shouldBe(visible);

        //Check automated banner switching and switching by button
        mobileAppStaticPage.appSlider().shouldBe(visible);
        mobileAppStaticPage.firstImageInSlider().shouldBe(visible);
        mobileAppStaticPage.secondImageInSlider().waitUntil(visible, 10000);
        mobileAppStaticPage.thirdImaigeInSlider().waitUntil(visible, 12000);
        mobileAppStaticPage.fourthImageInSlider().waitUntil(visible, 12000);
        mobileAppStaticPage.appRightSliderButton().click();
        mobileAppStaticPage.appRightSliderButton().click();
        mobileAppStaticPage.secondImageInSlider().shouldBe(visible);
        mobileAppStaticPage.appLeftSliderButton().click();
        mobileAppStaticPage.firstImageInSlider().shouldBe(visible);

        //Checks App Store and Google Play buttons functionality
        mobileAppStaticPage.appStoreButton()
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://itunes.apple.com/app/id1014949597"))
                .click();
        switchTo().window(1);
        mobileAppStaticPage.applePageTitle().shouldHave(text("Autodoc — Quality Auto Parts"));
        WebDriverRunner.getWebDriver().close();
        switchTo().window(0);
        mobileAppStaticPage.googlePlayButton()
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://play.google.com/store/apps/details?id=de.autodoc.gmbh&hl=de"))
                .click();
        switchTo().window(1);
        mobileAppStaticPage.googlePlayPageLogo().shouldBe(visible);
        WebDriverRunner.getWebDriver().close();
        switchTo().window(0);
    }
}
