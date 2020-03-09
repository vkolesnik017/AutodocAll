package ATD;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_maker_page_Logic extends LKW_maker_page {

    @Step("checking of elements on page. LKW_maker_page")
    public LKW_maker_page_Logic checkElementsOnPage() {
        titleOfSidebar().shouldBe(visible);
        linkingBlocks().shouldHave(size(7));
        for (int i=1;i<=linkingBlocks().size();i++){
            linksOfLinkingBlocks(i).shouldHave(size(5));
        }
        return this;
    }
}
