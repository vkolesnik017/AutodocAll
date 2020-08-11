package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

public class FAQ_static_page_Logic extends FAQ_static_page {

    @Step("Checks the elements. FAQ_static_page")
    public FAQ_static_page_Logic checkElementsOnThePage() {
        blockFaq().shouldBe(visible);
        faqAccordionButton().click();
        Assert.assertFalse(faqAccordionContent().text().isEmpty());
        return this;
    }
}
