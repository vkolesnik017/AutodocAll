package KAUF;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Main_page {

    public SelenideElement cartIcon() {
        return $x("//a[@class='tit']");
    }
}
