package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Uber_uns_static_page {

    SelenideElement blockUberUns() {
        return $x("//*[@id='content']//*[@class='text']");
    }
}
