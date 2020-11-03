package PKW;

import Common.DataBase;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static PKW.CommonMethods.checkingContainsUrl;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class Versand_static_page_Logic extends Versand_static_page {

    @Step("Checking gdpr success with click checkbox in footer subscribe block. Versand_static_page")
    public String checkingGdprSuccessWithClickCheckboxInFooter(String qc) {
        String mail = qc + PKW.CommonMethods.mailRandom();
        subscriptionMailField().setValue(mail);
        subscriptionMailCheckbox().click();
        subscriptionButton().click();
        subscriptionSuccessPopup().shouldHave(text("Viel Spaß beim Shoppen!"));
        subscriptionPopupClose().click();
        return mail;
    }

    @Step(":From Versand_static_page")
    public Versand_static_page_Logic checkingDatenschutzerklarungLinkBehavior(SelenideElement orderDatenschutzerklarungLink) {
        new CommonMethods().checkingDatenschutzerklarungLinkBehavior(orderDatenschutzerklarungLink, "underline solid rgb(0, 0, 0)");
        return this;
    }

    @Step("Scrolling to footer subscribe block. Versand_static_page")
    public Versand_static_page_Logic scrollToFooterSubscribeBlock() {
        footerForm().scrollIntoView(false);
        footerForm().shouldBe(visible);
        return this;
    }

    @Step("Checking the visibility of the elements in the BOX block. Versand_static_page") //step 2 .
    public Versand_static_page_Logic checkingBoxBlock() {
        deliveryPageContent().shouldBe(visible);
        deliveryPageBox().shouldBe(visible);
        deliveryPageDeliveryIcons().shouldBe(visible);
        deliveryPageBoxCountryText().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxCountryText().text().isEmpty());
        deliveryPageBoxCountryFlag().shouldHave(attribute("alt", "Deutschland: lieferbedingungen"));
        deliveryPageBoxFeatureOne().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxFeatureOne().text().isEmpty());
        deliveryPageBoxFeatureTwo().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxFeatureTwo().text().isEmpty());
        deliveryPageBoxFeatureThree().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxFeatureThree().text().isEmpty());
        deliveryPageBoxFeatureText().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxFeatureText().text().isEmpty());
        deliveryPageBoxFeatureTextBottom().shouldBe(visible);
        Assert.assertFalse(deliveryPageBoxFeatureTextBottom().text().isEmpty());
        return this;
    }

    @Step("Checking the visibility of the elements in the delivery country block. Versand_static_page") //.
    public Versand_static_page_Logic checkingDeliveryCountryBlockButton() {
        countryBlockTitle().shouldBe(visible);
        Assert.assertFalse(countryBlockTitle().text().isEmpty());
        countryBlockItemsButton().click();
        countryBlockOtherCountry().shouldBe(visible);
        countryBlockItemsButton().click();
        countryBlockOtherCountry().shouldNotBe(visible);
        return this;
    }

    @Step("Checking the visibility of the elements in the Delivery Terms block. Versand_static_page") //.
    public Versand_static_page_Logic checkingBlockWithTermsOfTheDelivery() {

        CommonMethods commonMethods = new CommonMethods();

        iconCar().shouldBe(visible);
        iconLock().shouldBe(visible);
        iconTime().shouldBe(visible);
        deliveryTextBlock().shouldBe(visible);
        Assert.assertFalse(deliveryTextBlock().text().isEmpty());
        lieferzeitTextBlock().shouldBe(visible);
        Assert.assertFalse(lieferzeitTextBlock().text().isEmpty());
        lieferzeitBlockLink().click();
        commonMethods.checkingUrlAndCloseTab("/DE/Fachthemen/Zollkosten/zollkosten_node.html");
        aufLagerImage().shouldBe(visible);
        nichtAufLagerImage().shouldBe(visible);
        aufLagerTextBlock().shouldBe(visible);
        Assert.assertFalse(aufLagerTextBlock().text().isEmpty());
        nichtAufLagerTextBlock().shouldBe(visible);
        Assert.assertFalse(nichtAufLagerTextBlock().text().isEmpty());
        return this;
    }

    @Step("Checking the visibility of the elements in the FAQ block. Versand_static_page") //steps 4,5 .
    public Versand_static_page_Logic checkingFaqBlock() {
        faqTitleOne().shouldBe(visible);
        Assert.assertFalse(faqTitleOne().text().isEmpty());
        faqTitleSecond().shouldBe(visible);
        Assert.assertFalse(faqTitleSecond().text().isEmpty());
        deliveryFaqText().shouldBe(visible);
        Assert.assertFalse(deliveryFaqText().text().isEmpty());
        carImage().shouldBe(visible);
        returnTextTitle().shouldBe(visible);
        Assert.assertFalse(returnTextTitle().text().isEmpty());
        returnTextSubTitle().shouldBe(visible);
        Assert.assertFalse(returnTextSubTitle().text().isEmpty());
        returnCircle1().shouldBe(visible);
        returnCircle2().shouldBe(visible);
        returnCircle3().shouldBe(visible);
        questionBlockList().shouldBe(visible);
        Assert.assertFalse(questionBlockList().text().isEmpty());
        return this;
    }

    @Step("Checking the visibility of the elements in the Spergut Versand block. Versand_static_page") //step 6 .
    public Versand_static_page_Logic spergutVersandBlock() {
        heavyGoodsTextBlock().shouldBe(visible);
        blockSpergutOne().shouldBe(visible).shouldHave(attribute("class", "image"));
        Assert.assertFalse(blockSpergutOneCheckingText().text().isEmpty());
        blockSpergutTwo().shouldBe(visible).shouldHave(attribute("class", "image"));
        Assert.assertFalse(blockSpergutTwoCheckingText().text().isEmpty());
        return this;
    }

    @Step("Checking the visibility of the table in the Spergut Versand block. Versand_static_page") //step 7.
    public Versand_static_page_Logic spergutVersandTableFirstTab() {
        firstTab().shouldBe(visible);
        mehrButton().click();
        expandTextAfterMehr().shouldBe(visible);
        return this;
    }

    @Step("Checking the visibility of the table in the Spergut Versand block. Versand_static_page")  //step 7.
    public Versand_static_page_Logic spergutVersandTableSecondTab() {
        secondTab().click();
        listWithTheCountriesAndPriceTabTwo().shouldBe(visible);
        return this;
    }


    @Step("Checking the Back Button to the Main Page. Versand_static_page") //.
    public Versand_static_page_Logic checkingBackButton() throws SQLException {
        backButton().click();
        checkingContainsUrl(new DataBase("PKW").getFullRouteByRouteName("prod", "DE", "main"));
        return this;
    }

    @Step("Checking the Download pdf buttons. Versand_static_page") //.
    public Versand_static_page_Logic checkingDownloadButtons() {
        downloadPdfButton().shouldHave(attribute("href", "https://www.pkwteile.de/pdf/versand"));
        downloadAcrobatButton().click();
        switchTo().window(1);
        checkingContainsUrl("de/reader");
        closeWindow();
        switchTo().window(0);
        return this;
    }

    @Step("Check visibility for three icons about delivery. Versand_static_page") //step 4 .
    public Versand_static_page_Logic checkingTextNearTheThreeIcons() {
        for (int i = 0; i < featureBlockTextForIcon().size(); i++) {
            featureBlockTextForIcon().get(i).shouldBe(visible);
            Assert.assertFalse(featureBlockTextForOneIcon().text().isEmpty());
        }
        return this;
    }

    @Step("Click FAQ questions and check answers visibility. Versand_static_page")  //step 4.
    public Versand_static_page_Logic questionsAndAnswersFaq() {
        for (int i = 0; i < faqQuestions().size(); i++) {
            faqQuestions().get(i).click();
            for (int a = 0; a < faqAnswers().size(); a++) {
                faqAnswers().get(a).shouldBe(visible);
                Assert.assertFalse(faqAnswersOne().text().isEmpty());
                faqQuestionsActive().click();
                break;
            }
            faqAnswers().get(i).shouldNotBe(visible);
        }
        return this;
    }

    @Step("Check the three questions in the table. Versand_static_page")   //step 6.
    public Versand_static_page_Logic checkingTextInTheQuestionsBlock() {
        for (int i = 0; i < questionsBlockItem().size(); i++) {
            questionsBlockItem().get(i).shouldBe(visible);
            Assert.assertFalse(questionsBlockItemOne().text().isEmpty());
        }
        return this;
    }

    @Step("Check list with the parts and prices. Versand_static_page") //step 7.
    public Versand_static_page_Logic countryPriceWithParts() {
        for (int i = 0; i < 3; i++) {
            blocksTextWithPrice().get(i).shouldBe(visible);
            Assert.assertFalse(blocksTextWithPriceOne().text().isEmpty());
        }
        return this;
    }

    @Step("Check list with the parts and prices after clicking Mehr Button. Versand_static_page") //step 7.
    public Versand_static_page_Logic tableCountryPriceWithPriceTabOne() {
        for (int i = 0; i < deliveryPriceWithCountryMehr().size(); i++) {
            deliveryPriceWithCountryMehr().get(i).shouldBe(visible);
            Assert.assertFalse(deliveryPriceWithCountryMehrOne().text().isEmpty());
        }
        return this;
    }

    @Step("Check list with the flags on the second tab. Versand_static_page") //step 7.
    public Versand_static_page_Logic checkingFlagTabTwo() {
        for (int i = 0; i < secondTabContentImageFlag().size(); i++) {
            secondTabContentImageFlag().get(i).shouldBe(visible).shouldHave(attribute("alt"));
        }
        return this;
    }


    @Step("Check list with the text and prices on the second tab. Versand_static_page") //step 7.
    public Versand_static_page_Logic checkingTextAndPriceTabTwo() {
        for (int i = 0; i < blocksCountryPriceText().size(); i++) {
            blocksCountryPriceText().get(i).shouldBe(visible);
            Assert.assertFalse(blocksCountryPriceTextOne().text().isEmpty());
        }
        return this;
    }

    @Step("Checking the countries in the delivery country block and in the AWS. Versand_static_page")
    public Versand_static_page_Logic checkingCountriesOnSiteAndOnAWS(List<String> list) {
        ArrayList<String> siteCountry = new ArrayList<>();
        for (int i = 0; i < countryNameOnSite().size(); i++) {
            siteCountry.add(countryNameOnSite().get(i).getText());
        }
        siteCountry.add(countryInBox().getAttribute("alt").replace(": shipping", ""));
        Assert.assertEquals(list.size(), siteCountry.size());
        Collections.sort(siteCountry);
        Collections.sort(list);
        Assert.assertEquals(list, siteCountry);
        return this;
    }

    @Step("Checking main elements of countries. Versand_static_page")
    public Versand_static_page_Logic checkingMainElementsOfCountries() {
        countryBlockItemsButton().click();
        countryTable().scrollIntoView("{block: \"center\"}");
        for (int i = 0; i < countryItemForCount().size(); i++) {
            flagsOfCountries().get(i).shouldBe(visible);
            titleOfCountries().get(i).shouldBe(visible);
            pricesOfCountries().get(i).shouldBe(visible);
        }
        return this;
    }
}
