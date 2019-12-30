package ATD.QASYS_5_Listings;

import ATD.DataBase;
import ATD.ListingTecDocSoft404_page;
import ATD.Listing_page;
import ATD.SetUp;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.getShopFromRoute;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class QASYS_11_LisitngSoft404 {

  private DataBase db = new DataBase();
  private Listing_page listingPage = new Listing_page();
  private ListingTecDocSoft404_page soft404Page = new ListingTecDocSoft404_page();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-1. Checks no list products on soft 404")
  public void testNoListProductsOnSoft404(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list11"));
    soft404Page.blockOfNoFindProduct().shouldBe(visible);
    listingPage.listProducts().shouldBe(not(visible));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-2. Checks availability request form")
  public void testAvailabilityRequestForm(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list11"));
    soft404Page.emailForm().setValue("testatd@gmail.com");
    soft404Page.newsletterSubscriptionCheckbox().click();
    soft404Page.submitBtn().click();
    soft404Page.successPopupAfterSubscribeOnProductsForCar().shouldHave(text("Mit großer Freude informieren wir Sie, sobald die Produkte auf Lager sind"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-3. Checks validation of required fileds on soft 404")
  public void testValidationOfRequiredEmailFieldOnSoft404(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list11"));
    soft404Page.submitBtn().click();
    soft404Page.errorTooltipOfEmailField().shouldHave(text("Bitte geben Sie eine gültige E-mail Adresse an"));
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Test-4. Checks validation of required checkbox newsletter subscription on soft 404")
  public void testValidationOfRequiredCheckboxNewsletterSubscriptionOnSoft404(String route) throws SQLException {
    open(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "category_car_list11"));
    soft404Page.emailForm().setValue("testatd@gmail.com");
    soft404Page.submitBtn().click();
    soft404Page.popupErrorConfirmYourNewsletter().shouldHave(text("Um fortzufahren bestätigen Sie bitte Ihr Newsletter-Abo"));
  }
}
