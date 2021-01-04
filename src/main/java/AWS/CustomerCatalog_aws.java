package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeNotEqual;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;

public class CustomerCatalog_aws {

    public String customerCatalog = "https://aws.autodoc.de/custom-catalog";

    private SelenideElement loginField() {
        return $(byId("login"));
    }

    private SelenideElement btnCloseSelectedSkin() {
        return $x("//li[@class='search-choice']/a");
    }

    private SelenideElement skinFiled() {
        return $x("//input[@class='default']");
    }

    private SelenideElement customCatalog() {
        return $x("//div[@class='custom-catalog']");
    }

    private SelenideElement idField() {
        return $(byId("form_filter[ga]"));
    }

    private SelenideElement btnSearch() {
        return $(byName("submitSearch"));
    }

    private ElementsCollection idOfChild() {
        return $$x("//ul[@class='catalog-table-content-items-item-child ui-sortable']/li/div/div[4]");
    }

    @Step("open Disabled Dangerous Product In Aws. CustomerCatalog_aws")
    public CustomerCatalog_aws openCustomerCatalog() {
        open(customerCatalog);
        if (loginField().isDisplayed()) {
            new Login_aws().loginInAws();
        }
        return this;
    }

    @Step("check Redirect For Dangerous Products. CustomerCatalog_aws")
    public CustomerCatalog_aws checkRedirectForDangerousProducts(String idOfDangerousProduct) {
        customCatalog().shouldBe(visible);
        setSkin("atd");
        setIdOfProduct(idOfDangerousProduct);
        int primordialQuantityOfChild = idOfChild().size();
        clickOnSearchButton();
        idOfChild().shouldHave(sizeNotEqual(primordialQuantityOfChild));
        String childId=null;

        for (int i = 0; i < idOfChild().size(); i++) {
            if (!idOfChild().get(i).equals("")) {
                childId = idOfChild().get(i).getText();
            }
        }
        return this;
    }

    @Step("set skin In Aws. CustomerCatalog_aws")
    public CustomerCatalog_aws setSkin(String skin) {
        if (btnCloseSelectedSkin().isDisplayed()) {
            executeJavaScript("arguments[0].click();", btnCloseSelectedSkin());
        }
        skinFiled().setValue(skin).pressEnter();
        return this;
    }

    @Step("set id of product In Aws. CustomerCatalog_aws")
    public CustomerCatalog_aws setIdOfProduct(String idOfProduct) {
        idField().setValue(idOfProduct);
        return this;
    }

    @Step("click on search button. CustomerCatalog_aws")
    public CustomerCatalog_aws clickOnSearchButton() {
        btnSearch().click();
        return this;
    }
}
