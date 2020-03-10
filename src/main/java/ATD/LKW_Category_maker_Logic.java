package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_maker_Logic extends LKW_Category_maker{

    @Step("check successfully child category page loading. LKW_Category_maker ")
    public LKW_Category_maker_Logic checkSuccessfullyCategoryMakerPageLoading() {
        childCategoryBlockSideBar().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/keilrippenriemen-satz-204335/mercedes-benz");
        return this;
    }
}
