package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Austauschartikel_static_page {

    SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    SelenideElement title() {
        return $(By.xpath("//div[@class='phand-about__title']"));
    }

    public SelenideElement mainTextFromPage() {
        return $(By.xpath("//div[@class='phand-about__text']"));
    }

    SelenideElement ausLogo() {
        return $(By.xpath("//div[@class='phand-about__price']"));
    }

    SelenideElement titleForImage() {
        return $(By.xpath("//div[@class='return-rules__title']"));
    }

    SelenideElement instruction() {
        return $(By.xpath("//div[@class='instruction']"));
    }

    SelenideElement mainImage() {
        return $(By.xpath("//img[@class='return-rules__infograph-img']"));
    }

    SelenideElement returnPolicy() {
        return $(By.cssSelector(".return-policy"));
    }

    SelenideElement formLink() {
        return $(By.cssSelector(".anchor-on-form"));
    }

    SelenideElement categoriesPfandBlock() {
        return $(By.cssSelector(".ccollateral-parts__list"));
    }

    SelenideElement allCategoriesButton() {
        return $(By.cssSelector(".ccollateral-parts__list-more-btn"));
    }

    ElementsCollection pfandAllCategories() {
        return $$(".ccollateral-parts__list-item");
    }

    ElementsCollection categoriesWithDeposits() {
        return $$(By.cssSelector(".ccollateral-parts__list>div>div>img"));
    }

    SelenideElement randomCategoryTitle() {
        return $(By.xpath("//*[@class='ccollateral-parts__list-item active']/span"));
    }

    SelenideElement openedCategoryTitle() {
        return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div/span"));
    }

    SelenideElement categoryImage() {
        return $x("//div[@class='collateral-parts__desc-item active']//div[@class='fancybox-product-image']/img");
    }

    SelenideElement openedCategoryDescription() {
        return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div[2]/div[1]"));
    }

    SelenideElement requirementForPartsBlock() {
        return $(By.cssSelector(".ccollateral-parts__desc"));
    }

    SelenideElement categoriesList() {
        return $(By.cssSelector(".collateral-parts__desc-list"));
    }

    SelenideElement requirementTitle() {
        return $(By.cssSelector(".collateral-parts__desc-title"));
    }

    SelenideElement depositRefundBlock() {
        return $(By.cssSelector(".ccollateral-parts__form-wrapper"));
    }

    SelenideElement titleFromDepositRefundBlock() {
        return $(By.cssSelector(".ccollateral-parts__form-title"));
    }

    SelenideElement informTextFromDepositRefundBlock() {
        return $(By.cssSelector(".ccollateral-parts__form-subtitle"));
    }

    SelenideElement depositRefundForm() {
        return $(By.cssSelector(".formular"));
    }

    SelenideElement plzTooltip() {
        return $(By.xpath("//a[@class='info_link_index']"));
    }

    SelenideElement closePlzTooltip() {
        return $(By.cssSelector(".closeInfoIndex"));
    }

    SelenideElement numberTooltip() {
        return $(By.cssSelector(".info_link_order"));
    }

    SelenideElement closeNumbetTooltip() {
        return $(By.cssSelector(".closeInfoOrder"));
    }

    SelenideElement plzSearchButton() {
        return $(By.xpath("//*[@class='row button']/a"));
    }

    SelenideElement popupError() {
        return $(By.id("popup_update"));
    }

    SelenideElement closePopupButton() {
        return $(By.cssSelector(".buttons-inner>a"));
    }
}
