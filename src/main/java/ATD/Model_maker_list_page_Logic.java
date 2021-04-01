package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.back;

public class Model_maker_list_page_Logic extends Model_maker_list_page{

    @Step("check Titles Of TOP Auto Links. Model_maker_list_page")
    public Model_maker_list_page_Logic checkTitlesOfTopCarLinks(List<String> expectedAutoLinks) {
        linksBlock().shouldBe(visible);
        Assert.assertEquals(topAutoLinks().size(), expectedAutoLinks.size());
        List<String> titleOfTopAutoLinks = topAutoLinks().stream().map(n -> n.getText()).collect(Collectors.toList());
        Assert.assertEquals(titleOfTopAutoLinks, expectedAutoLinks);
        return this;
    }

    @Step("check Transition Of TOP Auto Links. Model_maker_list_page")
    public Model_maker_list_page_Logic checkTransitionOfTopAutoLinks() throws IOException {
        for (int i = 0; i < topAutoLinks().size(); i++) {
            topAutoLinks().get(i).shouldBe(visible).scrollTo().click();
            new Category_car_list_page_Logic().checkResponseOfServer(200);
            back();
        }
        return this;
    }
}
