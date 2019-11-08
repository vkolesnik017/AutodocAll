package ATD.QASYS_252_StaticPage;


import ATD.Main_page;
import ATD.SetUp;
import ATD.Versand_static_page;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_309_StaticPage_versand {
    private Main_page mainPage = new Main_page();
    private Versand_static_page versandStaticPage = new Versand_static_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Flaky
    @Owner(value = "Oleg Romanyuta")
    @Test(dataProvider = "route")
    public void checkVersandPageElements(String route) {
        open(route);
        closeCookiesFooterMessage();
        mainPage.clickVersand();
        versandStaticPage.topBlockLeft().shouldBe(visible);
        versandStaticPage.topBlockRight().shouldBe(visible);
        versandStaticPage.deliveryPriceBlock().shouldBe(visible);
        versandStaticPage.deliveryCompanyImages().shouldBe(visible);
        versandStaticPage.countyPriceDelivery().shouldBe(visible);
        versandStaticPage.vatBlock().shouldBe(visible);
        versandStaticPage.allCountriesButton().click();
        versandStaticPage.allCountriesBlock().shouldBe(visible);
        versandStaticPage.allCountriesButton().click();
        versandStaticPage.allCountriesBlock().shouldNotBe(visible);
        versandStaticPage.textDelivery().shouldBe(visible);
        versandStaticPage.greenDeliveryBlock().shouldBe(visible);
        versandStaticPage.redDeliveryBlock().shouldBe(visible);
        versandStaticPage.deliveryLink().shouldBe(visible);
        versandStaticPage.recommendationBlock().shouldBe(visible);
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.deliveryCheckbox(), versandStaticPage.deliveryCheckboxClick());
        versandStaticPage.soDays().shouldHave(text("14"));
        versandStaticPage.soDaysBig().shouldHave(text("200"));
        versandStaticPage.soTime().shouldHave(text("14"));
        versandStaticPage.soPrice1().shouldHave(text("0"));
        versandStaticPage.soPrice2().shouldHave(text("2,99"));
        versandStaticPage.soDays2().shouldHave(text("14"));
        versandStaticPage.soDays3().shouldHave(text("200"));
        versandStaticPage.soText().shouldBe(visible);
        versandStaticPage.soReturnText().shouldBe(visible);
        versandStaticPage.tyresDelivery().shouldBe(visible);
        versandStaticPage.shippingBlock().shouldBe(visible);
        versandStaticPage.tabUnfoldingButton().click();
        versandStaticPage.fullTab1().shouldBe(visible);
        versandStaticPage.shippingTab2().click();
        versandStaticPage.countryBlock().shouldBe(visible);
        versandStaticPage.chooseDeliveryTitle().shouldBe(visible);
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.glsCheckbox(), versandStaticPage.glsCheckboxClick());
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.dhlCheckbox(), versandStaticPage.dhlCheckboxClick());
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.noxCheckbox(), versandStaticPage.noxCheckboxClick());
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.dpdCheckbox(), versandStaticPage.dpdCheckboxClick());
        versandStaticPage.checkUncheckCheckbox(versandStaticPage.pnordCheckbox(), versandStaticPage.pnordCheckboxClick());
        versandStaticPage.chooseDeliveryInput().sendKeys("Test Delivery");
        versandStaticPage.chooseDeliveryButton().click();
        versandStaticPage.chooseDeliveryAnswerText().shouldHave(text("Danke für Ihre Antwort! Wir tun unser Bestes, um Ihnen Ersatzteile auf den bequemsten Weg zu liefern."));
        versandStaticPage.chooseDeliveryBlock().shouldNotBe(visible);
        versandStaticPage.chooseDeliveryInput().shouldNotBe(visible);
    }
}
