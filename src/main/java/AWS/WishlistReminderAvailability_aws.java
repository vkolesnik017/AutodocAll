package AWS;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.assertEquals;

public class WishlistReminderAvailability_aws {

  private String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).replace("/", "-");


  public String urlWithCurrentDate = "https://aws.autodoc.de/wishlist/reminder/availability?Filter%5Bproject%5D%5B0%5D=_" + "DE"
          + "&Filter%5Bdate%5D%5BdateFrom%5D=" + currentDate + "&Filter%5Bdate%5D%5BdateTo%5D=" + currentDate
          + "&submit=%D0%9F%D0%BE%D0%B8%D1%81%D0%BA"; // TODO будем ли выносить в базу ?


  public SelenideElement articleOfFirstProduct() {
    return $(byXpath(".//*[@id='availability_reminder_table']//tr[1]/td[3]"));
  }

  public SelenideElement numberOfRequestsInFirstProduct() {
    return $(byXpath(".//*[@id='availability_reminder_table']//tr[1]/td[6]"));
  }

  public SelenideElement numberOfRequestsInProductByHisId(String idProduct) {
    return $(byXpath("//td[contains(text(),'" + idProduct + "')]/../td[6]"));
  }

  public SelenideElement idOfFirstProduct() {
    return $(byXpath(".//*[@id='availability_reminder_table']//tr[1]/td[4]"));
  }

  @Step("Check After Count Request. WishlistReminderAvailability_aws")
  public WishlistReminderAvailability_aws checkAfterCountRequest(int beforeCountRequests, String idProduct) {
    int afterCountRequests = Integer.parseInt(numberOfRequestsInProductByHisId(idProduct).text());
    assertEquals(afterCountRequests, beforeCountRequests + 1);
    return this;
  }

  @Step("Get text from article. WishlistReminderAvailability_aws")
  public String getTextFromArticle() {
    String textFromElement = articleOfFirstProduct().text();
    return textFromElement;
  }

  @Step("Get text from id. WishlistReminderAvailability_aws")
  public String getTextFromId() {
    String textFromElement = idOfFirstProduct().text();
    return textFromElement;
  }

  @Step("Get Before Count Requests. WishlistReminderAvailability_aws")
  public int getBeforeCountRequests() {
    return Integer.parseInt(numberOfRequestsInFirstProduct().text());
  }
}
