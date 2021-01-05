package AWS;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DisabledDangerousProducts_aws {

    public String disabledDangerousProducts = "https://taws.autodoc.de/hazard?disabledBy=2";

    private ElementsCollection btnGoToProductCard() {
        return $$x("//a[@class='btn btn-sm btn-success float-right']");
    }

    @Step("open Disabled Dangerous Product In Aws. DisabledDangerousProducts_aws")
    public DisabledDangerousProducts_aws openDisabledDangerousProductInAwsWithLogin() {
        open(disabledDangerousProducts);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("click on 'Go to product card' button. DisabledDangerousProducts_aws")
    public ProductCard_aws clickOnProductCard(int position) {
        btnGoToProductCard().get(position).shouldBe(visible).click();
        return page(ProductCard_aws.class);
    }
}
