package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Payment_handler_page {


    public SelenideElement successTextInHeader() {
        return $(By.xpath("//div[@class='success-order__head']/strong"));
    }

    public SelenideElement orderNumber() {
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
            popupAfterOrder().shouldBe(Condition.visible);
            closePopupAfterOrderBtn().click();
        }catch (Exception ignored){
        }
        return this;
    }

}
