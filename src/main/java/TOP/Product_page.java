package TOP;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Product_page {

    SelenideElement buyButton() {
        return $x("//button[@class='inden']/..");
    }
}
