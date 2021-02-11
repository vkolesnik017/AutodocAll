package PKW;

import io.qameta.allure.Step;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.visible;

public class Profile_page_Logic extends Profile_page {

    @Step("Checking presence id user on page. Profile_page")
    public Profile_page_Logic checkingPresenceIdUser() {
        Assert.assertFalse(idUser().text().isEmpty());
        return this;
    }

    @Step("Get id user on page. Profile_page")
    public String getIdUser() {
        String userId = idUser().shouldBe(visible).getText().replaceAll("\\D+", "");
        return userId;
    }
}
