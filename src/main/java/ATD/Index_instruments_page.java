package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

class Index_instruments_page {

    SelenideElement titleMainPage() {
        return $x("//h1[@class='special-catalog__title']");
    }

}
