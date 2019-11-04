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

    public SelenideElement applePageLogo() { return $(By.xpath("//picture[@id='ember143']")); }

    public SelenideElement googlePlayPageLogo() { return $(By.cssSelector(".xSyT2c>img")); }
}
