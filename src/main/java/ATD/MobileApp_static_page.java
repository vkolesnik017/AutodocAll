package ATD;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class MobileApp_static_page {

    SelenideElement appLogo() { return $(By.cssSelector(".logo_app")); }

    SelenideElement appQR() {
        return $(By.cssSelector(".qr_code>img"));
    }

    SelenideElement appDiscount() {
        return $(By.cssSelector(".icons_discount>img"));
    }

    SelenideElement appTitle() {
        return $(By.cssSelector(".title"));
    }

    SelenideElement appSubmitEmailButton() {
        return $(By.cssSelector("#mobile_app_page_get_email"));
    }

    SelenideElement appWrongEmailError() {
        return $(By.cssSelector("#wrong_ma_page>span"));
    }

    SelenideElement appServicesList() {
        return $(By.cssSelector(".services_list"));
    }

    SelenideElement appSubscribeCheckbox() {
        return $(By.xpath("//*[@class='checkbox subscribe_checkbox']"));
    }

    SelenideElement appReviewsBlock() {
        return $(By.cssSelector(".app_reviews"));
    }

    SelenideElement appLeftList() {
        return $(By.cssSelector(".left"));
    }

    SelenideElement appRightList() {
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

    SelenideElement appStoreButton() { return $(By.xpath("//*[@class='download_icons']/a[1]")); }

    SelenideElement googlePlayButton() { return $(By.xpath("//*[@class='download_icons']/a[2]")); }

    SelenideElement applePageLogo() { return $(By.xpath("//picture[@id='ember143']")); }

    SelenideElement googlePlayPageLogo() { return $(By.cssSelector(".xSyT2c>img")); }
}
