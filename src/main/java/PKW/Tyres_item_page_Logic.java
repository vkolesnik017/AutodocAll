package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.codeborne.selenide.Condition.*;
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
}
