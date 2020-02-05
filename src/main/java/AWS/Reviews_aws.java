package AWS;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Reviews_aws {
    public SelenideElement searchTextOnPage(String textForSearch) {
        return $x("//*[contains(text(),'" + textForSearch + "')]");
    }
}
