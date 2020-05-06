package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.CommonMethods;
import ATD.Product_page_Logic;
import ATD.SetUp;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;

public class QC_513_PresenceStaticCharacteristicInBasket {

  private Product_page_Logic productPageLogic = new Product_page_Logic();

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
  @Description(value = "Checks presence static characteristics in basket")
  public void testPresenceStaticCharacteristicInBasket(String route) {
    clearBrowserCache();

    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n280");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n63,5");
    expectedCharacteristics.add("Bohrbild/Lochzahl:\\n05/05");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n108,0");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n11,0");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n9,0");
    expectedCharacteristics.add("Höhe \\[mm]:\\n50,7");
    expectedCharacteristics.add("Gewicht\\[kg]:\\n4,6");
    expectedCharacteristics.add("Ergänzungsartikel/Ergänzende Info 2:\\nohne Radnabe, ohne Radbefestigungsbolzen");
    expectedCharacteristics.add("Zustand:\\nBrandneu");

    ElementsCollection actualCharacteristics =
            productPageLogic.openProductPageById(route, "8039059")
                    .addProductToCart()
                    .closePopupOtherCategoryIfYes()
                    .cartClick()
                    .getCharacteristicsOfProduct();
    new CommonMethods().compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }

  @AfterMethod
  private void teatDown() {
    close();
  }

}
