package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.Listing_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.clickOfBuyBtnForAllPages;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_513_PresenceStaticCharacteristicInBasket {

  private Product_page_Logic productPageLogic = new Product_page_Logic();
  private List<String> expectedCharacteristics;

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "route", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen4");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks presence static characteristics in basket")
  public void testPresenceStaticCharacteristicInBasket(String route) {
    openPage(route);
    expectedCharacteristics = new Listing_page_Logic().getExpectedCharacteristcsOfProduct();
    clickOfBuyBtnForAllPages();
    ElementsCollection actualCharacteristics =
            productPageLogic.closePopupOtherCategoryIfYes()
                            .cartClick()
                            .getCharacteristicsOfProduct();
    new Product_page_Logic().compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @AfterMethod
  private void close() {
    closeWebDriver();
  }

}
