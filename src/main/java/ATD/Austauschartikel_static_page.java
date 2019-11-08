package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.Random;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Austauschartikel_static_page {

    public SelenideElement logo() {
        return $(By.cssSelector(".logo>a>img"));
    }

    public SelenideElement title() {
        return $(By.xpath("//div[@class='phand-about__title']"));
    }

    public SelenideElement mainText() { return $(By.xpath("//div[@class='phand-about__text']")); }

    public SelenideElement ausLogo() { return $(By.xpath("//div[@class='phand-about__price']")); }

    public SelenideElement title2() { return $(By.xpath("//div[@class='return-rules__title']")); }

    public SelenideElement instruction() { return $(By.xpath("//div[@class='instruction']")); }

    public SelenideElement mainImage() { return $(By.xpath("//img[@class='return-rules__infograph-img']")); }

    public SelenideElement returnPolicy() { return $(By.cssSelector(".return-policy")); }

    public SelenideElement formLink() { return $(By.cssSelector(".anchor-on-form")); }

    public SelenideElement categoriesPfandBlock() { return $(By.cssSelector(".ccollateral-parts__list")); }

    public SelenideElement allCategoriesButton() { return $(By.cssSelector(".ccollateral-parts__list-more-btn")); }

    public ElementsCollection pfandAllCategories() { return $$(".ccollateral-parts__list-item"); }

    public SelenideElement randomCategory() {
        {
           ElementsCollection categoriesList = $$(".ccollateral-parts__list>div>div>img");
           Random random = new Random();
           return categoriesList.get(random.nextInt(categoriesList.size()));
        }
    }

    public SelenideElement randomCategoryTitle() { return $(By.xpath("//*[@class='ccollateral-parts__list-item active']/span")); }

    public SelenideElement openedCategoryTitle() { return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div/span")); }

    public SelenideElement openedCategoryDescription() { return $(By.xpath("//*[@class='collateral-parts__desc-item active']/div[2]/div[1]")); }

    public SelenideElement requirmentsBlock() { return $(By.cssSelector(".collateral-parts__desc-title")); }

    public SelenideElement plzTooltip() { return $(By.cssSelector(".info_link_index")); }

    public SelenideElement closePlzTooltip() { return $(By.cssSelector(".closeInfoIndex")); }

    public SelenideElement numberTooltip() { return $(By.cssSelector(".info_link_order")); }

    public SelenideElement closeNumbetTooltip() { return $(By.cssSelector(".closeInfoOrder")); }

    public SelenideElement plzSearchButton() { return $(By.xpath("//*[@class='row button']/a")); }

    public SelenideElement popupError() { return $(By.id("popup_update")); }

    public SelenideElement closePopupButton() { return $(By.cssSelector(".buttons-inner>a")); }
}
