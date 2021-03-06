package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_Brand_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}
}
