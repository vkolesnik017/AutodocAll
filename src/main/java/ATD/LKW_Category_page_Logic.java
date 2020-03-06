package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Category_page_Logic extends LKW_Category_page {
@Step("check successfully child category page loading")
    public LKW_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://lkwteile.autodoc.de/ersatzteile/olfilter-200157");
        return this;
    }
}
