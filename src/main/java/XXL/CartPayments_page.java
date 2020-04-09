package XXL;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CartPayments_page {

    SelenideElement btnReturnTheAddressPage() {
        return $x("//div[@class='steps']//li[3]");
    }
}
