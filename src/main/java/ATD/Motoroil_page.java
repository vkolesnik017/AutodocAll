package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class Motoroil_page {

    SelenideElement carSelector() {
        return $x("//*[@class='block-select-car']");
    }

    SelenideElement oilSelector() {
        return $x("//*[@id='oil-top-select']");
    }

    SelenideElement popularBrandBlock() {
        return $x("//*[@class='oil-brands']");
    }

    SelenideElement reviewsBlock() {
        return $x("//*[@class='oil-review']");
    }

    SelenideElement seoBlock() {
        return $x("//*[@class='oil-info']");
    }

    SelenideElement kbaInfoButton() {
        return $x("//*[@class='block-select-kba__info']");
    }

    SelenideElement kbaInfoPopUp() {
        return $x("//*[@class='kba_popup_example']");
    }

    SelenideElement kbaInfoPopUpCloseButton() {
        return $x("//*[@class='kba_popup_example']//*[@class='close']");
    }

    SelenideElement advantagesBlock() {
        return $x("//*[@class='oil-advantages']");
    }

    ElementsCollection advantagesBlockItem() {
        return $$x("//*[@class='oil-advantages__item']");
    }

    ElementsCollection advantagesBlockIcon() {
        return $$x("//*[@class='oil-advantages__icon']//img");
    }

    ElementsCollection advantagesBlockCount() {
        return $$x("//*[@class='oil-advantages__count']");
    }

    ElementsCollection advantagesBlockTitle() {
        return $$x("//*[@class='oil-advantages__title']");
    }

    ElementsCollection advantagesBlockText() {
        return $$x("//*[@class='oil-advantages__text']");
    }

    SelenideElement oilViscosityBlock() {
        return $x("//*[@class='oil-viscosity']");
    }

    ElementsCollection oilViscosityItem() {
        return $$x("//*[@class='oil-viscosity__item']");
    }

    ElementsCollection oilViscosityItemLink() {
        return $$x("//*[@class='oil-viscosity__item']/a");
    }

    ElementsCollection oilViscosityIcon() {
        return $$x("//*[@class='oil-viscosity__item__icon']");
    }

    ElementsCollection oilViscosityText() {
        return $$x("//*[@class='oil-viscosity__item__text']");
    }

    SelenideElement kbaSelectorBlock() {
        return $x("//*[@class='block-select-kba  block-select-kba--de']");
    }

    SelenideElement kbaSelectorSendButton() {
        return $x("//*[@class='submit kba_submit']");
    }

    SelenideElement kbaSelectorErrorMessageEmptyFields() {
        return $x("//*[@id='kba-error-tooltip']");
    }

    SelenideElement kbaSelectorErrorMessageEmptyFieldsIcon() {
        return $x("//*[@id='kba-error-tooltip']//i");
    }

    SelenideElement kbaSelectorErrorMessageEmptyFieldsText() {
        return $x("//*[@id='kba-error-tooltip']//div");
    }

    SelenideElement kbaSelectorFirstInput() {
        return $x("//*[@id='kba1']");
    }

    SelenideElement mehrSchliebenButtonMarksBlock() {
        return $x("//*[@class='show-all__link']");
    }

    SelenideElement carListBlock() {
        return $x("//*[@class='cars-list']");
    }

    SelenideElement kbaSelectorSecondInput() {
        return $x("//*[@id='kba2']");
    }

    SelenideElement mehrButtonMarksBlock() {
        return $x("//*[@class='cars-list__wrap js-show-all-content clearfix']");
    }

    SelenideElement schliebenButtonMarksBlock() {
        return $x("//*[@class='cars-list__wrap js-show-all-content clearfix show']");
    }

    SelenideElement carsMakerItem() {
        return $x("//*[@class='cars-list-item'][2]//img");
    }

}