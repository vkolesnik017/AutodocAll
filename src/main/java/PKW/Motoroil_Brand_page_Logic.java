package PKW;

import Common.DataBase;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static Common.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Motoroil_Brand_page_Logic extends Motoroil_Brand_page {

    @Step("presence of bread crumbs block. Motoroil_Brand_page")
    public Motoroil_Brand_page_Logic presenceOfBreadCrumbsBlock() {
        breadCrumbsBlock().shouldBe(visible);
        return this;
    }

    @Step("check transition by click on links of breadcrumbs. Motoroil_Brand_page")
    public Motoroil_Brand_page_Logic checkTransitionByClickOnLinksOfBreadCrumbs() throws SQLException {
        checkFirstLinkOfBreadCrumbs().presenceOfTecDocCatalog();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "parts"));
        back();
        checkSecondLinkOfBreadCrumbs().presenceOfBrandsBlock();
        checkingContainsUrl(new DataBase("PKW").getRouteByRouteName("DE", "motoroil"));
        back();
        presenceOfBreadCrumbsBlock().checkThirdLinkOfBreadCrumbs("MOTUL");
        return this;
    }

    @Step("check First link of breadCrumbs. Motoroil_Brand_page")
    public Parts_page_Logic checkFirstLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(0).click();
        return page(Parts_page_Logic.class);
    }

    @Step("check Second link of breadCrumbs. Motoroil_Brand_page")
    public Motoroil_page_Logic checkSecondLinkOfBreadCrumbs() {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(1).click();
        return page(Motoroil_page_Logic.class);
    }

    @Step("check Third link of breadCrumbs. Motoroil_Brand_page")
    public Motoroil_Brand_page_Logic checkThirdLinkOfBreadCrumbs(String text) {
        breadCrumbsBlock().shouldBe(visible);
        linksOfBreadCrumbsBlock().get(2).shouldNotHave(attribute("href")).shouldHave(text(text));
        return this;
    }


    @Step("presence Of characteristic in main Headline. Motoroil_Brand_page")
    public Motoroil_Brand_page_Logic presenceOfCharacteristicInMainHeadline() {
        String characteristicFromUrl=getCharacteristicFromUrl();
        mainHeadline().shouldHave(text(characteristicFromUrl));
        return this;
    }

    @Step("presence Of characteristic in main Headline. Motoroil_Brand_page")
    public String getCharacteristicFromUrl() {
        String urlPart = url().replace(url().substring(url().lastIndexOf("/")), "");
        String characteristicFromUrl = url().replace(urlPart + "/", "").replace("-", " ").toUpperCase();
        return characteristicFromUrl;
    }
}
