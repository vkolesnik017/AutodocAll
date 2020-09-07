package PKW;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;
import java.sql.SQLException;
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

    @Step("Checking the visibility of the elements in the BOX block. Versand_static_page") //good
    public Versand_static_page_Logic checkingBoxBlock() {
        deliveryPageContent().shouldBe(visible);
        deliveryPageBox().shouldBe(visible);
        deliveryPageDeliveryIcons().shouldBe(visible);
        deliveryPageBoxCountryText().shouldBe(visible);
        deliveryPageBoxCountryFlag().shouldHave(attribute("alt", "Deutschland: lieferbedingungen"));
        Assert.assertFalse(deliveryPageBoxCountryText().text().isEmpty());
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

    @Step("Checking the visibility of the elements in the delivery country block. Versand_static_page")
    public Versand_static_page_Logic checkingDeliveryCountryBlock() {  //good
        countryBlockTitle().shouldBe(visible);
        Assert.assertFalse(countryBlockTitle().text().isEmpty());
        countryBlockItemsButton().click();
        countryBlockOtherCountry().shouldBe(visible);
        Assert.assertEquals(countryItemForCount().size(), 57); //change on 57
        checkingMainElementsOfCountries();
        countryBlockItemsButton().click();
        countryBlockOtherCountry().shouldNotBe(visible);
       // countryBlockItemsButton().click();
        return this;
    }

    @Step("Checking main elements of countries. Versand_static_page")
    public Versand_static_page_Logic checkingMainElementsOfCountries() {  //good
     for (int i=0;i<countryItemForCount().size();i++){
         flagsOfCountries().get(i).shouldBe(visible);
         titleOfCountries().get(i).shouldBe(visible);
         pricesOfCountries().get(i).shouldBe(visible);
     }
        return this;
    }

    @Step("Checking the visibility of the elements in the Delivery Terms block. Versand_static_page")
    public Versand_static_page_Logic checkingBlockWithTermsOfTheDelivery() {  //good

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










    @Step("Checking the visibility of the elements in the FAQ block. Versand_static_page")
    public Versand_static_page_Logic checkingFaqBlock() { //good
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

    @Step("Checking the visibility of the elements in the Spergut Versand block. Versand_static_page")
    public Versand_static_page_Logic spergutVersandBlock() { //good
        heavyGoodsTextBlock().shouldBe(visible);
        blockSpergutOne().shouldBe(visible).shouldHave(attribute("class", "image"));
        Assert.assertFalse(blockSpergutOneCheckingText().text().isEmpty());
        blockSpergutTwo().shouldBe(visible).shouldHave(attribute("class", "image"));
        Assert.assertFalse(blockSpergutTwoCheckingText().text().isEmpty());
        return this;
    }

    @Step("Checking the visibility of the table in the Spergut Versand block. Versand_static_page")
    public Versand_static_page_Logic spergutVersandTableFirstTab() {  //good
        firstTab().shouldBe(visible);
        mehrButton().click();
        expandTextAfterMehr().shouldBe(visible);
        return this;
    }

    @Step("Checking the visibility of the table in the Spergut Versand block. Versand_static_page")
    public Versand_static_page_Logic spergutVersandTableSecondTab() { //good
        secondTab().click();
        listWithTheCountriesAndPriceTabTwo().shouldBe(visible);
        return this;
    }


    @Step("Checking the Back Button. Versand_static_page")
    public Versand_static_page_Logic checkingBackButton() throws SQLException {
        backButton().click();
        checkingContainsUrl(new DataBase().getFullRouteByRouteName("prod", "DE", "main"));
        return this;
    }

    @Step("Checking the Download buttons. Versand_static_page")
    public Versand_static_page_Logic checkingDownloadButtons() {
        downloadPdfButton().shouldHave(attribute("href", "https://www.pkwteile.de/pdf/versand"));
        downloadAcrobatButton().click();
        switchTo().window(1);
        checkingContainsUrl("/reader/?loc=de");
        closeWindow();
        switchTo().window(0);
        return this;
    }

    public Versand_static_page_Logic checkingCountAndTextNearTheCountriesForTheFirstCountryBlock() { //good
        for (int i = 0; i < 55; i++) {
            countryListWithFlagAndPriceForFiftyFive().get(i).shouldBe(visible);
            Assert.assertFalse(oneCountryWithPriceFromFiftyFive().text().isEmpty());
        }
        return this;
    }

    public Versand_static_page_Logic checkingTheFlagInTheFirstCountryBlock() {  //good
        for (int i = 0; i < 55; i++) {
            countryListWithFlagAndPriceForFiftyFive().get(i).shouldBe(visible).shouldHave(attribute("src"));
        }
        return this;
    }

    public Versand_static_page_Logic checkingTextAndPriceForFirstThreeCountries() { //good
        for (int i = 0; i < 3; i++) {
            countryBlockItems().get(i).shouldBe(visible);
            Assert.assertFalse(countryBlockItem().text().isEmpty());
        }
        return this;
    }

    public Versand_static_page_Logic checkingFlagsForFirstThreeCountry() {  //good
        for (int i = 0; i < 3; i++) {
            countryBlockFlagsForFirstThree().get(i).shouldBe(visible).shouldHave(attribute("src"));
        }
        return this;
    }

    public Versand_static_page_Logic checkingTextNearTheThreeIcons() {  //good
        for (int i = 0; i < 3; i++) {
            featureBlockTextForIcon().get(i).shouldBe(visible);
            Assert.assertFalse(featureBlockTextForOneIcon().text().isEmpty());
        }
        return this;
    }

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

    public Versand_static_page_Logic checkingTextInTheQuestionsBlock() { //good
        for (int i = 0; i < 3; i++) {
            questionsBlockItem().get(i).shouldBe(visible);
            Assert.assertFalse(questionsBlockItemOne().text().isEmpty());
        }
        return this;
    }

    public Versand_static_page_Logic countryPriceWithParts() {
        for (int i = 0; i < 3; i++) {
            blocksTextWithPrice().get(i).shouldBe(visible).shouldHave(attribute("span"));
            Assert.assertFalse(blocksTextWithPriceOne().text().isEmpty());
        }
        return this;
    }

    public Versand_static_page_Logic tableCountryPriceWithPriceTabOne() {  //good
        for (int i = 0; i < 87; i++) {
            deliveryPriceWithCountryMehr().get(i).shouldBe(visible);
            Assert.assertFalse(deliveryPriceWithCountryMehrOne().text().isEmpty());
        }
        return this;
    }

    public Versand_static_page_Logic checkingFlagTabTwo() {
        for (int i = 0; i < 11; i++) {  //good
            secondTabContentImageFlag().get(i).shouldBe(visible).shouldHave(attribute("alt"));
        }
        return this;
    }

    public Versand_static_page_Logic checkingTextAndPriceTabTwo() {
        for (int i = 0; i < 22; i++) {
            blocksCountryPriceText().get(i).shouldBe(visible);
            Assert.assertFalse(blocksCountryPriceTextOne().text().isEmpty());
        }
        return this;
    }
}
