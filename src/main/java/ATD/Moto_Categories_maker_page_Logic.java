package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Categories_maker_page_Logic extends Moto_Categories_maker_page {

    @Step("get brand of  motorcycle from Url .Moto_Categories_maker_page")
    public String getBrandOfMotoFromUrl() {
        String brandOfMoto = url().replaceAll("(.*)\\/", "");
        return brandOfMoto;
    }

    @Step("presence brand of motorcycle in selector .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceBrandOfMotoInSelector(String brandOfMoto) {
        String brandOfMotoFromSelector = markeOfMotoInSelector().getSelectedText().replaceAll("[^A-z]", "");
        Assert.assertEquals(brandOfMotoFromSelector, brandOfMoto.replaceAll("-", "").toUpperCase());
        return this;
    }

    @Step(" selecting motorcycle in selector .Moto_Categories_maker_page")
    public Moto_Catalog_page_Logic selectMotoInSelector(String marke, String model, String motor) {
        markeOfMotoInSelector().selectOptionByValue(marke);
        modelFiledInSelector().selectOptionByValue(model);
        motorFiledInSelector().selectOptionByValue(motor);
        searchButton().click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step(" reset of motorcycle selector .Moto_Categories_maker_page")
    public Moto_Categories_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Categories_page_Logic.class);
    }


    @Step("check bread crumbs block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkBreadCrumbsBlock() {
        iconOfFirstLinksAtBreadCrumbs().shouldBe(visible);
        titleOfFirstLinksAtBreadCrumbs().shouldHave(text("KTM MOTORCYCLES"));
        linksOfBreadCrumbs().get(1).shouldHave(text("Modell Wählen")).shouldNotHave(attribute("href"));
        linksOfBreadCrumbs().get(2).shouldHave(text("Motorrad Wählen")).shouldNotHave(attribute("href"));
        linksOfBreadCrumbs().get(3).shouldHave(text("Teil Wählen")).shouldNotHave(attribute("href"));
        return this;
    }


    @Step("presence of TOP parent and child block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfTopParentAndChildBlock() {
        topParentChildBlock().shouldBe(visible);
        return this;
    }


    @Step("presence TOP parent block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceTopParentBlock() {
        topParentBlocks().shouldHave(sizeGreaterThan(1));
        return this;
    }


    @Step("check elements of TOP parent block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkElementsOfTopParentBlock() {
        for (int i=0;i<topParentBlocks().size();i++) {
            imageOfTopParentBlock().get(i).shouldBe(visible);
            titleOfTopParentBlock().get(i).shouldBe(visible);
        }
        for (int i=1;i<=topParentBlocks().size();i++) {
           topChildLinks(i).shouldHave(sizeGreaterThan(0)).shouldHave(sizeLessThan(4));
        }

        return this;
    }
}
