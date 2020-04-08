package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class CartPayments_page {

    SelenideElement btnReturnTheAddressPage() {
        return $x("//li[@class='complete second_step link ']//a");
    }
}
