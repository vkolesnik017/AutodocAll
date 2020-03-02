package ATD;


import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;

import static ATD.CommonMethods.clickable;
import static ATD.CommonMethods.mailRandom;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class MobileApp_static_page_Logic extends MobileApp_static_page {

    @Step(":registration form. MobileApp_static_page")
    public MobileApp_static_page_Logic checkingDatenschutzerklarungLinkBehavior() {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(privacyPolicyLink(), "underline solid rgb(0, 0, 0)");
        return this;
    }

    public String fillingFieldsAndCheckBehaviorSubscribeForm(String qc){
        String mail = qc + mailRandom();
        mailFieldInSubscribeForm().setValue(mail);
        appSubscribeCheckbox().click();
        getMailBtnInSubscribeForm().click();
        successPopupInSubscribeForm().shouldHave(text("Herzlichen Glückwünsch! Jetzt haben Sie einen 10% Rabatt für Ihren ersten Einkauf über die Handy-App."));
        successPopupCloseBtnInSubscribeForm().click();
        mailFieldInSubscribeForm().shouldBe(visible);
        return mail;
    }


    @Step("Checks for items at the top of the page MobileApp")
    public MobileApp_static_page_Logic checkForItemsFromTopPage(){
        appLogo().shouldBe(visible);
        appTitle().shouldBe(visible);
        appStoreButton().shouldBe(visible).shouldBe(clickable);
        googlePlayButton().shouldBe(visible).shouldBe(clickable);
        appQR().shouldBe(visible);
        appDiscount().shouldBe(visible);
        subscribeBlock().shouldBe(visible);
        mailFieldInSubscribeForm().shouldBe(visible);
        getMailBtnInSubscribeForm().shouldBe(visible);
        appSubscribeCheckbox().shouldBe(visible);
        privacyPolicyLink().shouldBe(visible).shouldBe(clickable);
        getMailBtnInSubscribeForm().click();
        appWrongEmailError().shouldBe(visible);
        appServicesList().shouldBe(visible);
        return this;
    }

    @Step("Checks App Store and Google Play buttons functionality")
    public MobileApp_static_page_Logic checkAppStoreAndGooglePlayButtonsFunctionality(){
        appStoreButton()
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://itunes.apple.com/app/id1014949597"))
                .click();
        switchTo().window(1);
        applePageTitle().shouldHave(text("Autodoc — Quality Auto Parts"));
        WebDriverRunner.getWebDriver().close();
        switchTo().window(0);
        googlePlayButton()
                .shouldBe(visible)
                .shouldHave(attribute("href", "https://play.google.com/store/apps/details?id=de.autodoc.gmbh&hl=de"))
                .click();
        switchTo().window(1);
        googlePlayPageLogo().shouldBe(visible);
        WebDriverRunner.getWebDriver().close();
        switchTo().window(0);
        return this;
    }

    @Step("Checks for the presence of a block with a slider and elements inside it")
    public MobileApp_static_page_Logic checkBlockWithSlider(){
        blockWithSlider().scrollTo();
        blockWithSlider().shouldBe(visible);
        sliderTitle().shouldBe(visible);
        leftListFeature().shouldBe(visible);
        rightListFeature().shouldBe(visible);
        appSlider().shouldBe(visible);
        return this;
    }

    @Step("Checks auto banner switching")
    public MobileApp_static_page_Logic checkAutoBannerSwitching(){
        firstImageInSlider().waitUntil(visible, 10000);
        secondImageInSlider().waitUntil(visible, 10000);
        thirdImaigeInSlider().waitUntil(visible, 12000);
        fourthImageInSlider().waitUntil(visible, 12000);
        return this;
    }

    @Step("Checks the banner switch buttons")
    public MobileApp_static_page_Logic checkBannerSwitchButtons(){
        appRightSliderButton().click();
        appRightSliderButton().click();
        secondImageInSlider().shouldBe(visible);
        appLeftSliderButton().click();
        firstImageInSlider().shouldBe(visible);
        return this;
    }

    @Step("Checks for the presence of a block Reviews")
    public MobileApp_static_page_Logic checkReviewsBlock(){
        appReviewsBlock().shouldBe(visible);
        return this;
    }
}
