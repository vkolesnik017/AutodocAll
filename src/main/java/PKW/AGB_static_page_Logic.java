package PKW;

import Common.DataBase;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.sql.SQLException;
import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AGB_static_page_Logic extends AGB_static_page {

    @Step("Checks the elements and blocks on the page. AGB_static_page")
    public AGB_static_page_Logic checkElementsOnThePage() {

        blockAGBText().shouldBe(visible);
        Assert.assertFalse(blockAGBText().text().isEmpty());
        countryListBlockAgb().shouldBe(visible);
        Assert.assertFalse(countryListBlockAgb().text().isEmpty());
        deliveryTimeBlockAgb().shouldBe(visible);
        Assert.assertFalse(deliveryTimeBlockAgb().text().isEmpty());
        return this;
    }

    @Step("Checks the downloads buttons Acrobat reader and pdf download. AGB_static_page")
    public AGB_static_page_Logic checkDownloadButtons() {

        pdfDownloadButton().shouldHave(attribute("class", "link"));
        pdfDownloadButton().shouldHave(attribute("url", "pdf/agb"));
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/?loc=de");
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Checking links in the Text. AGB_static_page")
    public AGB_static_page_Logic checkingLinksInTheText() throws SQLException {

        CommonMethods commonMethods = new CommonMethods();

        autodocEmail().shouldHave(attribute("href", "mailto:info@autodoc.de"));
        zollLinkAgb().click();
        commonMethods.checkingUrlAndCloseTab("Fachthemen/Zollkosten/zollkosten_node.html");
        klarnaLinkAgbFirst().click();
        commonMethods.checkingUrlAndCloseTab("1.0/shared/content/legal/terms/27506/de_de/invoice?fee=0");
        klarnaLinkAgbSecond().click();
        commonMethods.checkingUrlAndCloseTab("1.0/shared/content/legal/terms/27506/de_de/consent");
        europaLinkAgbSecond().click();
        commonMethods.checkingUrlAndCloseTab("/consumers/odr/main/index.cfm?event=main.home.chooseLanguage");
        autodocLinkAgbText().hover().click();
        commonMethods.checkingUrlAndCloseTab(new DataBase().getFullRouteByRouteName("prod", "DE", "main"));
        back();
        return this;
    }

    @Step("Checking flags in the Country block. AGB_static_page")
    public AGB_static_page_Logic checkingFlagsInTheCountryList() {
        for (int i = 0; i < 55; i++) {
            imagesCountryFlag().get(i).shouldBe(visible);
        }
        return this;
    }

    @Step("Checking price displaying in the Country block. AGB_static_page")
    public AGB_static_page_Logic checkingPriceInTheCountryList() {
        for (int i = 0; i < 55; i++) {
            countryPrice().get(i).shouldBe(visible);
        }
        return this;
    }
}








