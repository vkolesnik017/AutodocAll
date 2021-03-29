package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;

public class Category_maker_page_Logic extends Category_maker_page {

    @Step("check Titles Of TOP Auto Links. Category_maker_page")
    public Category_maker_page_Logic checkTitlesOfTopAutoLinks(List<String> expectedAutoLinks) {
        linksBlock().shouldBe(visible);
        Assert.assertEquals(topAutoLinks().size(), expectedAutoLinks.size());
        List<String> titleOfTopAutoLinks = topAutoLinks().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(titleOfTopAutoLinks, expectedAutoLinks);
        return this;
    }

    @Step("check Transition Of TOP Auto Links. Category_maker_page")
    public Category_maker_page_Logic checkTransitionOfTopAutoLinks() throws IOException {
        for (int i = 0; i < topAutoLinks().size(); i++) {
            topAutoLinks().get(i).shouldBe(visible).click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }

    @Step("check Transition Of Car Parts For Links. Category_maker_page")
    public Category_maker_page_Logic checkTransitionOfCarPartsForLinks() throws IOException {
        for (int i = 0; i < carPartsForLinks().size(); i++) {
            carPartsForLinks().get(i).shouldBe(visible).click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }

    @Step("check Titles Of Car Parts For Another auto Links. Category_maker_page")
    public Category_maker_page_Logic checkTitlesOfCarPartsForLinks(List<String> expectedAutoLinks) {
        linksBlock().shouldBe(visible);
        Assert.assertEquals(carPartsForLinks().size(), expectedAutoLinks.size());
        List<String> titleOfTopAutoLinks = carPartsForLinks().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(titleOfTopAutoLinks, expectedAutoLinks);
        return this;
    }


}
