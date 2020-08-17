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
        Assert.assertEquals(cards().size(), 39);
        pfandReturnPolicy().shouldBe(visible);
        pfandReturn().shouldBe(visible);

        sleep(1500);
        pfandInfoButtonTooltipPlz().scrollIntoView(false);
        pfandInfoButtonTooltipPlz().click();
        pfandInfoTooltipPlz().shouldBe(visible);
        pfandInfoTooltipPlz().scrollIntoView("{block: \"center\"}");
        closeButtonTooltipPlz().click();

        pfandInfoButtonTooltipOrder().click();
        pfandInfoTooltipOrder().shouldBe(visible);
        closeButtonTooltipOrder().click();

        checkClickableCards();
        return this;
    }

    public Austauschartikel_static_page_Logic checkClickableCards() {
        for (int i = 0; i <= 11; i++) {
            if (categories().get(i).has(attribute("data-id"))) {
                categories().get(i).scrollIntoView("{block: \"center\"}").click();
            }
            popUpOfCategory().get(i).shouldBe(visible);
            sleep(1500);
        }
        return this;
    }
}











