package ATD;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class AutodocClub_page {


    SelenideElement iconAutodocClub() {
        return $x("//div[@class='seo-tool__container']//img[@alt='club.autodoc.de']");
    }



    @Step("check presence icon autodoc club in header. AutodocClub_page")
    public AutodocClub_page checkPresenceIconAutodocClubInHeader() {
        iconAutodocClub().shouldBe(visible);
        return this;
    }



}
