package ATD.QASYS_252_StaticPage;


import ATD.Austauschartikel_static_page;
import ATD.Main_page;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_312_StaticPage_austauschartikel {
    private Main_page mainPage = new Main_page();
    private Austauschartikel_static_page austauschartikelStaticPage = new Austauschartikel_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Oleg Romanyuta")
    @Flaky
    @Description(value = "Test checks elements on austauschartikel page")
    public void checkAustauschartikelPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickAustauschartikel();
        austauschartikelStaticPage.logo().shouldBe(visible);
        austauschartikelStaticPage.title().shouldBe(visible);
        austauschartikelStaticPage.mainText().shouldBe(visible);
        austauschartikelStaticPage.ausLogo().shouldBe(visible);
        austauschartikelStaticPage.title2().shouldBe(visible);
        austauschartikelStaticPage.instruction().shouldBe(visible);
        austauschartikelStaticPage.mainImage().shouldBe(visible);
        austauschartikelStaticPage.returnPolicy().shouldBe(visible);
        austauschartikelStaticPage.formLink().shouldBe(visible);
        austauschartikelStaticPage.plzTooltip().click();
        austauschartikelStaticPage.closePlzTooltip().click();
        austauschartikelStaticPage.numberTooltip().click();
        austauschartikelStaticPage.closeNumbetTooltip().click();
        austauschartikelStaticPage.categoriesPfandBlock().shouldBe(visible);
        austauschartikelStaticPage.allCategoriesButton().click();
        austauschartikelStaticPage.pfandAllCategories().shouldHave(size(34));
        austauschartikelStaticPage.allCategoriesButton().click();
        austauschartikelStaticPage.requirmentsBlock().shouldBe(visible);
        austauschartikelStaticPage.plzSearchButton().click();
        austauschartikelStaticPage.popupError().shouldBe(visible);
        austauschartikelStaticPage.closePopupButton().click();

        //Check opened category
        austauschartikelStaticPage.randomCategory().click();
        austauschartikelStaticPage.randomCategoryTitle().shouldHave(text(austauschartikelStaticPage.openedCategoryTitle().getText()));
        austauschartikelStaticPage.openedCategoryDescription().shouldBe(visible);
        austauschartikelStaticPage.openedCategoryTitle().click();
    }
}
