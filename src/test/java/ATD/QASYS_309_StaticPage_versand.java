package ATD;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
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
        return new CommonMethods().setUpShop("prod", "DE");
    }

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
        close();
    }
}
