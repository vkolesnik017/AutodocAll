package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_Search_page {

    SelenideElement selector() {return $x("//div[@class='mainblock-search__car ']");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    SelenideElement btnResetOfSelector() {return $(byId("reset_selector_form"));}
}
