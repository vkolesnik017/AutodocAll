package AWS;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.testng.Assert;

import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.CommonMethods.getExpectedCalendarData;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class SearchOrders_page_aws {

    // Link for pages
    public static String searchOrderPageURL = "https://aws.autodoc.de/search-orders";

    // Locator for form-inline search-order block
    private SelenideElement orderIdField() {
        return $x("//input[@id='form_Filter[orderId]']");
    }

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

    private SelenideElement calendarDataFromBtn() {
        return $x("//div[@id='dp_start']//span[@class='input-group-addon']/i");
    }

    private SelenideElement prevMonthBtmInCalendarDataFrom() {
        return $x("(//table[@class=' table-condensed']//i[@class='glyphicon glyphicon-arrow-left'])[1]");
    }

    private SelenideElement dayInCalendarDataFrom(String expectedDay) {
        return $x("(//div[@class='datepicker-days']//tbody//tr//td[@class='day'][text()='" + expectedDay + "'])[1]");
    }

    private SelenideElement customerInfoField() {
        return $x("//input[@id='form_Filter[customerInfo]']");
    }

    private SelenideElement projectSelector() {
        return $x("//input[@value='<Project>']");
    }

    private SelenideElement shopList(String shop) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + shop + "']");
    }

    private SelenideElement projectList(String project) {
        return $x("(//div[@class='chzn-drop']//ul//li[text()='" + project + "'])[2]");
    }

    public SelenideElement projectDesktopInSelector() {
        return $x("//li[text()='desktop']");
    }

    public SelenideElement projectMobileInSelector() {
        return $x("//li[text()='mobile']");
    }

    private SelenideElement countrySelector() {
        return $x("//div[@id='form_Filter_countries____chzn']//input[@value='<Place field>']");
    }

    private SelenideElement countryList(String country) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + country + "']");
    }

    private SelenideElement paymentsMethodsSelector() {
        return $x("//div[@id='form_Filter_paymentId____chzn']//input[@value='<Payment>']");
    }

    private SelenideElement paymentsMethodsList(String paymentsMethods) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + paymentsMethods + "']");
    }

    private SelenideElement currencySelector() {
        return $x("//div[@id='form_Filter_currencyCode____chzn']//input[@value='<Currency>']");
    }

    private SelenideElement currencyList(String currency) {
        return $x("//div[@class='chzn-drop']//ul//li[text()='" + currency + "']");
    }

    private SelenideElement assemblyWarehouse() {
        return $x("//select[@name='Filter[warehouseId]']");
    }

    // Locators for table with orders
    private ElementsCollection orderLine() {
        return $$x("//td[@class='w-20 select-row']//..//a[@class='printBillPopup']");
    }

    private ElementsCollection allOrderLine() {
        return $$x("//td[@class='w-20 select-row']");
    }

    private ElementsCollection greenOrderLine() {
        return $$x("//tr[@class='green-bg']");
    }

    private ElementsCollection yellowOrderLine() {
        return $$x("//tr[@class='yellow-bg']");
    }

    private ElementsCollection redOrderLine() {
        return $$x("//tr[@class='red-bg']");
    }

    private SelenideElement orderLincInOrderLine(String orderId) {
        return $x("//tr[@data-id='" + orderId + "']//a[@class='order_link']");
    }

    private ElementsCollection orderID() {
        return $$x("//a[@class='order_link']");
    }

    // Locators for pagination block
    private SelenideElement nexPageBtnInPaginationBlock() {
        return $x("//ul[@class='pagination']//li//a[text()='»']");
    }


    @Step("Click button search. SearchOrders_page_aws")
    public SearchOrders_page_aws clickSearchBtn() {
        searchBtn().waitUntil(visible, 10000);
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
        groupFieldSelector().shouldBe(visible);
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

    @Step("Open search page with expected data. SearchOrders_page_aws")
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

    @Step("Open search page with expected data. SearchOrders_page_aws")
    public SearchOrders_page_aws openSearchPageWithExpectedData(String dataFrom, String dataBefore) {
        openPage(searchOrderPageURL +
                "?Filter%5BorderId%5D=&Filter%5BorderFrom%5D=our&Filter%5Bplz%5D=&Filter%5Bort%5D=&Filter%5" +
                "Bgroup%5D=&Filter%5BgroupList%5D=&Filter%5BsortByHours%5D=&Filter%5BcorrectionPaid%5D=0&Filter%5Bpaid%5D=&Filter%" +
                "5BcustomerInfo%5D=&Filter%5BfromDate%5D=" + dataFrom + "&Filter%5BtoDate%5D=" + dataBefore + "&Filter%5BwarehouseId%5D=&Filter%" +
                "5BhasSingleProduct%5D=0&Filter%5BhasNoProduct%5D=0&search=");
        new Login_aws().loginInAws();
        return this;
    }

    @Step("Open search page with expected data from. SearchOrders_page_aws")
    public SearchOrders_page_aws openSearchPageWithExpectedDataFrom(String dataFrom) {
        openPage(searchOrderPageURL +
                "?Filter%5BorderId%5D=&Filter%5BorderFrom%5D=our&Filter%5" +
                "Bplz%5D=&Filter%5Bort%5D=&Filter%5Bgroup%5D=&Filter%5BgroupList%5D=&Filter%5BsortByHours%5D=&Filter%" +
                "5BcorrectionPaid%5D=0&Filter%5Bpaid%5D=&Filter%5BcustomerInfo%5D=&Filter%5BfromDate%5D=" + dataFrom + "&" +
                "Filter%5BtoDate%5D=&Filter%5BwarehouseId%5D=&Filter%5BhasSingleProduct%5D=0&Filter%5BhasNoProduct%5D=0&search=");
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

    @Step("Filing field order id {orderId}. SearchOrders_page_aws")
    public SearchOrders_page_aws fillingFieldOrderId(String orderId) {
        orderIdField().shouldBe(visible);
        orderIdField().setValue(orderId);
        return this;
    }

    @Step("iling field customer information {customerInfo}. SearchOrders_page_aws")
    public SearchOrders_page_aws fillingFieldCustomerInfo(String customerInfo) {
        customerInfoField().sendKeys(customerInfo);
        return this;
    }

    @Step("Transition in order page. SearchOrders_page_aws")
    public Order_aws clickOnOrderLinc(String orderId) {
        orderLincInOrderLine(orderId).shouldBe(visible);
        orderLincInOrderLine(orderId).click();
        return page(Order_aws.class);
    }

    @Step("Go to a random order and check the PayLink. SearchOrders_page_aws")
    public SearchOrders_page_aws clickOnRandomOrderID(int countOfClick) {
        int minValue = 0;
        for (int i = 0; i < countOfClick; i++) {
            int randomIndex = minValue + (int) (Math.random() * orderID().size());
            orderID().get(randomIndex).scrollIntoView("{block: \"center\"}").shouldBe(visible);
            orderID().get(randomIndex).click();
            new Order_aws().checkPresencePauLinkAmount();
            back();
        }
        return this;
    }

    @Step("Choosing shop in selector {shop}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseShopInSelector(String shop) {
        projectSelector().shouldBe(visible);
        projectSelector().click();
        shopList(shop).shouldBe(visible);
        shopList(shop).click();
        return this;
    }

    @Step("Open project selector. SearchOrders_page_aws")
    public SearchOrders_page_aws openProjectSelector() {
        projectSelector().shouldBe(visible);
        projectSelector().click();
        return this;
    }

    @Step("Choosing project in selector {project}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseProjectInSelector(String project) {
        openProjectSelector();
        projectList(project).shouldBe(visible);
        projectList(project).click();
        return this;
    }

    @Step("Choosing project in selector {expectedElement}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseProjectInSelector(SelenideElement expectedElement) {
        openProjectSelector();
        expectedElement.shouldBe(visible).click();
        return this;
    }

    @Step("Choosing country {expectedCountry}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseCountry(String expectedCountry) {
        countrySelector().shouldBe(visible);
        countrySelector().click();
        countryList(expectedCountry).shouldBe(visible);
        countryList(expectedCountry).click();
        return this;
    }

    @Step("Choosing Payments method {expectedPaymentsMethod}. SearchOrders_page_aws")
    public SearchOrders_page_aws choosePaymentsMethods(String expectedPaymentsMethod) {
        paymentsMethodsSelector().shouldBe(visible);
        paymentsMethodsSelector().click();
        paymentsMethodsList(expectedPaymentsMethod).shouldBe(visible);
        paymentsMethodsList(expectedPaymentsMethod).click();
        return this;
    }

    @Step("Choosing currency method {expectedCurrency}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseCurrency(String expectedCurrency) {
        currencySelector().shouldBe(visible);
        currencySelector().click();
        currencyList(expectedCurrency).shouldBe(visible);
        currencyList(expectedCurrency).click();
        return this;
    }

    @Step("Choosing assembly warehouse {expectedAssemblyWarehouse}. SearchOrders_page_aws")
    public SearchOrders_page_aws chooseAssemblyWarehouse(String expectedAssemblyWarehouse) {
        assemblyWarehouse().shouldBe(visible);
        assemblyWarehouse().selectOptionContainingText(expectedAssemblyWarehouse);
        return this;
    }

    @Step("Selects any desired filter. SearchOrders_page_aws")
    public SearchOrders_page_aws choosingAllExpectedFilter(String expectedFilter, String expectedPayments, String expectedCurrency,
                                                           String expectedAssembly, String customerInfo) {
        switch (expectedFilter) {
            case "choosePaymentsMethods":
                choosePaymentsMethods(expectedPayments);
                break;
            case "chooseCurrency":
                chooseCurrency(expectedCurrency);
                break;
            case "chooseAssemblyWarehouse":
                chooseAssemblyWarehouse(expectedAssembly);
                break;
            case "fillingFieldCustomerInfo":
                fillingFieldCustomerInfo(customerInfo);
                break;
        }
        return this;
    }

    @Step("Gives an a randomly one payment method. SearchOrders_page_aws")
    public static String randomPaymentsMethod() {
        String[] payments = {"PayPal", "Be2bill"};
        int minValue = 0;
        int randomIndex = minValue + (int) (Math.random() * payments.length);
        return payments[randomIndex];
    }

    @Step("Gives an a randomly one assembly warehouse. SearchOrders_page_aws")
    public static String randomAssemblyWarehouse() {
        String[] AssemblyWarehouse = {"Основной склад", "Склад PL", "Склад PL A (M13)"};
        int minValue = 0;
        int randomIndex = minValue + (int) (Math.random() * AssemblyWarehouse.length);
        return AssemblyWarehouse[randomIndex];
    }

    @Step("Checks the percentage of paid orders. SearchOrders_page_aws")
    public SearchOrders_page_aws checkPercentageOfPaidOrders() {
        int allOrders = allOrderLine().size();
        int greenOrders = 0;
        int yellowOrders = 0;
        int redOrders = 0;
        if (greenOrderLine().get(0).isDisplayed()) {
            greenOrders = greenOrderLine().size();
        }
        if (yellowOrderLine().get(0).isDisplayed()) {
            yellowOrders = yellowOrderLine().size();
        }
        if (redOrderLine().get(0).isDisplayed()) {
            redOrders = redOrderLine().size();
        }
        int totalNumOfPaidOrders = greenOrders + yellowOrders + redOrders;
        int minPercentageOfPaidOrders = (allOrders * 10 / 100);
        Assert.assertTrue(totalNumOfPaidOrders >= minPercentageOfPaidOrders);
        return this;
    }
}

