package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class PriceProductDescription_aws {

    private final String urlPage = "https://aws.autodoc.de/products/price-description";

    private SelenideElement idField() {
        return $(byId("form_articleId"));
    }

    private SelenideElement btnSearch() {
        return $x("//button[@class='btn btn-success']");
    }

    SelenideElement noResult() {
        return $x("//td[contains(text(),'Nothing to show')]");
    }

    private ElementsCollection idOfProducts() {
        return $$x("//table[@class='table table-bordered table-striped']//td[1]/a");
    }

    private ElementsCollection aliasOfProduct() {
        return $$x("//table[@class='table table-bordered table-striped']//td[2]");
    }

    private SelenideElement tableOfProducts() {
        return $x("//table[@class='table table-bordered table-striped']");
    }

    @Step("open price-description page. PriceProductDescription_aws")
    public PriceProductDescription_aws openPriceDescriptionPage() {
        open(urlPage);
        new Login_aws().loginInAws();
        return this;
    }

    @Step("check presence of price title. PriceProductDescription_aws")
    public PriceProductDescription_aws checkPresenceOfPriceTitle(List<String> id, List<String> title) {
        for (int i = 0; i < id.size(); i++) {
            setId(id.get(i));
            clickSearch();
            idOfProducts().shouldHaveSize(1);
            if (noResult().isDisplayed()) {
                Assert.assertFalse(title.get(i).equals("Preis pro Meter"));
            } else if (aliasOfProduct().get(0).isDisplayed()) {
                Assert.assertTrue(aliasOfProduct().get(0).getText().equals("price_for_one_metr"));
                Assert.assertTrue(title.get(i).equals("Preis pro Meter"));
            }
        }

        return this;
    }

    @Step("set id of product. PriceProductDescription_aws")
    public PriceProductDescription_aws setId(String id) {
        idField().shouldBe(visible).setValue(id);
        return this;
    }

    @Step("click on Search button of Search filter. PriceProductDescription_aws")
    public PriceProductDescription_aws clickSearch() {
        btnSearch().click();
        return this;
    }

    @Step("get id of product. PriceProductDescription_aws")
    public String getIdOfProduct() {
        String id = url().replaceAll(".+\\/", "");
        return id;
    }

    @Step("get included product. PriceProductDescription_aws")
    public PriceProductDescription_aws getIncludedProduct() {
        tableOfProducts().shouldBe(visible);
        boolean label = false;

        while (!label == true) {
            for (int i = 0; i < idOfProducts().size(); i++) {
                idOfProducts().get(i).click();
                switchTo().window(1);
                boolean activeLabel = new ProductCard_aws().getActiveLabelByArtNumAndBrand();
                if (activeLabel == true) {
                    label = true;
                } else {
                    switchTo().window(1).close();
                    switchTo().window(0);
                }
                if (label == true) {
                    break;
                } else {
                    continue;
                }
            }
        }
        return this;
    }
}
