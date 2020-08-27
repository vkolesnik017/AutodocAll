package PKW;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class Austauschartikel_static_page {

    SelenideElement pfandPageTitle() {
        return $(byXpath("//*[@class='pfand-page__title']"));
    }

    SelenideElement pfandInfoText() {
        return $(byXpath("//*[@class='pfand-info']"));
    }

    SelenideElement pfandItWorksText() {
        return $(byXpath("//*[@class='it-works']"));
    }

    SelenideElement pfandItWorksAnchorLink() {
        return $(byXpath("//*[@class='anchor-on-form']"));
    }

    SelenideElement pfandReturnPartsText() {
        return $(byXpath("//*[@class='return-parts']"));
    }

    SelenideElement pfandReturnPartsCategories() {
        return $(byXpath("//*[@class='return-parts__categories']"));
    }

    SelenideElement pfandReturnPartsItem() {
        return $(By.id("pfand_categories"));
    }

    SelenideElement pfandReturnPolicy() {
        return $(byXpath("//*[@class='return-policy']"));
    }

    SelenideElement pfandReturn() {
        return $(byXpath("//*[@class='return']"));
    }

    SelenideElement pfandInfoButtonTooltipPlz() {
        return $(byXpath("//a[@class='info_link_index']"));
    }

    SelenideElement pfandInfoButtonTooltipOrder() {
        return $(byXpath("//*[@class='info_link_order']"));
    }

    SelenideElement pfandInfoTooltipPlz() {
        return $(byXpath("//*[@class='informIndex']"));
    }

    SelenideElement closeButtonTooltipPlz() {
        return $(byXpath("//*[@class='closeInfoIndex']"));
    }

    SelenideElement pfandInfoTooltipOrder() {
        return $(byXpath("//*[@class='informOrder']"));
    }

    SelenideElement closeButtonTooltipOrder() {
        return $(byXpath("//*[@class='closeInfoOrder']"));
    }

    SelenideElement anchorOnTheForm() {
        return $(byXpath("//*[@class='formular']"));
    }

    ElementsCollection categories() {
        return $$x("//*[@class='return-parts__item']");
    }

    ElementsCollection popUpOfCategory() {
        return $$x("//div[@class='return-subcategory']");
    }


    SelenideElement popUpOfCategoryOne() {
        return $x("//div[@class='return-subcategory']");
    }
}


