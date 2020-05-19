package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Moto_Category_page_Logic extends Moto_Category_page {

    @Step("check successfully child category page loading. Moto_Category_page")
    public Moto_Category_page_Logic checkSuccessfullyChildCategoryPageLoading() {
        imageOfChildCategory().shouldBe(visible);
        Assert.assertEquals(url(), "https://moto.autodoc.de/ersatzteile/motorrad-luftfilter-43063");
        return this;
    }


    @Step(" availability Of Moto Selector. Moto_Category_page")
    public Moto_Category_page_Logic availabilityOfMotoSelector() {
        motoSelectorBlock().shouldBe(visible);
        return this;
    }


    @Step(" appears of tooltip for marke field in selector. Moto_Category_page")
    public Moto_Category_page_Logic visibilityOfToolTipOfMarkeField() {
              if (!brandOfMotoField().getSelectedValue().equals("0")) {
                  brandOfMotoField().selectOptionByValue("0");
              }

                btnSearchAtSelector().click();
                tooltipOfMarkeField().shouldBe(visible);
        return this;
    }

}
