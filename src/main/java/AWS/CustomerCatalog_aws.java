package AWS;

import ATD.Category_name_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
import Common.DataBase;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.sql.SQLException;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
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

    private ElementsCollection idOfChildList() {
        return $$x("//ul[@class='catalog-table-content-items-item-child ui-sortable']/li/div/div[4]");
    }

    private ElementsCollection idOfChild() {
        return $$x("//ul[@class='catalog-table-content-items-item-child ui-sortable']/li/div/div[4][(contains(text(),' '))]");
    }

    private ElementsCollection childName() {
        return $$x("//ul[@class='catalog-table-content-items-item-child ui-sortable']/li/div/div[4][(contains(text(),' '))]/../div[5]/input");
    }

    private SelenideElement loader() {
        return $x("//div[@class='center loading-text']");
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
    public CustomerCatalog_aws checkRedirectForDangerousProducts(String idOfDangerousProduct, String idOfProduct) throws SQLException {
        customCatalog().shouldBe(visible);
        setSkin("atd");
        setIdOfProduct(idOfDangerousProduct);
        clickOnSearchButton();
        loader().shouldBe(visible);
        loader().shouldNotBe(visible);
        String childId = idOfChild().last().getText();
        String firstChar = childId.substring(0, 1);
        String title = childName().last().getAttribute("value");
        if (firstChar.equals("3")) {
            open(new DataBase("ATD").getFullRouteByRouteName("prod", "DE", "main") + "/brand/" + idOfProduct);
            waitWhileRouteBecomeExpected("listing_chemicals");
            new Listing_chemicals_Page_Logic().checkMainHeadline(title);
        } else if (firstChar.equals("1")) {
            open(new DataBase("ATD").getFullRouteByRouteName("prod", "DE", "main") + "/brand/" + idOfProduct);
            waitWhileRouteBecomeExpected("category_name");
            new Category_name_page_Logic().checkMainHeadline(title);
        } else if (firstChar.equals(" ")) {
            open(new DataBase("ATD").getFullRouteByRouteName("prod", "DE", "main") + "/brand/" + idOfProduct);
            waitWhileRouteBecomeExpected("main");
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
