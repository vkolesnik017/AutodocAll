package PRF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Cart_page {

    SelenideElement nextButton() {
        return $x("//a[@class='btn push-right step_1']");
    }
}
