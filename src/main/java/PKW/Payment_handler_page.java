package PKW;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Payment_handler_page {

    SelenideElement orderNumber() {
        return $(By.xpath("//div[@class='success-order__info']/b"));
    }

    SelenideElement popupAfterOrder() {
        return $x("//div[@class='popup-after-order__wrap']");
    }

    SelenideElement closePopupAfterOrderBtn() {
        return $x("//span[@class='popup-after-order__close']");
    }

    SelenideElement nameOfOrganization() {
        return $x("//div[@class='success-order__order']/div/div[1]/span[2]/b");
    }

    SelenideElement priceOrder() {
        return $x("//div[@class='success-order__order']/div/div[3]/span[2]/b");
    }

    SelenideElement lincForPDF() {
        return $x("(//span[@class='link'])[1]");
    }

    SelenideElement requisites() {
        return $x("(//div[@class='success-order__order']//div)[1]");
    }
}
