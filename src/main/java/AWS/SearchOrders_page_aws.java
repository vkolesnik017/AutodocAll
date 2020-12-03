package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ATD.CommonMethods.checkingContainsUrl;
import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.getExpectedCalendarData;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

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
        return $$x("//td[@class='w-20 select-row']//..//a[@class='printBillPopup']");
    }

    private SelenideElement calendarDataFromBtn() {
        return $x("//div[@id='dp_start']//span[@class='input-group-addon']/i");
    }

    private SelenideElement prevMonthBtmInCalendarDataFrom() {
        return $x("(//table[@class=' table-condensed']//i[@class='glyphicon glyphicon-arrow-left'])[1]");
    }

    private SelenideElement dayInCalendarDataFrom(String expectedDay) {
        return $x("(//div[@class='datepicker-days']//tbody//tr//td[@class='day'][text()='" + expectedDay + "'])[1]");
    }

    private SelenideElement nexPageBtnInPaginationBlock() {
        return $x("//ul[@class='pagination']//li//a[text()='»']");
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
        return this;
    }

    @Step("Open search page with expected data")
    public SearchOrders_page_aws openSearchPageWithExpectedData(int minusMonths, int minusDays) {
        String expectedData = getExpectedCalendarData("yyyy-MM-dd", minusMonths, minusDays);
        openPage(searchOrderPageURL +
                "?Filter%5BorderId%5D=&Filter%5BorderFrom%5D=our&Filter%5Bplz%5D=&Filter%5Bort%5D=&Filter%5" +
                "Bgroup%5D=&Filter%5BgroupList%5D=&Filter%5BsortByHours%5D=&Filter%5BcorrectionPaid%5D=0&Filter%5Bpaid%5D=&Filter%" +
                "5BcustomerInfo%5D=&Filter%5BfromDate%5D=" + expectedData + "&Filter%5BtoDate%5D=" + expectedData + "&Filter%5BwarehouseId%5D=&Filter%" +
                "5BhasSingleProduct%5D=0&Filter%5BhasNoProduct%5D=0&search=");
        new Login_aws().loginInAws();
        return this;
    }

    @Step("Check present transaction cod block in re-order. SearchOrders_page_aws")
    public Boolean checkPresentTransactionCodInReOrder(SelenideElement element) {
        boolean check = false;
        Order_aws order_aws = new Order_aws();
        if (orderLine().size() > 0) {
            for (int i = 0; i < orderLine().size(); i++) {
                if (!orderLine().get(i).$x(".//div[@data-hint='Test']").isDisplayed()) {
                    float sum = Float.parseFloat((orderLine().get(i).$x("./../..//a[@class='order-grandtotal']").shouldBe(visible).getText()));
                    if (sum <= 10.0f) {
                        String id = orderLine().get(i).$x("./../..//a[@class='order_link']").getText();
                        System.out.println(id);
                        orderLine().get(i).$x("./../..//a[@class='order_link']").shouldBe(visible).click();
                        if (element.isDisplayed()) {
                            check = true;
                            new Order_aws().transitionToReorderNumber();
                            switchTo().window(1);
                            sleep(5000);
                            if (order_aws.transactionCodBlock().isDisplayed()) {
                                break;
                            } else {
                                closeWindow();
                                switchTo().window(0);
                                String url = url();
                                if (url.contains("https://aws.autodoc.de/order/view/" + id + "")) {
                                    back();
                                    continue;
                                }
                            }
                        } else {
                            back();
                            continue;
                        }
                    }
                }
            }
        }
        return check;
    }

    @Step("Toggles pagination pages if there are no Parent and Re-Order number blocks in the order. SearchOrders_page_aws")
    public Order_aws togglesPaginationIfThereAreNoParentAndReOrderNumberBlocksInOrder(SelenideElement element) {
        while (nexPageBtnInPaginationBlock().isDisplayed()) {
            Boolean check = checkPresentTransactionCodInReOrder(element);
            if (!check) {
                nexPageBtnInPaginationBlock().click();
                checkingContainsUrl("https://aws.autodoc.de/search-orders");
            } else {
                break;
            }
        }
        return page(Order_aws.class);
    }
}
