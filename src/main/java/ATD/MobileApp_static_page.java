package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MobileApp_static_page {

    public SelenideElement appLogo() { return $(By.cssSelector(".logo_app")); }

    public SelenideElement appQR() {
        return $(By.cssSelector(".qr_code>img"));
    }

    public SelenideElement appDiscount() {
        return $(By.cssSelector(".icons_discount>img"));
    }

    public SelenideElement appTitle() {
        return $(By.cssSelector(".title"));
    }

    public SelenideElement appSubmitEmailButton() {
        return $(By.cssSelector("#mobile_app_page_get_email"));
    }

    public SelenideElement appWrongEmailError() {
        return $(By.cssSelector("#wrong_ma_page>span"));
    }

    public SelenideElement appServicesList() {
        return $(By.cssSelector(".services_list"));
    }

    public SelenideElement appSubscribeCheckbox() {
        return $(By.xpath("//*[@class='checkbox subscribe_checkbox']"));
    }

    public SelenideElement appReviewsBlock() {
        return $(By.cssSelector(".app_reviews"));
    }

    public SelenideElement appLeftList() {
        return $(By.cssSelector(".left"));
    }

    public SelenideElement appRightList() {
        return $(By.cssSelector(".right"));
    }

    public SelenideElement appSlider() {
        return $(By.cssSelector("#slider"));
    }

    public SelenideElement appLeftSliderButton() {
        return $(By.xpath("//*[@class='prev left_arrow']"));
    }

    public SelenideElement appRightSliderButton() {
        return $(By.xpath("//*[@class='next right_arrow']"));
    }

    public SelenideElement firstImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[1]/img"));
    }

    public SelenideElement secondImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[2]/img"));
    }

    public SelenideElement thirdImaigeInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[3]/img"));
    }

    public SelenideElement fourthImageInSlider() {
        return $(By.xpath("//*[@id='slider']/div/ul/li[4]/img"));
    }

    public SelenideElement appStoreButton() { return $(By.xpath("//*[@class='download_icons']/a[1]")); }

    public SelenideElement googlePlayButton() { return $(By.xpath("//*[@class='download_icons']/a[2]")); }

    public SelenideElement applePageTitle() { return $(By.xpath("//*[@class='product-header__title app-header__title']")); }

    public SelenideElement googlePlayPageLogo() { return $(By.cssSelector(".xSyT2c>img")); }

    public SelenideElement datenschutzerklarungLink() { return $(By.cssSelector("#privacy_policy_app>a")); }

    SelenideElement mailFieldInSubscribeForm() { return $(By.id("mobile_app_page_email")); }

    SelenideElement checkboxInSubscribeForm() { return $(By.id("subscribe_rules")); }

    SelenideElement getMailBtnInSubscribeForm() { return $(By.id("mobile_app_page_get_email")); }

    SelenideElement successPopupInSubscribeForm() { return $(By.id("news_subscribe")); }
    SelenideElement successPopupCloseBtnInSubscribeForm() { return $(By.xpath("//div[@class='buttons-inner']")); }
}
