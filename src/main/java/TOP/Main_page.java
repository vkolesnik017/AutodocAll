package TOP;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    SelenideElement cartIcon() {
        return $x("//div[@id='cart_block']");
    }

    SelenideElement cartBtn() {
        return $x("//a[@class='button ga-click']");
    }

}
