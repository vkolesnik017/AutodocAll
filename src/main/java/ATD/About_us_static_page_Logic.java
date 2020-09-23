package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

public class About_us_static_page_Logic extends About_us_static_page {

    @Step("Check the elements in the Twelve years block. About_us_static_page")
    public About_us_static_page_Logic checkElementsOnThePage() {
        imageTwelve().shouldBe(visible);
        textOnTheImage().shouldBe(visible);
        Assert.assertFalse(textOnTheImage().text().isEmpty());
        textOnTheImageSecond().shouldBe(visible);
        Assert.assertFalse(textOnTheImageSecond().text().isEmpty());
        numberWithPlus().shouldBe(visible);
        Assert.assertFalse(numberWithPlus().text().isEmpty());
        textBlockTitle().shouldBe(visible);
        Assert.assertFalse(textBlockTitle().text().isEmpty());
        textBlockText().shouldBe(visible);
        Assert.assertFalse(textBlockText().text().isEmpty());
        return this;
    }

    @Step("Checking the presence of the numbers in the Twelve Block. About_us_static_page")
    public About_us_static_page_Logic checkingTheNumbers() {
        for (int i = 0; i < numbersOnTheBlock().size(); i++) {
            numbersOnTheBlock().get(i).shouldBe(visible);
            Assert.assertFalse(numbersOnTheBlock().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the presence of the text in the Twelve Block. About_us_static_page")
    public About_us_static_page_Logic checkingTheText() {
        for (int i = 0; i < textOnTheBlock().size(); i++) {
            textOnTheBlock().get(i).shouldBe(visible);
            Assert.assertFalse(textOnTheBlock().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the presence of the titles in the UNSERE STÄRKEN block. About_us_static_page")
    public About_us_static_page_Logic checkingTheTitles() {
        for (int i = 0; i < sixTextTitles().size(); i++) {
            sixTextTitles().get(i).scrollIntoView(true).shouldBe(visible);
            Assert.assertFalse(sixTextTitles().get(i).text().isEmpty());
        }
        return this;
    }

    @Step("Checking the presence of the short five text in the UNSERE STÄRKEN block. About_us_static_page")
    public About_us_static_page_Logic checkingTheShortFiveText() {
        for (int i = 0; i < fiveTextBlocks().size(); i++) {
            fiveTextBlocks().get(i).shouldBe(visible);
            Assert.assertFalse(fiveTextBlocks().get(i).text().isEmpty());
        }
        oneTextBlock().shouldBe(visible);
        Assert.assertFalse(oneTextBlock().text().isEmpty());
        return this;
    }

    @Step("Check the icons in the UNSERE STÄRKEN block. About_us_static_page")
    public About_us_static_page_Logic checkTheIconsInTheBlock() {
        firstIcon().shouldBe(visible);
        secondIcon().shouldBe(visible);
        thirdIcon().shouldBe(visible);
        fourthIcon().shouldBe(visible);
        fifthIcon().shouldBe(visible);
        sixthIcon().shouldBe(visible);
        Assert.assertEquals(iconsWithTitleAndTextBlock().size(), 18);
        return this;
    }

    @Step("Check the elements in the sales block . About_us_static_page")
    public About_us_static_page_Logic checkTheElementsInTheSalesBlock() {
        bigDiscountImage().shouldBe(visible);
        staticText().shouldBe(visible);
        Assert.assertFalse(staticText().text().isEmpty());
        return this;
    }

    @Step("Checking the years in the sales block. About_us_static_page")
    public About_us_static_page_Logic checkingTheYears() {
        for (int i = 0; i < nineYears().size(); i++) {
            nineYears().get(i).shouldBe(visible);
            Assert.assertFalse(nineYears().get(i).text().isEmpty());
        }
        Assert.assertEquals(nineYears().size(), 9);
        return this;
    }

    @Step("Checking the money in the sales block. About_us_static_page")
    public About_us_static_page_Logic checkingTheMoney() {
        for (int i = 0; i < nineTraffic().size(); i++) {
            nineTraffic().get(i).shouldBe(visible);
            Assert.assertFalse(nineTraffic().get(i).text().isEmpty());
        }
        Assert.assertEquals(nineTraffic().size(), 9);
        return this;
    }
}
