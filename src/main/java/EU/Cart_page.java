package EU;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $x("//a[@class='order_bt ga-click']");
    }
}
