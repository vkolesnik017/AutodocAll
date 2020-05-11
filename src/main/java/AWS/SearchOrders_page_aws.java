package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

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
}
