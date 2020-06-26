package PKW;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;


public class Index_instruments_page {

    SelenideElement titleMainPage() {
        return $x("//h1[@class='page-title']");
    }


}
