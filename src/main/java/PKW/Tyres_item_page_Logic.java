package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static org.testng.Assert.assertEquals;

public class Tyres_item_page_Logic extends Tyres_item_page {
    @Step("presence of return 14 days block . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfReturnBlock() {
        returnBlockProductPage().shouldBe(visible);
        returnBlockTextProductPage().shouldBe(visible).shouldHave(text("14"));
        return this;
    }

    @Step("checking presence of the name tyres . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfNameTyres() {
        nameOfTyres().shouldBe(visible);
        Assert.assertFalse(nameOfTyres().text().isEmpty());
        return this;
    }

    @Step("checking presence of rating stars and checking anchor on reviews block . Tyres_item_page")
    public Tyres_item_page_Logic presenceAndClickOnRatingStars() {
        ratingStars().shouldBe(visible).click();
        reviewsBlockStars().shouldBe(visible);
        reviewsBlockAnchor().shouldHave(attribute("class", "rating-block-anchor"));
        return this;
    }

    @Step("checking presence photo of the tyres . Tyres_item_page")
    public Tyres_item_page_Logic presenceTyresPhoto() throws IOException {
        photoOfTyres().shouldBe(visible);
        photoOfTyres().isDisplayed();
        String linkInsideImage = photoOfTyres().getAttribute("src");
        URL url = new URL(linkInsideImage);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        assertEquals(responseCode, 200);
        return this;
    }

    @Step("presence of characteristics block . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfCharacteristicsBlock() {
        characteristicsBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of tyres price . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfTyresPrice() {
        priceOfTyres().shouldBe(visible);
        Assert.assertFalse(priceOfTyres().text().isEmpty());
        return this;
    }

    @Step("presence of EU Reifenlabel block . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfEuReifenlabelBlock() {
        euReifenlabelBlock().shouldBe(visible);
        return this;
    }

    @Step("Check adding tyres to basket. Tyres_item_page")
    public Tyres_item_page_Logic checkAddingTyresToBasket() {
        addToBasketButton().click();
        basketBlock().hover();
        Float priceOfOneTyre = getPriceFromElement(priceOfTyres());
        Float priceOfTyresInBasket = getPriceFromElement(priceInBasketPopup());
        Float numberOfTyresInFloat = Float.parseFloat(numberOfProductsOnProductPage().attr("value").trim());
        Float resultPrice = (priceOfOneTyre * numberOfTyresInFloat);
        Assert.assertEquals(resultPrice, priceOfTyresInBasket);
        numberOfProductsInBasketPopup().shouldHave(text(numberOfProductsOnProductPage().text()));
        return this;
    }

    @Step(":from Tyres_item_page")
    public Cart_page_Logic cartClick() {
        new Main_page_Logic().cartClick();
        return page(PKW.Cart_page_Logic.class);
    }

    @Step("presence of Reviews block with messages. Tyres_item_page")
    public Tyres_item_page_Logic presenceOfReviewsBlock() {
        reviewsMessageBlock().shouldBe(visible);
        return this;
    }

    @Step("presence of Product Analog block . Tyres_item_page")
    public Tyres_item_page_Logic presenceOfAnalogsBlock() {
        analogBlock().shouldBe(visible);
        return this;
    }
}
