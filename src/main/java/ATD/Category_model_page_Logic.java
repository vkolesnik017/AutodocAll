package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;

public class Category_model_page_Logic extends Category_model_page {

    @Step("check Titles Of TOP Auto Links. Category_model_page")
    public Category_model_page_Logic checkTitlesOfTopCarLinks(List<String> expectedAutoLinks) {
        checkLinks(topCarLinks(), expectedAutoLinks);
        return this;
    }

    @Step("check Titles Of TOP Categories Links. Category_model_page")
    public Category_model_page_Logic checkTitlesOfTopCategoriesLinks(List<String> expectedAutoLinks) {
        checkLinks(topCategoriesLinks(), expectedAutoLinks);
        return this;
    }

    @Step("check Titles Of TOP Models Links. Category_model_page")
    public Category_model_page_Logic checkTitlesOfTopModelLinks(List<String> expectedAutoLinks) {
        checkLinks(topModelsLinks(), expectedAutoLinks);
        return this;
    }

    @Step("check links. Category_model_page")
    public Category_model_page_Logic checkLinks(ElementsCollection linksBlock, List<String> expectedLinks) {
        linksBlock().shouldBe(visible);
        Assert.assertEquals(linksBlock.size(), expectedLinks.size());
        List<String> frontTitleLinks = linksBlock.stream().map(n -> n.getText()).collect(Collectors.toList());
        Collections.sort(frontTitleLinks);
        Collections.sort(expectedLinks);
        Assert.assertEquals(frontTitleLinks, expectedLinks);
        return this;
    }


}
