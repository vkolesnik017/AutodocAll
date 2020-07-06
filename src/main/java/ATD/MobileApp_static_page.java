package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

class MobileApp_static_page {

    SelenideElement appLogo() {
        return $(By.cssSelector(".logo_app"));
    }

    SelenideElement appQR() {
        return $(By.cssSelector(".qr_code>img"));
    }

    SelenideElement appTitle() {
        return $x("//div[text()='Autodoc App']");
    }

    SelenideElement mailFieldInSubscribeForm() {
        return $(By.id("mobile_app_page_email"));
    }

    SelenideElement getMailBtnInSubscribeForm() {
        return $(By.id("mobile_app_page_get_email"));
    }

    SelenideElement appSubscribeCheckbox() {
        return $(By.xpath("//*[@class='checkbox subscribe_checkbox']"));
    }

    SelenideElement privacyPolicyLink() {
        return $(By.cssSelector("#privacy_policy_app>a"));
    }

    SelenideElement appServicesList() {
        return $(By.cssSelector(".services_list"));
    }

    SelenideElement blockWithSlider() {
        return $x("//div[@class='slider_description']");
    }

    SelenideElement sliderTitle() {
        return $x("//div[@class='slider_description']//div[@class='title']");
    }

    SelenideElement leftListFeature() {
        return $(By.cssSelector(".left"));
    }

    SelenideElement rightListFeature() {
        return $(By.cssSelector(".right"));
    }

    SelenideElement appSlider() {
        return $(By.cssSelector("#slider"));
    }

    SelenideElement appLeftSliderButton() {
        return $(By.xpath("//*[@class='prev left_arrow']"));
    }

    SelenideElement appRightSliderButton() {
        return $(By.xpath("//*[@class='next right_arrow']"));
    }

    SelenideElement firstImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[1]/img"));
    }

    SelenideElement secondImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[2]/img"));
    }

    SelenideElement thirdImaigeInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[3]/img"));
    }

    SelenideElement fourthImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[4]/img"));
    }

    SelenideElement appStoreButton() {
        return $(By.xpath("//*[@class='download_icons']/a[1]"));
    }

    SelenideElement googlePlayButton() {
        return $(By.xpath("//*[@class='download_icons']/a[2]"));
    }

    SelenideElement applePageTitle() {
        return $(By.xpath("//*[@class='product-header__title app-header__title']"));
    }

    SelenideElement googlePlayPageLogo() {
        return $(By.cssSelector(".xSyT2c>img"));
    }

    SelenideElement appReviewsBlock() {
        return $(By.cssSelector(".app_reviews"));
    }

    SelenideElement successPopupInSubscribeForm() {
        return $(By.id("news_subscribe"));
    }

    SelenideElement successPopupCloseBtnInSubscribeForm() {
        return $(By.xpath("//div[@class='buttons-inner']"));
    }
}
