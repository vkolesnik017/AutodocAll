package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Car_parts_motoroil_page {

    SelenideElement oilLitericon() {return $("div.oil-title-info-block");}

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}
}
