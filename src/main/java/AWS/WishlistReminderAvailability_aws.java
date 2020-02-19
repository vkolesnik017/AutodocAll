package AWS;

import com.codeborne.selenide.SelenideElement;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class WishlistReminderAvailability_aws {

  private String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).replace("/", "-");


  public String urlWithCurrentDate = "https://aws.autodoc.de/wishlist/reminder/availability?Filter%5Bproject%5D%5B0%5D=_" + "DE"
          + "&Filter%5Bdate%5D%5BdateFrom%5D=" + currentDate + "&Filter%5Bdate%5D%5BdateTo%5D=" + currentDate
          + "&submit=%D0%9F%D0%BE%D0%B8%D1%81%D0%BA"; // TODO будем ли выносить в базу ?

  public String urlWithData20191027 = "https://aws.autodoc.de/wishlist/reminder/availability?Filter%5Bproject%5D%5B0%5D=_" + "DE"
          + "&Filter%5Bdate%5D%5BdateFrom%5D=" + "2019-10-27" + "&Filter%5Bdate%5D%5BdateTo%5D=" + "2019-10-27"
          + "&submit=%D0%9F%D0%BE%D0%B8%D1%81%D0%BA";

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

}
