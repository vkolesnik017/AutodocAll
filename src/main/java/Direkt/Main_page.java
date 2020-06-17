package Direkt;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    public SelenideElement cartIcon() {
        return $x("//div[@class='header-middle__cart']//div[@class='cart-icon']");
    }
}
