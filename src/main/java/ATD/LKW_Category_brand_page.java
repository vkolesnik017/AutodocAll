package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class LKW_Category_brand_page {

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
        return $x("//li[@class='step_4 active parts_step_4']//a");
    }
    SelenideElement fifthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_5 not_active parts_step_5']//span");
    }
    SelenideElement sixthLinkOfBreadCrumbsBlock() {
        return $x("//li[@class='step_6 not_active parts_step_6']//span");
    }

    SelenideElement seventhLinkOfBreadCrumbsBlock() {
        return $x("(//div[@class='steps breadcrumbs']//li//span/span)[3]");
    }

    SelenideElement childCategoryBlockInSideBar() {
        return $x("//div[@class='sidebar']");
    }

    SelenideElement imageOfBrandInHeadLine() {return $x("//div[contains(@class,'image_padding')]");}
}
