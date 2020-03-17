package ATD.Listings.QC_423_ListingOEN;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.usualIdProduct;
import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_425_GoingToRouteCategoryOenFromOenNumbersBlockOnProductPage {

  private Product_page_Logic productPageLogic = new Product_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() {
    return new SetUp().setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Going to root category_oen from OEN block on product page")
  public void testGoingToRouteCategoryOenFromOenNumberBlockOnProductPage(String route) {
    openPage(route);
    productPageLogic.openProductPageById(route, usualIdProduct)
            .clickFirstLinkInOenBlock();
    waitWhileRouteBecomeExpected("category_oen");
  }

  @AfterMethod
  private void tearDown() {
    close();
  }
}
