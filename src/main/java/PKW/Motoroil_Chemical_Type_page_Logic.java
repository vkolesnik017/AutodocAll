package PKW;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;

public class Motoroil_Chemical_Type_page_Logic extends Motoroil_Chemical_Type_page {

    @Step("presence of bread crumbs block. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase().getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("Synthetische");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_Chemical_Type_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_Chemical_Type_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }

    @Step("presence of Relinking blocks. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic presenceOfRelinkingBlocks(int expectedSize) {
        relinkingBlocks().get(0).shouldBe(visible);
        relinkingBlocks().shouldHaveSize(expectedSize);
        return this;
    }

    @Step("check main elements of Relinking blocks. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic checkElementsOfRelinkingBlocks() {
        for (int i = 0; i < relinkingBlocks().size(); i++) {
            titleOfRelinkingBLocks(i + 1).shouldBe(visible);
            contentPartOfRelinkingBLocks(i + 1).shouldBe(visible);
        }
        return this;
    }

    @Step("get attribute from link in Relinking block. Motoroil_Chemical_Type_page")
    public String getAttributeFromLink(ElementsCollection list, int position) {
        String urlFromLink = list.get(position).getAttribute("href");
        String urlPart = urlFromLink.replace(urlFromLink.substring(urlFromLink.lastIndexOf("/")), "");
        String cutUrlPart = urlPart.replace(urlPart.substring(urlPart.lastIndexOf("/")), "");
        String expectedPart = urlFromLink.replace(cutUrlPart + "/", "");
        return expectedPart;
    }

    @Step("check transition by click in Relinking block. Motoroil_Chemical_Type_page")
    public Motoroil_Chemical_Type_page_Logic checkTransitionByClickInRelinkingBlock() throws SQLException {
        String firstBlock = getAttributeFromLink(linksOfRelinkingBlocks(1), 0);
        clickOnValueFromFirstRelinkingBlock(0);
        checkingContainsUrl(firstBlock);
         return this;
    }

    @Step("click on value from First relinking block. Motoroil_Chemical_Type_page")
    public Motoroil_Maker_Group_page_Logic clickOnValueFromFirstRelinkingBlock(int position) {
        linksOfRelinkingBlocks(1).get(position).shouldBe(visible).scrollIntoView("{block: \"center\"}").hover().click();
        return page(Motoroil_Maker_Group_page_Logic.class);
    }
}
