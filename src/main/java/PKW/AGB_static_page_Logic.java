package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.closeWindow;
import static com.codeborne.selenide.Selenide.switchTo;

public class AGB_static_page_Logic extends AGB_static_page {
    @Step("Checks the clickable link, pdf download and acrobat reader buttons, text block visibility, country list and delivery time blocks. AGB_static_page")
    public AGB_static_page_Logic checkElementsOnThePage() {

        blockAGBText().shouldBe(visible);
        Assert.assertFalse(blockAGBText().text().isEmpty());
        countryListBlockAgb().shouldBe(visible);
        Assert.assertFalse(countryListBlockAgb().text().isEmpty());
        deliveryTimeBlockAgb().shouldBe(visible);
        Assert.assertFalse(deliveryTimeBlockAgb().text().isEmpty());
        return this;
    }

    public AGB_static_page_Logic checkDownloadButtons() {

        CommonMethods commonMethods = new CommonMethods();

        pdfDownloadButton().shouldHave(attribute("class","link"));
        pdfDownloadButton().shouldHave(attribute("url", "pdf/agb"));
        acrobatReaderButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/?loc=de");
        closeWindow();
        switchTo().window(0);
        return this;
    }

//    public AGB_static_page_Logic checkingLinksInTheText() {
//
//        CommonMethods commonMethods = new CommonMethods();
//
////        payPalLinkZahlung().click();
////        commonMethods.checkingUrlAndCloseTab("/de/home/");
//
//
//        return this;
//    }

    public AGB_static_page_Logic checkingFlagsAndCountriesInTheCountryList () {
        for (int i = 0; i < 55; i++) {
            imagesCountryFlag().get(i).shouldBe(visible);
            Assert.assertFalse(countryPriceOne().text().isEmpty());
        }
        return this;
    }

    public AGB_static_page_Logic checkingPriceInTheCountryList () {
        for (int i = 0; i < 55; i++) {
            countryPrice().get(i).shouldBe(visible);
            Assert.assertFalse(countryPriceOne().text().isEmpty());
        }
        return this;
    }

}








