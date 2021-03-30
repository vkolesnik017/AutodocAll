package ATD;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.*;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.sizeLessThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
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
        for (int i = 0; i < topParentBlocks().size(); i++) {
            imageOfTopParentBlock().get(i).shouldBe(visible);
            titleOfTopParentBlock().get(i).shouldBe(visible);
        }
        for (int i = 1; i <= topParentBlocks().size(); i++) {
            topChildLinks(i).shouldHave(sizeGreaterThan(0)).shouldHave(sizeLessThan(4));
        }
        return this;
    }

    @Step(" check transition by click on TOP elements .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkTransitionByClickOnTopElements() throws SQLException {
        clickOnIconOfParentCategory();
        checkingContainsUrl(new DataBase("ATD").getRouteByRouteName("DE", "moto_parent_category_maker3"));
        back();
        clickOnChildCategory();
        return this;
    }

    @Step("click on icon of parent category .Moto_Categories_maker_page")
    public Moto_Parent_Category_maker_page_Logic clickOnIconOfParentCategory() {
        imageOfTopParentBlock().get(1).shouldBe(visible).click();
        return page(Moto_Parent_Category_maker_page_Logic.class);
    }

    @Step("click on child category .Moto_Categories_maker_page")
    public Moto_Category_maker_page_Logic clickOnChildCategory() {
        topChildLinks(2).get(0).shouldBe(visible).click();
        return page(Moto_Category_maker_page_Logic.class);
    }


    @Step("presence of main headline block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfMainHeadlineBlock() {
        mainHeadline().shouldBe(visible);
        return this;
    }

    @Step("presence of brand icon in Headline .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfBrandIconInHeadline() {
        iconOfBrandInHeadline().shouldBe(visible);
        return this;
    }

    @Step("get count of models from title of models block .Moto_Categories_maker_page")
    public int getCountOfModelsFromTitle() {
        int countOfModels = Integer.parseInt(headlineOfModelsBlock().getText().replaceAll("[^0-9]", ""));
        return countOfModels;
    }

    @Step("comparing quantity of models .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic comparingQuantityOfModels(int expectedCountOfModels) {
        linksOfModels().shouldHaveSize(expectedCountOfModels);
        return this;
    }

    @Step("presence of bread crumbs block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbs().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("check components of model block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkComponentsOfModelBlock() {
        modelBlock().shouldBe(visible);
        if (linkMoreOfModelBlock().isDisplayed()) {
            linkMoreOfModelBlock().click();
        }
        for (int i = 0; i < linksOfMotoModels().size(); i++) {
            imageOfMotoModels().get(i).shouldBe(visible);
            titleOfMotoModels().get(i).shouldBe(visible);
            yearOfMotoModels().get(i).shouldHave(text("ab"));
        }
        return this;
    }

    @Step("presence title of models block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfModelsTitle(String title) {
        titleOfModelsBlock().shouldBe(visible).shouldHave(text(title));
        return this;
    }

    @Step("presence of models block .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfModelsBlock() {
        modelBlock().shouldBe(visible);
        linksOfModels().shouldHave(sizeGreaterThan(1));
        return this;
    }


    @Step("click on motorcycle model  .Moto_Categories_maker_page")
    public Moto_Catalog_model_page_Logic clickOnMotoModel(int position) {
        linksOfModels().get(position).shouldBe(visible).click();
        return page(Moto_Catalog_model_page_Logic.class);
    }

    @Step("presence of headline at TOP parent categories .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfHeadlineAtTopParentCategories() {
        headlineOfTopParentCategories().shouldBe(visible);
        return this;
    }

    @Step("check count of TOP products .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkCountOfTopProducts() {
        topProducts().shouldHaveSize(10);
        return this;
    }

    @Step("presence of main brand image .Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic presenceOfMainBrandImage() {
        mainImageBlock().shouldBe(visible);
        mainImageOfBrand().shouldBe(visible);
        int width = mainImageOfBrand().getSize().getWidth();
        int height = mainImageOfBrand().getSize().getHeight();
        Assert.assertEquals(width, 170);
        Assert.assertEquals(height, 170);
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page and go back. Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic loginToProfilePlusPageAndBack(String email) {
        loginAndTransitionToProfilePlusPage(email).visibilityOfUsersName();
        back();
        return this;
    }

    @Step("update of page. Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic updateOfPage() {
        refresh();
        return this;
    }

    @Step("Login in header with mail {mail} and transition to profile plus page. Moto_Categories_maker_page")
    public Profile_plus_page_Logic loginAndTransitionToProfilePlusPage(String mail) {
        loginBtnInHeader().click();
        mailFieldLogin().setValue(mail);
        passFieldLogin().setValue(password);
        submitBtnLogin().click();
        new Profile_page().nameOfClient().shouldBe(visible);
        return page(Profile_plus_page_Logic.class);
    }

    @Step("update of page. Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkCountOfVehicleInIconOfGarage(String expectedCountOfVehicle) {
        countOfVehicleInIconOfGarageInHeader().shouldBe(visible).shouldHave(exactText(expectedCountOfVehicle));
        return this;
    }

    @Step("click on Garage icon in header. Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic clickOnGarageIconInHeader() {
        garageIconInHeader().shouldBe(visible).click();
        popUpOfGarageInHeader().shouldBe(visible);
        return this;
    }

    @Step("click on Garage icon in header. Moto_Categories_maker_page")
    public Moto_Catalog_page_Logic selectMotoInGaragePopUp(String idOfVehicle) {
        idOfVehicleInGaragePopUp(idOfVehicle).shouldBe(visible).click();
        return page(Moto_Catalog_page_Logic.class);
    }

    @Step("check Seo Text Block. Moto_Categories_maker_page")
    public Moto_Categories_maker_page_Logic checkSeoTextBlock(String value) throws SQLException {
        String frontText = seoText().shouldBe(visible).getText().replaceAll("\\W", "");
        String bdText = new DataBase("ATD").getTranslate("seo_text", "DE", value).replaceAll("\\W", "");
        Assert.assertEquals(frontText, bdText);
        return this;
    }
}
