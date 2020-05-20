package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Moto_Category_page {
    protected SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
    }

    SelenideElement motoSelectorBlock() {return $(byId("top-select"));}

    SelenideElement brandOfMotoField() {return  $(byId("form_maker_id"));}

    SelenideElement btnSearchAtSelector() {return $x("//a[@class='submit search_button']");}

    SelenideElement tooltipOfMarkeField() {return $x("//div[@id='maker-select']//div[@id='selector-error-tooltip']");}
}
