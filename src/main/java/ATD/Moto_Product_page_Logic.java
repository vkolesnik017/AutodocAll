package ATD;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;

public class Moto_Product_page_Logic  extends Moto_Product_page{

    @Step("availability of moto selector block .Moto_Product_page")
   public Moto_Product_page_Logic availabilityOfMotoSelector() {
       motoSelectorBlock().shouldBe(visible);
       return this;
   }
}
