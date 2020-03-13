package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.url;

public class LKW_Categories_page_Logic extends LKW_Categories_page {

    @Step("Check successfully LKW_Categories page loading. LKW_Categories_page")
    public LKW_Categories_page_Logic checkSuccessfullyLKWCategoriesPageLoading() {
        tecDocCatalog().shouldBe(visible);
        Assert.assertTrue(url().contains("https://lkwteile.autodoc.de/ersatzteile"));
        return this;
    }

}
