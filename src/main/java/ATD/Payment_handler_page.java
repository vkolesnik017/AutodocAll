package ATD;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Payment_handler_page {


    public SelenideElement successTextInHeader() {
        return $(By.xpath("//div[@class='success-order__head']/strong"));
    }

    private SelenideElement orderNumber() {
        return $(By.xpath("//div[@class='success-order__info']/b"));
    }

    public SelenideElement orderMail() {
        return $(By.xpath("//div[@class='success-order__main']/p/b"));
    }

    private SelenideElement popupAfterOrder() {
        return $(By.xpath("//div[@class='popup-after-order']"));
    }

    private SelenideElement closePopupAfterOrderBtn() {
        return $(By.cssSelector(".popup-after-order__close"));
    }

    public Payment_handler_page closePopupAfterOrder(){
        try {
            popupAfterOrder().shouldBe(visible);
            closePopupAfterOrderBtn().click();
        }catch (ElementNotFound e){
        }
        return this;
    }

    public String getOrderNumber(){
        return orderNumber().getText();
    }
}
