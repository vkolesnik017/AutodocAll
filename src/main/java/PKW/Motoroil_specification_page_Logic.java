package PKW;

import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_specification_page_Logic extends Motoroil_specification_page {

    @Step("presence of bread crumbs block. Motoroil_specification_page")
    public Motoroil_specification_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_specification_page")
    public Motoroil_specification_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("ACEA A1");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_specification_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_specification_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_specification_page")
    public Motoroil_specification_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence of Relinking blocks. Motoroil_specification_page")
    public Motoroil_specification_page_Logic presenceOfRelinkingBlocks(int expectedSize) {
        relinkingBlocks().get(0).shouldBe(visible);
        relinkingBlocks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check main elements of Relinking blocks. Motoroil_specification_page")
    public Motoroil_specification_page_Logic checkElementsOfRelinkingBlocks() {
        for (int i = 0; i < relinkingBlocks().size(); i++) {
            titleOfRelinkingBLocks(i + 1).shouldBe(visible);
            contentPartOfRelinkingBLocks(i + 1).shouldBe(visible);
        }
        return this;
    }


    @Step("check transition by click in Relinking block. Motoroil_specification_page")
    public Motoroil_specification_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        DataBase db = new DataBase();
        String currentMainHeadline = mainHeadline().getText();
        clickOnValueFromFirstRelinkingBlock(0).waitForChangingOfMainHeadline(currentMainHeadline);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_specification2"));
        back();
        clickOnValueFromSecondRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_viscosity2"));
        back();
        clickOnValueFromThirdRelinkingBlock(0);
        checkingContainsUrl(db.getRouteByRouteName("DE", "motoroil_brand2"));
        return this;
    }

    @Step("click on value from First relinking block. Motoroil_specification_page")
    public Motoroil_specification_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).click();
        return page(Motoroil_specification_page_Logic.class);
    }

    @Step("click on value from Second relinking block. Motoroil_specification_page")
    public Motoroil_viscosity_page_Logic clickOnValueFromSecondRelinkingBlock(int position) {
        linksOfRelinkingBlocks(2).get(position).shouldBe(visible).click();
        return page(Motoroil_viscosity_page_Logic.class);
    }

    @Step("click on value from Third relinking block. Motoroil_specification_page")
    public Motoroil_Brand_page_Logic clickOnValueFromThirdRelinkingBlock(int position) {
        linksOfRelinkingBlocks(3).get(position).shouldBe(visible).click();
        return page(Motoroil_Brand_page_Logic.class);
    }

    @Step("waitint for changing of Main headline. Motoroil_specification_page")
    public Motoroil_specification_page_Logic waitForChangingOfMainHeadline(String headline) {
        mainHeadline().shouldNotHave(exactText(headline));
        return this;
    }

    @Step("presence of selector. Motoroil_specification_page")
    public Motoroil_specification_page_Logic presenceOfSelector() {
        selector().shouldBe(visible);
        return this;
    }

    @Step("select vehicle in selector. Motoroil_specification_page")
    public Car_parts_motoroil_page_Logic selectVehicleInSelector(String marke, String model, String motor) {
        presenceOfSelector();
        markeFieldInSelector().selectOptionByValue(marke);
        modelFieldInSelector().selectOptionByValue(model);
        motorFieldInSelector().selectOptionByValue(motor);
        btnSearchOfSelector().click();
        return page(Car_parts_motoroil_page_Logic.class);
    }


}