package ATD;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

class TyresListing_page {

    SelenideElement buyButton() { return $x("//div[@class='button ']/a[@id='search_page_product']"); }

}
