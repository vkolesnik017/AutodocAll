package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Austauschartikel_static_page {

    protected SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    protected SelenideElement title() {
        return $(By.xpath("//div[@class='phand-about__title']"));
    }

    public SelenideElement mainTextFromPage() { return $(By.xpath("//div[@class='phand-about__text']")); }

    protected SelenideElement ausLogo() { return $(By.xpath("//div[@class='phand-about__price']")); }

    protected SelenideElement title2() { return $(By.xpath("//div[@class='return-rules__title']")); }

    protected SelenideElement instruction() { return $(By.xpath("//div[@class='instruction']")); }

    protected SelenideElement mainImage() { return $(By.xpath("//img[@class='return-rules__infograph-img']")); }

    protected SelenideElement returnPolicy() { return $(By.cssSelector(".return-policy")); }

    protected SelenideElement formLink() { return $(By.cssSelector(".anchor-on-form")); }

    protected SelenideElement categoriesPfandBlock() { return $(By.cssSelector(".ccollateral-parts__list")); }

    protected SelenideElement allCategoriesButton() { return $(By.cssSelector(".ccollateral-parts__list-more-btn")); }

    protected ElementsCollection pfandAllCategories() { return $$(".ccollateral-parts__list-item"); }

    protected ElementsCollection categoriesWithDeposits() { return $$(By.cssSelector(".ccollateral-parts__list>div>div>img")); }

    protected SelenideElement randomCategoryTitle() { return $(By.xpath("//*[@class='ccollateral-parts__list-item active']/span")); }

    protected SelenideElement openedCategoryTitle() { return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div/span")); }

    protected SelenideElement openedCategoryDescription() { return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div[2]/div[1]")); }

    protected SelenideElement requirementForPartsBlock(){
        return $(By.cssSelector(".ccollateral-parts__desc"));
    }

    protected SelenideElement categoriesList() {
        return $(By.cssSelector(".collateral-parts__desc-list"));
    }

    protected SelenideElement requirementTitle() { return $(By.cssSelector(".collateral-parts__desc-title")); }

    protected SelenideElement depositRefundBlock(){
        return $(By.cssSelector(".ccollateral-parts__form-wrapper"));
    }

    protected SelenideElement titleFromDepositRefundBlock(){ return $(By.cssSelector(".ccollateral-parts__form-title")); }

    protected SelenideElement informTextFromDepositRefundBlock(){ return $(By.cssSelector(".ccollateral-parts__form-subtitle"));}

    public SelenideElement depositRefundForm(){
        return $(By.cssSelector(".formular"));
    }

    protected SelenideElement plzTooltip() { return $(By.cssSelector(".info_link_index")); }

    protected SelenideElement closePlzTooltip() { return $(By.cssSelector(".closeInfoIndex")); }

    protected SelenideElement numberTooltip() { return $(By.cssSelector(".info_link_order")); }

    protected SelenideElement closeNumbetTooltip() { return $(By.cssSelector(".closeInfoOrder")); }

    protected SelenideElement plzSearchButton() { return $(By.xpath("//*[@class='row button']/a")); }

    protected SelenideElement popupError() { return $(By.id("popup_update")); }

    protected SelenideElement closePopupButton() { return $(By.cssSelector(".buttons-inner>a")); }
}
