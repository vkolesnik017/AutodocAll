package ATD;

import io.qameta.allure.Step;

import static ATD.CommonMethods.getTextFromUnVisibleElement;
import static com.codeborne.selenide.Condition.visible;

public class Group_list_body_page_Logic  extends Group_list_body_page{

    @Step("presence of TOP products block. Group_list_body_page")
    public Group_list_body_page_Logic presenceOfTopProductsBlock() {
        topProductsBlock().shouldBe(visible);
        return this;
    }

    @Step("get signal word from first dangerous product .Group_list_body_page")
    public String getSignalWordFromFirstDangerousProduct() {
        dangerousPopUp().shouldBe(visible);
        return getTextFromUnVisibleElement(signalWordOfDangerousProduct().get(0));
    }
}
