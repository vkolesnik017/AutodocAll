package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Oe_number_page {

    SelenideElement titlePage() {
        return $x("//h2/span");
    }
}
