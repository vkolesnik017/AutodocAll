package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

class LKW_Category_page {
    SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
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
        return $x("//li[@class='step_3 active parts_step_3']//a");
    }

    SelenideElement fourthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_4 not_active parts_step_4']/span/span");
    }

    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 not_active parts_step_5']/span/span");
    }

    SelenideElement sixthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_6 not_active parts_step_6']/span/span");
    }
}
