package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LKW_Parent_Category_page {
    SelenideElement childCategoryBlock() {
        return $x("//div[@class='filter_small_img']");
    }

    ElementsCollection breadCrumbsLinks() {
        return $$x("//div[@class='steps breadcrumbs']//li");
    }

    SelenideElement firstLinkOfBreadCrumbsBlock() {
        return $x("//div[@class='steps breadcrumbs']//img");
    }

    SelenideElement secondLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_2 active parts_step_2']//a");
    }

    SelenideElement thirdLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_3 not_active parts_step_3']/span/span");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 not_active parts_step_4']/span/span");
    }

    SelenideElement closeToolTipTextSelector() {
        return $x("//div[@class='tooltiptext-close js-tooltiptext-close']");
    }

}
