package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_viscosity_page {

    SelenideElement breadCrumbsBlock() {return $x("//div[@class='crabs']");}

    ElementsCollection linksOfBreadCrumbsBlock() {return $$x("//div[@class='crabs']/div/a");}

    SelenideElement productsListBlock() {return $x("//div[@class='listing_items']");}

    SelenideElement paymentMethodsBlock() {return $x("//div[@class='sidebar']//div[contains(text(),'Zahlungsmethode')]/..");}

    ElementsCollection imageOfPaymentMethods() {return $$x("//div[@class='sidebar']//div[contains(text(),'Zahlungsmethode')]/..//ul/li/img");}

    SelenideElement advantagesBlock() {return $x("//div[@class='sb_content features_wrap']");}

    SelenideElement headLineOfAdvantagesBlock() { return $x("//div[@class='sb_content features_wrap']/../div[1]");}

    ElementsCollection advantagesLinks() {return $$x("//div[@class='sb_content features_wrap']//ul/li");}

    ElementsCollection titleOfAdvantagesLinks() {return $$x("//div[@class='sb_content features_wrap']//ul/li//strong");}

    ElementsCollection hoveringTextOfAdvantagesLinks() {return $$x("//span[@class='pophover-text']");}

    SelenideElement mainHeadline() {return $x("//div[@class='listing_title listing-title--oil']/h1");}

}
