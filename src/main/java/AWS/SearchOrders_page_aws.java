package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class SearchOrders_page_aws {

    public static String searchOrderPageURL = "https://aws.autodoc.de/search-orders";


    private SelenideElement addOrderBtn() {
        return $x("//a[@class='btn btn-primary control-add']");
    }

    @Step("Click button add order. SearchOrders_page_aws")
    public OrderAdd_page_aws clickAddOrderBtn() {
        addOrderBtn().click();
        return page(OrderAdd_page_aws.class);
    }

    @Step("Open search order page. SearchOrders_page_aws")
    public SearchOrders_page_aws openSearchOrderPageWithLogin() {
        open(searchOrderPageURL);
        new Login_aws().loginInAws();
        return page(SearchOrders_page_aws.class);
    }
}
