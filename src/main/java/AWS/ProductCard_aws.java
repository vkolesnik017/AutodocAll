package AWS;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class ProductCard_aws {

    public ProductCard_aws(String productId) {
        this.productId = productId;
    }

    private String productId;
    private String url = "https://aws.autodoc.de/products/view/" + productId;


    @Step
    public ProductCard_aws openProductSearchPageAndLogin() {
        open(url);
        new Login_aws().loginInAws();
        return this;
    }
}
