package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.sleep;

public class Austauschartikel_static_page_Logic extends Austauschartikel_static_page {

    @Step("Checking the text blocks visibility,link anchor, tips and closing buttons, the quantity of the cards and their description. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkElementsOnThePage() {

        pfandPageTitle().shouldBe(visible).shouldHave(text("Austauschartikel"));
        pfandInfoText().shouldBe(visible);
        pfandItWorksText().shouldBe(visible);
        pfandItWorksAnchorLink().click();
        anchorOnTheForm().shouldBe(visible);

        pfandReturnPartsText().shouldBe(visible);
        pfandReturnPartsCategories().shouldBe(visible);
        pfandReturnPartsItem().shouldBe(visible);
        Assert.assertEquals(categories().size(), 39);
        pfandReturnPolicy().shouldBe(visible);
        pfandReturn().shouldBe(visible);
        sleep(5000);

        pfandInfoButtonTooltipPlz().hover().click();
        pfandInfoTooltipPlz().scrollIntoView("{block: \"center\"}");
        pfandInfoTooltipPlz().shouldBe(visible);
        closeButtonTooltipPlz().hover().click();

        pfandInfoButtonTooltipOrder().hover().click();
        pfandInfoTooltipOrder().shouldBe(visible);
        closeButtonTooltipOrder().hover().click();

        return this;
    }
    @Step("Checking the pop up with info about parts after click. Austauschartikel_static_page")
    public Austauschartikel_static_page_Logic checkClickableCards() {
        pfandReturnPartsItem().hover().scrollIntoView(false);
        for (int i = 0; i < 12; i++) {
            categories().get(i).scrollIntoView(false).click();
            popUpOfCategory().get(i).waitUntil(appear, 10).scrollIntoView(false);
            categories().get(i).click();
            popUpOfCategory().get(i).waitUntil(hidden, 10).shouldNotBe(visible);
        }
        return this;
    }
}







