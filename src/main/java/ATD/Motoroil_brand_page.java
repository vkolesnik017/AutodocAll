package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_brand_page {

    SelenideElement inhaltFilterInSideBar() {return $x("//div[@class='sidebar two']//div[text()='Inhalt [Liter]']/..");}

    SelenideElement oemFreigabeFilterInSideBar() {return $x("//div[@class='sidebar two']//div[text()='OEM-Freigabe']/..");}

    SelenideElement specificationFilterInSideBar() {return $x("//div[@class='filter-disk__head specificationFilter']/..");}
}
