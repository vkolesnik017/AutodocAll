package AWS;

import ATD.Main_page_Logic;
import ATD.Search_page_Logic;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.Assert.assertEquals;

public class WishlistReminderAvailability_aws {

    private String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).replace("/", "-");


    public String urlWithCurrentDate = "https://aws.autodoc.de/wishlist/reminder/availability?Filter%5Bproject%5D%5B0%5D=_" + "DE"
            + "&Filter%5Bdate%5D%5BdateFrom%5D=" + currentDate + "&Filter%5Bdate%5D%5BdateTo%5D=" + currentDate
            + "&submit=%D0%9F%D0%BE%D0%B8%D1%81%D0%BA"; // TODO будем ли выносить в базу ?


    public SelenideElement firstRowInTable() {
        return $x(".//*[@id='availability_reminder_table']//tbody/tr[1]");
    }

    public SelenideElement articleCategoryInTable(String nameCategories) {
        return $x("//*[@id='availability_reminder_table']//tbody//td[2]//a[text()='" + nameCategories + "']/../..//td[3]");
    }

    public SelenideElement idCategoryInTable(String nameCategories) {
        return $x("//*[@id='availability_reminder_table']//tbody//td[2]//a[text()='" + nameCategories + "']/../..//td[4]");
    }

    public ElementsCollection columnNameCategory() {
        return $$x("//*[@id='availability_reminder_table']//tbody/tr/td[2]");
    }

    public SelenideElement numberOfRequests(String numberOfRequest) {
        return $(byXpath("//*[@id='availability_reminder_table']//tbody//td[2]//a[text()='" + numberOfRequest + "']/../..//td[6]"));
    }

    public SelenideElement numberOfRequestsInProductByHisId(String idProduct) {
        return $(byXpath("//td[contains(text(),'" + idProduct + "')]/../td[6]"));
    }

    @Step("Check After Count Request. WishlistReminderAvailability_aws")
    public WishlistReminderAvailability_aws checkAfterCountRequest(int beforeCountRequests, String idProduct) {
        int afterCountRequests = Integer.parseInt(numberOfRequestsInProductByHisId(idProduct).text());
        assertEquals(afterCountRequests, beforeCountRequests + 1);
        return this;
    }

    @Step("Check After Count Request. WishlistReminderAvailability_aws")
    public WishlistReminderAvailability_aws checkAfterCountRequest(List <String> parameters) {
        int numberOfRequests = Integer.parseInt(parameters.get(0));
        String idProduct = parameters.get(1);
        int afterCountRequests = Integer.parseInt(numberOfRequestsInProductByHisId(idProduct).text());
        assertEquals(afterCountRequests, numberOfRequests + 1);
        return this;
    }

    @Step("Get text from article. WishlistReminderAvailability_aws")
    public String getTextFromArticle() {
        String textFromElement = null;
        firstRowInTable().waitUntil(visible, 5000);
        for (int i = 0; i < columnNameCategory().size(); i++) {
            String nameCategory = columnNameCategory().get(i).getText();
            if (!nameCategory.contains("Tyres")) {
                textFromElement = articleCategoryInTable(nameCategory).getText();
                break;
            }
        }
        return textFromElement;
    }

    @Step("Get text from id. WishlistReminderAvailability_aws")
    public String getTextFromId() {
        String textFromElement = null;
        firstRowInTable().waitUntil(visible, 5000);
        for (int i = 0; i < columnNameCategory().size(); i++) {
            String nameCategory = columnNameCategory().get(i).getText();
            if (!nameCategory.contains("Tyres")) {
                textFromElement = idCategoryInTable(nameCategory).getText();
                break;
            }
        }
        return textFromElement;
    }

    @Step("Get Before Count Requests. WishlistReminderAvailability_aws")
    public int getBeforeCountRequests() {
        String numberOfRequests = null;
        firstRowInTable().waitUntil(visible, 5000);
        for (int i = 0; i < columnNameCategory().size(); i++) {
            String nameCategory = columnNameCategory().get(i).getText();
            if (!nameCategory.contains("Tyres")) {
                numberOfRequests = numberOfRequests(nameCategory).getText();
                break;
            }
        }
        return Integer.parseInt(numberOfRequests);
    }

    @Step("Get number of request and product ID. WishlistReminderAvailability_aws")
    public List<String> getNumberOfRequestAndProductID(String route, String email, String urlWithCurrentDate) {
        Search_page_Logic search_page_logic = new Search_page_Logic();
        String article = null;
        String productID = null;
        String numberOfRequests = null;
        List<String> parameters = new ArrayList<>();
        for (int i = 0; i < columnNameCategory().size(); i++) {
            String nameCategory = columnNameCategory().get(i).getText();
            if (!nameCategory.contains("Tyres")) {
                article = articleCategoryInTable(nameCategory).getText();
                productID = idCategoryInTable(nameCategory).getText();
                numberOfRequests = numberOfRequests(nameCategory).getText();
            }
            openPage(route);
            new Main_page_Logic().useSearch(article);
            if (search_page_logic.buttonProductById(productID).isDisplayed()) {
                search_page_logic.clickButtonProductById(productID);
                search_page_logic.sendRequestByGrayButtonFromSearchPage(email);
                break;
            } else {
                open(urlWithCurrentDate);
            }

        }
        parameters.add(numberOfRequests);
        parameters.add(productID);
        return parameters;
    }
}
