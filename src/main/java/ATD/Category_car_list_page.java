package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

class Category_car_list_page {
    // Form soft 404
    SelenideElement mailFieldSoftForm() {
        return $(By.id("form_email"));
    }

    SelenideElement submitBtnSoftForm() {
        return $(By.cssSelector(".notification-form__row > button"));
    }

    SelenideElement subscribeCheckboxSoftForm() {
        return $(By.id("subscribe_on"));
    }

    SelenideElement errPopupSoftForm() {
        return $(By.id("popup_update"));
    }

    SelenideElement successPopupSoftForm() {
        return $(By.xpath("//div[@class='new_popup popup_email_not']"));
    }

    SelenideElement closeErrPopupSoftForm() {
        return $(By.xpath("//div[@class='popup_content']//a[@class='close']"));
    }

    SelenideElement closeSuccessPopupSoftForm() {
        return $(By.xpath("//div[@class='button loc']"));
    }

    SelenideElement datenschutzerklarungLinkSoftForm() {
        return $(By.cssSelector("#privacy_policy1>a"));
    }

    SelenideElement loaderInTecDocListing() {
        return $x("//div[@class='preloader_wrapper']");
    }

    SelenideElement  brandsFilterBlock() {return $x("//div[@data-name='brand']");}

    SelenideElement brandsLinkInSideBar(String idOfBrand) {return $x("//div[@id='selected-instalation__slider']//ul//li//label[@for='cb-brand-"+idOfBrand+"']");}

    SelenideElement forwardLinkAtBrandsFilter() {return $x("//a[contains(@class,'next')]");}

   ElementsCollection titleOfProductInTecDocListing() {return $$x("//div[@class='name']/*[self::a or self::span][1]");}

    ElementsCollection subTitleOfProductInTecDocListing() {return $$x("//span[@class='subname']");}

   ElementsCollection imageOfBrandInProductBlock() {return $$x("//div[@class='image']/span[1]/img");}

    ElementsCollection descriptionBlockOfProduct() {return $$x("//div[@class='description']");}

    ElementsCollection characteristicListOfProduct(int positionOfProduct) {return $$x("(//div[@class='description'])["+positionOfProduct+"]//div[@class='about']//ul/li/span[1]");}

    ElementsCollection activeBtnAddProductToBasket() {return $$x("//div[@class='button ']");}

    ElementsCollection priceOfProduct() {return $$x("//p[contains(@class,'actual_price')]");}

    ElementsCollection notActiveBtnAddProductToBasket() {return $$x("//div[@class='button not_active']");}

    SelenideElement forwardNextPaginator() {return $x("//span[@class='next'][1]/a");}

    SelenideElement mainSearchField() {return $x("//input[@class='header-search__input']");}

    SelenideElement btnSearchOfSearchField() {return $x("//a[@class='header-search__submit search_submit form-submitter']");}

    SelenideElement  countOfVehicleInIconOfGarageInHeader() {return $x("//span[@class='header-garage__count header-garage__count--added']");}

    SelenideElement idOfVehicleInGaragePopUp(String idOfVehicle) {return $x("//div[@class='wrapper-radio']/label[@for='"+idOfVehicle+"']");}

    SelenideElement headerGarageIcon(){ return $x("//div[@class='header-garage js-header-garage']"); }

    SelenideElement popUpOfGarageInHeader() {return $x("//div[@class='header-garage__logged-header']");}

    SelenideElement listingOfProductsBlock() {return $x("//div[@class='listing-wrap']");}

    SelenideElement forwardLinkOfPaginator() {return $x("//span[@class='next'][1]"); }

    ElementsCollection allCharacteristicsOfProducts() {return $$x("//div[@class='about']//ul/li");}

    ElementsCollection artNumOfProduct() {return $$x("//span[@class='article_number'][1]");}

    ElementsCollection visibleCharacteristicsOfProducts(int position) {
        return $$x("(//div[@class='about'])[" + position + "]//ul/li");
    }

  }
