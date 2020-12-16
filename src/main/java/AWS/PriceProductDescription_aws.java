package AWS;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class PriceProductDescription_aws {

    private final String urlPage = "https://aws.autodoc.de/products/price-description";


    @Step("open price-description page. PriceProductDescription_aws")
    public PriceProductDescription_aws openPriceDescriptionPage() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }
}
