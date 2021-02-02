package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Payment_handler_page {


    public SelenideElement successTextInHeader() {
        return $(By.xpath("//div[@class='success-order__head']/strong"));
    }

    SelenideElement orderNumber() {
        return $(By.xpath("//div[@class='success-order__info']/*"));
    }

    public SelenideElement orderMail() {
        return $(By.xpath("//div[@class='success-order__main']/p/b"));
    }

    SelenideElement popupAfterOrder() {
        return $(By.xpath("//div[@class='popup-after-order']"));
    }

    SelenideElement closePopupAfterOrderBtn() {
        return $(By.cssSelector(".popup-after-order__close"));
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
