package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

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

   SelenideElement specificationBlock() {return $x("//h4[contains(text(),'Spezifikationen')]/..");}

   ElementsCollection specificationLinks() {return $$x("//h4[contains(text(),'Spezifikationen')]/..//li/a");}

   ElementsCollection btnAddToBasketTopBLock() {return $$x("//div[@class='basket_btn button active_red_button ']/a");}

    ElementsCollection btnUpOfCountTopProduct() {return $$x("//div[@class='basket_btn button active_red_button ']/..//i[@class='fa fa-angle-right']");}

   SelenideElement basketDropMenu() {
      return $x("//div[@class='cart-items-block ']");
   }

   SelenideElement basket() {
      return $(byId("cart_block_link"));
   }

   SelenideElement kbaSelector() {return $x("//div[@class='mainblock-search__kba']");}

   SelenideElement firstFieldKbaSelector() {return $(byId("kba1"));}

   SelenideElement secondFieldKbaSelector() {return $(byId("kba2"));}

   SelenideElement btnSearchOfKbaSelector() {return $x("//div[@class='mainblock-search__kba']//a[contains(@class,'submit')]");}

   SelenideElement titleOfKbaSelector() {return $x("//div[@class='mainblock-search__kba']//b");}

   SelenideElement errorToolTipFirstField() {return $(byId("kba-error-tooltip"));}

   SelenideElement errorMessageAboutEmptyKbaFields() {return $x("//div[contains(text(),'Geben Sie bitte Ihre Schl??sselnummer ein')]");}

   SelenideElement informationKbaPopUp() {return $(byId("show-kba-popup"));}

   SelenideElement informationIconOfKbaSelector() {return $x("//div[@class='kba_popup']");}

   SelenideElement detectedVehicleLink() {return $x("//a[@class='form-not-found-car ga-click']");}

   SelenideElement detectedVehiclePopUp() {return $x("//div[@class='not-found-car-popup']");}

   SelenideElement oilTypesBlock() {return $x("//h4[contains(text(),'Eigenschaften')]/..");}

   ElementsCollection linksOfOilTypes() {return $$x("//h4[contains(text(),'Eigenschaften')]/..//li/a");}

   ElementsCollection visibleLinksOfViscosityBlock() {return $$x("//div[@class='select_oil_block--oil']//a").filter(visible);}

   SelenideElement errorPopUpOfSelector() {return $x("//div[@class='popup-box-selector']");}

   SelenideElement firstFieldKbaSelectorInErrorPopUp() {return $x("//div[@class='popup-box-selector']//input[@id='kba1']");}

   SelenideElement secondFieldKbaSelectorInErrorPopUp() {return $x("//div[@class='popup-box-selector']//input[@id='kba2']");}

   SelenideElement btnSearchOfKbaSelectorInErrorPopUp() {return $x("//div[@class='popup-box-selector']//a[@class='submit kba_submit ripple-out']");}

   SelenideElement brand(String title) {return $x("//ul[@class='featured_manufacturers_image']/li/a[@data-ga-action='"+title+"']");}

}
