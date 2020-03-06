package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_maker_page_Logic extends LKW_maker_page {

    @Step("checking of elements on page. LKW_maker_page")
    public LKW_maker_page_Logic checkElementsOnPage() {
        titleOfSidebar().shouldBe(visible);

        return this;
    }
}
