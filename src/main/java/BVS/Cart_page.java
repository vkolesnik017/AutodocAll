package BVS;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;

public class Cart_page {

    SelenideElement nextButton() {
        return $(byCssSelector(".next-step"));
    }
}
