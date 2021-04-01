package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Categories_maker_page_Logic extends Categories_maker_page {

    @Step("presence Of TecDoc catalog. Categories_maker_page")
    public Categories_maker_page_Logic presenceOfTecDocCatalog() {
        tecDocCatalog().shouldBe(visible);
        return this;
    }

    @Step("get position of expected parent category. Categories_maker_page")
    public int getPositionOfParentCategory(String expectedParentCategory) {
        int position = titlesOfParentCategories()
                .stream()
                .map(n -> n.getText())
                .collect(Collectors.toList())
                .indexOf(expectedParentCategory);
        return position;
    }

    @Step("click On Parent Category By Title. Categories_maker_page")
    public Tyres_maker_page_Logic clickOnParentCategoryByTitle(String parentCategory) {
        exactParentCategory(parentCategory).click();
        return page(Tyres_maker_page_Logic.class);
    }

    @Step("check Titles Of Seo Block. Categories_maker_page")
    public Categories_maker_page_Logic checkTitlesOfSeoBlock(List<String> expectedTitles) {
        List<String> frontSeoTitles = titlesOfSeoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(frontSeoTitles, expectedTitles);
        return this;
    }

    @Step("check Titles links Of Seo Block. Categories_maker_page")
    public Categories_maker_page_Logic checkTitleLinksOfSeoBlock(List<String> expectedTitleLinks) {
        List<String> frontSeoTitles = titleLinksOfSeoBlock().stream().map(n -> n.getText()).collect(Collectors.toList());
        Collections.sort(expectedTitleLinks);
        Collections.sort(frontSeoTitles);
        Assert.assertEquals(frontSeoTitles, expectedTitleLinks);
        return this;
    }
}
