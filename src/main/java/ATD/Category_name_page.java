package ATD;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class Category_name_page {

    protected SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
    }

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement topProductsBlock() {return $x("//div[contains(@class,'js-product-list-animation')]");}

    ElementsCollection btnAddDangerousProductToWishList() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[contains(@class,'product-list__item')]/div[@class='add-to-wishlist add-article']");}

    SelenideElement forwardOfTopBrandsBlock() {return $x("//button[@class='slick-next slick-arrow']");}

    ElementsCollection visibleArtNumOfTopProduct() {return $$x("//div[@class='product-list__item__nummer']").filter(visible);}

    ElementsCollection signalWordOfDangerousProduct() {return $$x("//div[@class='popup-dangerous__title']");}

    ElementsCollection dangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']/ancestor::div[contains(@class,'product-list__item')]");}

    ElementsCollection attributeOfWarningIcon(int positionOfProduct) {return $$x("(//span[@class='dangerous-listing__show-more'])["+positionOfProduct+"]/ancestor::div[contains(@class,'product-list__item')]//div[@class='dangerous-listing__icon dangerous-listing__icon-attention']");}

    ElementsCollection labelTitleDangerousProducts() {return $$x("//span[@class='dangerous-listing__show-more']");}

    SelenideElement blackBackground() {return $x("//div[@class='overlay black hidden']");}

    SelenideElement warningPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement titleOfDangerousPopUp() {return $x("//div[@class='popup-dangerous__title']");}

    SelenideElement infoTextOfDangerousPopUp() {return $x("//div[@class='popup-dangerous']//p");}

    ElementsCollection visibleTopProducts() {return $$x("//div[@class='product-list__item active']").filter(visible);}

    ElementsCollection allTopBrands() {return $$x("//div[@class='kategorie_top_brands']//li/a");}

    ElementsCollection visibleTopBrands() {return $$x("//div[@class='kategorie_top_brands']//li/a").filter(visible);}

    SelenideElement topBrandsBlock() {return $x("//div[@class='kategorie_top_brands']");}

    SelenideElement btnMoreOfBrandsBlock() {return $x("//div[@class='kategorie_top_brands']//div[@class='m_text show']");}

    SelenideElement priceInfoText() {return $x("//div[@class='summary-table']/div[1]");}

    SelenideElement headlineOfBrandsBlock() {return $x("//div[@class='summary-table__table']//tr[5]/th");}

    ElementsCollection valuesOfBrandsBlock() {return $$x("//div[@class='summary-table__table']//tr[6]/td/span");}

    SelenideElement headlineOfManufacturerBlock() {return $x("//div[@class='summary-table__table']//tr[3]/th");}

    ElementsCollection valuesOfManufactureBlock() {return $$x("//div[@class='summary-table__table']//tr[4]/td/span");}

    SelenideElement infoBlockUnderTopProductsBlock() {return $x("//div[@class='product-list product-list--4items js-product-list-slider--4 js-product-list-animation js-car-popup slick-initialized slick-slider']/following-sibling::div[@class='summary-table']");}

    SelenideElement headlineOfInfoBlock() {return $x("//div[@class='title_list'][2]");}

   ElementsCollection valuesOfInfoBlock() {return $$x("//th[contains(text(),'Für beliebte Automarken:')]/../following-sibling::tr/td/span");}

    SelenideElement headlineOfProductArtBlock() {return $x("//th[contains(text(),'Die meistverkauften Artikel:')]");}

    ElementsCollection valuesOfProductArtBlock() {return $$x("//th[contains(text(),'Die meistverkauften Artikel:')]/../following-sibling::tr[1]/td/span");}

    SelenideElement characteristicBlockUnderMainBlock() {return $x("//th[contains(text(),'Für beliebte Automarken:')]/ancestor::div[@class='summary-table__table']//table[2]");}

    ElementsCollection typeOfCharacteristicsBlock() {return $$x("//table[2]//tr[2]/td");}

    ElementsCollection allValueFromCharacteristicBlock(int positionOfBlock) {return $$x("//table[2]//td["+positionOfBlock+"]");}

    SelenideElement dangerousPopUp() {return $x("//div[@class='popup-dangerous']");}

    SelenideElement brandImgFromBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']//img");
    }

    SelenideElement hrefBrandFromBrandsBlock() {
        return $x("//div[@class='kategorie_top_brands']//a");
    }

    SelenideElement mainHeadline() {return $x("//div[@class='top_title no_image']/h2");}

    SelenideElement markeFieldInSelector() {return $(byId("form_maker_id"));}

    ElementsCollection  titleOfTopProducts() {return $$(".product-list__item__title span");}

   public SelenideElement infoPriceForSetFromTopProduct() { return $x("//div[contains(@class,'slick-active')]//p[text()='(Preis pro Satz)']");}

    public SelenideElement infoPriceForPieceFromTopProduct() { return $x("//div[contains(@class,'slick-active')]//p[text()='(Artikelpreis)']");}

    SelenideElement headlineOfSummaryTable() {return $x("//div[@class='summary-table']/preceding-sibling::div[1]/b");}

    ElementsCollection titlesOfSummaryTable() {return $$(".summary-table th");}

    ElementsCollection titlesOfSeoBlock() {return $$x("//div[contains(@class,'block_youtube_video')]//*[self::h3 or self::strong]");}

    SelenideElement titleOfBrandsBlock() {return $x("//div[@class='kategorie_top_brands']/preceding-sibling::div[1]/b");}
}
