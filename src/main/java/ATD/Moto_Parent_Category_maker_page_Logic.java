package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class Moto_Parent_Category_maker_page_Logic  extends Moto_Parent_Category_maker_page{

    @Step(" reset of motorcycle selector .Moto_Parent_Category_maker_page")
    public Moto_Parent_Category_page_Logic resetOfMotoSelector() {
        btnResetOfSelector().shouldBe(visible).click();
        return page(Moto_Parent_Category_page_Logic.class);
    }
}
