package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_page {

   SelenideElement brandsBlock() {return $x("//div[@class='featured_manufacturers featured_manufacturers--oil']");}

   ElementsCollection brandLinks() {return $$x("//ul[@class='featured_manufacturers_image']/li/a");}

   SelenideElement toleranceBlock() {return $x("//h4[contains(text(),'Herstellerfreigabe')]/..");}

   ElementsCollection toleranceLinks() {return $$x("//h4[contains(text(),'Herstellerfreigabe')]/..//li/a");}

   SelenideElement viscosityBlock() {return $x("//div[@class='select_oil_block--oil']");}

   ElementsCollection linksOfViscosityBlock() {return $$x("//div[@class='select_oil_block--oil']//a");}

   ElementsCollection iconOfLinksInViscosityBlock() {return $$x("//div[@class='select_oil_block--oil']//a/div[1]");}

   SelenideElement topProductsBlock() {return $x("//div[@class='car_tires_toppop']");}

   ElementsCollection linksOfTopProducts() {return $$x("//div[@class='car_tires_toppop']//ul/li");}

   ElementsCollection btnDetailsOfTopProducts() {return $$x("//div[@class='pkw-related__additional-header']/span");}

   ElementsCollection arrowsDownOfTopProduct() {return $$x("//div[@class='pkw-related__item-header']/button");}

   ElementsCollection titleOfTopProducts() {return $$x("//span[@class='pkw-related__title']");}

   ElementsCollection imageOfTopProducts() {return $$x("//span[@class='pkw-related__img-container']/img");}

}
