package ATD;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static ATD.CommonMethods.pageReload;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Tyres_maker_page_Logic extends Tyres_maker_page {
    @Step("check payment methods Block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkPaymentMethodsBlock() {
        paymentMethodsBlock().shouldBe(visible);
        for (SelenideElement e : iconOfPaymentMethods()) {
            e.shouldBe(visible);
        }
        return this;
    }

    @Step("display of advantages block. Tyres_maker_page")
    public Tyres_maker_page_Logic displayAdvantagesBlock() {
        advantagesBlock().shouldBe(visible);
        return this;
    }

    @Step("check Titles Of Advantages Block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkTitlesOfAdvantagesBlock() {
        titlesOfAdvantagesBlock().shouldHaveSize(4);
        titlesOfAdvantagesBlock().get(0).shouldHave(exactText("Umfangreiches Sortiment"));
        titlesOfAdvantagesBlock().get(1).shouldHave(exactText("Rückgabe-Garantie"));
        titlesOfAdvantagesBlock().get(2).shouldHave(exactText("Versandkostenfrei"));
        titlesOfAdvantagesBlock().get(3).shouldHave(exactText("Sichere Zahlungen"));
        return this;
    }

    @Step("check Elements Of Advantages Block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkElementsOfAdvantagesBlock() {
        for (int i = 0; i < titlesOfAdvantagesBlock().size(); i++) {
            numberingOfAdvantageBlocks().get(i).shouldBe(visible).shouldNotBe(empty);
            infoTextOfAdvantageBlocks().get(i).shouldBe(visible).shouldNotBe(empty);
            iconOfAdvantageBlocks().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("display сar brands block. Tyres_maker_page")
    public Tyres_maker_page_Logic displayCarBrandsBlock() {
        carBrandsBlock().shouldBe(visible);
        return this;
    }

    @Step("check Count Of Car In Car Brands Block. Tyres_maker_page")
    public Tyres_maker_page_Logic checkCountOfCarInCarBrandsBlock() {
        linksInCarBrandsBlock().shouldHaveSize(6);
        for (int i = 0; i < logoInCarBrandsBlock().size(); i++) {
            logoInCarBrandsBlock().get(i).shouldBe(visible);
        }
        String currentCar = url().replaceAll(".+\\/", "");
        List<String> allCarBrands = linksInCarBrandsBlock().stream().map(n -> n.attr("href").replaceAll(".+\\/", "")).collect(Collectors.toList());
        Assert.assertFalse(allCarBrands.contains(currentCar));
        return this;
    }

    @Step("click On Car Brand. Tyres_maker_page")
    public Tyres_maker_page_Logic clickOnCarBrand() {
        String firstCarBrand = linksInCarBrandsBlock().get(0).attr("href").replaceAll(".+\\/", "");
        linksInCarBrandsBlock().get(0).click();
        pageReload();
        List<String> allCarBrands = linksInCarBrandsBlock().stream().map(n -> n.attr("href").replaceAll(".+\\/", "")).collect(Collectors.toList());
        Assert.assertFalse(allCarBrands.contains(firstCarBrand));
        return this;
    }

    @Step("check Top Tyres. Tyres_maker_page")
    public Tyres_maker_page_Logic checkTopTyres() {
        for (int i = 0; i < typeOfSeasonsInTopBlock().size(); i++) {
            typeOfSeasonsInTopBlock().get(i).shouldBe(visible).click();
            checkSeason();
        }
        return this;
    }

    @Step("check season. Tyres_maker_page")
    public Tyres_maker_page_Logic checkSeason() {
        for (int i = 0; i < visibleTitlesOfTopTyres().size(); i++) {
            String select = Keys.chord(Keys.CONTROL, Keys.RETURN);
            visibleTitlesOfTopTyres().get(i).sendKeys(select);
            switchTo().window(1);
            new Tyre_item_page_Logic().displayActiveBtnAddToBasket();
            closeWindow();
            switchTo().window(0);
        }
        return this;
    }

    @Step("Click season button and check transition. Tyres_maker_page")
    public TyresListing_page_Logic clickSeasonButtonAndCheckTransition(int position) {
        tyreSeasons().get(position).hover().click();
        return page(TyresListing_page_Logic.class);
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsBlockVisibility() {
        new Tyres_feature_page_Logic().checkPopularBrandsBlockVisibility();
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic clickSecondPageInBrandSlider() {
        new Tyres_feature_page_Logic().clickSecondPageInBrandSlider();
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsSliderFirstPosition(int numberOfBrands, int numberOfBrandsInFirstSlide,
                                                                        ElementsCollection brandsInSlider, String attrName) {
        new Tyres_page_Logic().checkPopularBrandsSliderFirstPosition(numberOfBrands, numberOfBrandsInFirstSlide, brandsInSlider, attrName);
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsSliderSecondPosition(int numberOfBrands, int numberOfBrandsInFirstSlide,
                                                                         ElementsCollection brandsInSlider, String attrName) {
        new Tyres_page_Logic().checkPopularBrandsSliderSecondPosition(numberOfBrands, numberOfBrandsInFirstSlide, brandsInSlider, attrName);
        return this;
    }

    @Step("Check tyres season block presence. Tyres_maker")
    public Tyres_maker_page_Logic checkTyresSeasonBlockPresence() {
        seasonBlock().shouldBe(visible);
        seasonsInSeasonBlock().shouldHaveSize(3);
        return this;
    }

    @Step("check default value of width field. Tyres_maker")
    public Tyres_maker_page_Logic displayingVehicleLinksInSelector() {
        for (int i = 0; i < vehicleLinksInSelector().size(); i++) {
            vehicleLinksInSelector().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Check all elements from car-group block. Tyres_maker")
    public Tyres_maker_page_Logic checkAllElementsFromCarGroupBlock() {
        carGroupBlock().scrollIntoView("{block: \"center\"}").shouldBe(visible);
        titleCarGroupBlock().shouldHave(exactText("Reifen für alle BMW Modelle"));
        linkModelsBlocks().shouldHaveSize(4);
        linkModels().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("Click and check correct transition by link from car-group block. Tyres_maker")
    public Tyres_model_page_Logic clickOnLinkFromCarGroupBlock() {
        String nameLink = linkModel().getText();
        linkModel().shouldBe(visible).click();
        waitWhileRouteBecomeExpected("tyres_model");
        String nameTitle = new Tyres_model_page_Logic().getTextTitlePage();
        Assert.assertTrue(nameTitle.contains(nameLink));
        return page(Tyres_model_page_Logic.class);
    }



}
