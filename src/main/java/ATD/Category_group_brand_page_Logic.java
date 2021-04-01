package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class Category_group_brand_page_Logic extends Category_group_brand_page {

    @Step("displaying Headline Of Other Engine Parts Block. Category_group_brand_page")
    public Category_group_brand_page_Logic displayHeadlineOfOtherEnginePartsBlock(String expectedTitle) {
        headlineOfOtherEnginePartsBlock().shouldBe(visible).shouldHave(text(expectedTitle));
        return this;
    }

    @Step("check Titles Of Seo Block. Category_group_brand_page")
    public Category_group_brand_page_Logic checkTitlesOfOtherEnginePartsBlock(List<String> expectedTitles) {
        List<String> frontSeoTitles = titlesOfOtherEnginePartsBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontSeoTitles, expectedTitles);
        return this;
    }
}
