package ATD;

import AWS.Login_aws;
import AWS.WishlistReminderAvailability_aws;
import mailinator.Mailinator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.closeCookiesFooterMessage;
import static ATD.CommonMethods.setUpBrowser;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertEquals;

public class QASYS_69_GrayButton {

  private String email = "CheckGrayButton@mailinator.com";

  Mailinator mailinator = new Mailinator();
  Search_page search_page = new Search_page();
  Product_page product_page = new Product_page();
  WishlistReminderAvailability_aws wishlistReminderAvailability = new WishlistReminderAvailability_aws();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new CommonMethods().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  public void testGrayButton(String route) {
    new Login_aws().loginInAws();
    open(wishlistReminderAvailability.urlWithParameters);
    String articleProduct = wishlistReminderAvailability.articleFirstProduct().text();
    String idProduct = wishlistReminderAvailability.idFirstProduct().text();
    int beforeCountRequests = Integer.parseInt(wishlistReminderAvailability.countRequestsFirstProduct().text());
    open(route);
    new Main_page().useSearch(articleProduct);
    closeCookiesFooterMessage();
    search_page.productButtonById(idProduct).click();
    search_page.emailFieldInPopUpAvailable().setValue(email);
    search_page.checkboxInPopUpAvailable().click();
    search_page.sendenButtonInPopUpAvailable().click();
    search_page.closeSuccessPopUpAvailable().click();
    search_page.imageProductById(idProduct).click();
    product_page.grayButton().click();
    product_page.emailFieldInPopUpAvailable().setValue(email);
    product_page.checkboxInPopUpAvailable().click();
    product_page.sendenButtonInPopUpAvailable().click();
    product_page.closeSuccessPopUpAvailable().click();
    mailinator.openEmail(email);
    mailinator.letter(1).shouldHave(text("moments ago")).shouldHave(text("Wir bearbeiten"));
    mailinator.letter(2).shouldHave(text("moments ago")).shouldHave(text("Wir bearbeiten"));
    open(wishlistReminderAvailability.urlWithParameters);
    int afterCountRequests = Integer.parseInt(wishlistReminderAvailability.countRequestsByIdProduct(idProduct).text());
    assertEquals(afterCountRequests, beforeCountRequests + 2);
    close();
  }

}
