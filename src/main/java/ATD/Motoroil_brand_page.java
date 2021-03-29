package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class Motoroil_brand_page {

    SelenideElement brandOilFilterBlock() {
        return $x("//div[@id='selected-instalation__slider']");
    }

    SelenideElement inhaltFilterInSideBar() {return $x("//div[@class='sidebar two']//div[text()='Inhalt [Liter]']/..");}

    SelenideElement oemFreigabeFilterInSideBar() {return $x("//div[@class='sidebar two']//div[text()='OEM-Freigabe']/..");}

    SelenideElement specificationFilterInSideBar() {return $x("//div[@class='filter-disk__head specificationFilter']/..");}

    SelenideElement oemFreigabeFilter(String id) {return $x("//div[@class='filter-disk__head releaseFilter']/..//label[text()='"+id+"']");}

    SelenideElement inhaltFilter(String id) {return $x("//div[@class='filter-disk__form']/..//label[text()='"+id+"']");}

    SelenideElement specificationFilter(String id) {return $x("//div[@class='filter-disk__form']/..//label[text()='"+id+"']");}

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement oemFreigabeSelector() {return $(byName("release"));}

    SelenideElement specificationSelector() {return $(byName("specification"));}

    SelenideElement checkBoxOemFreigabeFilter(String id) {return $x("//div[@class='filter-disk__head releaseFilter']/..//label[text()='"+id+"']/preceding-sibling::input");}

    SelenideElement checkBoxSpecificationFilter(String id) {return $x("//div[@class='filter-disk__form']/..//label[text()='"+id+"']/preceding-sibling::input");}

    SelenideElement checkBoxInhaltFilter(String id) {return $x("//div[@class='filter-disk__form']/..//label[text()='"+id+"']/preceding-sibling::input");}

    ElementsCollection oemValuesInProducts() {return $$x("//span[text()='Herstellerfreigabe:  ']/following-sibling::span");}

    ElementsCollection specificationValuesInProducts() {return $$x("//span[text()='Spezifikation:  ']/following-sibling::span");}

    ElementsCollection inhaltValuesInProducts() {return $$x("//span[text()='Inhalt [Liter]:  ']/following-sibling::span");}

    SelenideElement nameOilInSelector() {
        return $x("//*[@name='brand']//*[@value='castrol']");
    }

    SelenideElement oilSelector() {
        return $x("//*[@class='selector-oil selector-oil--white']");
    }

    SelenideElement brandNameInListing() {
        return $x("//*[@class='js-product-item ovVisLi item_in_stock  '][1]//*[@class='name']//a");
    }

    SelenideElement btnAddToBasketFromDisplacementBlock() { return $x("//div[@class='select-displacement ']/a");}

    SelenideElement displacementFromProduct() {return $x("//div[@class='select-displacement ']");}

    SelenideElement popupBasketAddedProducts() {
        return $x("//div[@class='cart-items-block ']");
    }


}
