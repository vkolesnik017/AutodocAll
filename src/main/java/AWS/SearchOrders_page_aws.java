package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.getExpectedCalendarData;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchOrders_page_aws {

    public static String searchOrderPageURL = "https://aws.autodoc.de/search-orders";


    private SelenideElement addOrderBtn() {
        return $x("//a[@class='btn btn-primary control-add']");
    }

    private SelenideElement inputDateFrom() {
        return $x("//input[@name='Filter[fromDate]']");
    }

    private SelenideElement inputDataTo() {
        return $x("//input[@name='Filter[toDate]']");
    }

    private SelenideElement searchBtn() {
        return $x("//button[@name='search']");
    }

    private SelenideElement groupFieldSelector() {
        return $x("//select[@name='Filter[groupList]']");
    }

    private ElementsCollection orderLine() {
        return $$x("//td[@class='w-20 select-row']/..");
    }



    @Step("Click button search. SearchOrders_page_aws")
    public SearchOrders_page_aws clickSearchBtn() {
        searchBtn().click();
        return this;
    }

    @Step("Fields inputs data from and data to. SearchOrders_page_aws")
    public SearchOrders_page_aws fieldInputsDateFromAndDataTo(int minusMonths, int minusDays) {
        String data = getExpectedCalendarData("yyyy-MM-dd", minusMonths, minusDays);
        inputDateFrom().setValue(data);
        inputDataTo().setValue(data);
        return this;
    }

    @Step("Select expected Group field {expectedGroup}. SearchOrders_page_aws")
    public SearchOrders_page_aws selectExpectedGroupField(String expectedGroup) {
        groupFieldSelector().selectOptionContainingText(expectedGroup);
        return this;
    }

    @Step("Click button add order. SearchOrders_page_aws")
    public OrderAdd_page_aws clickAddOrderBtn() {
        addOrderBtn().shouldBe(visible);
        addOrderBtn().click();
        return page(OrderAdd_page_aws.class);
    }

    @Step("Open search order page. SearchOrders_page_aws")
    public SearchOrders_page_aws openSearchOrderPageWithLogin() {
        openPage(searchOrderPageURL);
        new Login_aws().loginInAws();
        return page(SearchOrders_page_aws.class);
    }

    @Step("Find order with a generated invoice and with order amount equal to or less than ten. SearchOrders_page_aws")
    public Order_aws findOrderWithGeneratedInvoice() {
        if (orderLine().size() > 0) {
            for (int i = 0; i < orderLine().size(); i++) {
                if (!orderLine().get(i).$x(".//div[@data-hint='Test']").isDisplayed()){
                    int sum = Integer.parseInt(orderLine().get(i).$x("./..//a[@class='printBillPopup']/../..//a[@class='order-grandtotal']").getText());
                    if (sum <= 10) {
                        String id = orderLine().get(i).$x("./..//a[@class='printBillPopup']/../..//a[@class='order_link']").getText();
                        System.out.println(id);
                        orderLine().get(i).$x("./..//a[@class='printBillPopup']/../..//a[@class='order_link']").click();
                        break;
                    }
                }
            }
        }
        return page(Order_aws.class);
    }
}
