package ATD;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class LKW_Error_page_Logic  extends LKW_Error_page{
    @Step("visibility of headline   .LKW_Error_page")
    public LKW_Error_page_Logic visibilityOfHeadLine() {
        headlineInHeader().shouldBe(visible);
        return this;
    }
}
