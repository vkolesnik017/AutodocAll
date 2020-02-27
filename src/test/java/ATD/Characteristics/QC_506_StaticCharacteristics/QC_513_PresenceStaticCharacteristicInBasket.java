package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.CommonMethods;
import ATD.Product_page_Logic;
import ATD.SetUp;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static ATD.SetUp.setUpBrowser;
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
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n239");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65");
    expectedCharacteristics.add("Lochanzahl:\\n5");
    expectedCharacteristics.add("Felge Lochzahl:\\n5");
    expectedCharacteristics.add("Bohrung-Ø 2 \\[mm]:\\n6,6");
    expectedCharacteristics.add("Bohrung-Ø 1 \\[mm]:\\n15,75");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n8,9");
    expectedCharacteristics.add("Mindestdicke \\[mm]:\\n7");
    expectedCharacteristics.add("Innendurchmesser \\[mm]:\\n132,5");
    expectedCharacteristics.add("Höhe \\[mm]:\\n33,4");
    expectedCharacteristics.add("Bohrung-Ø \\[mm]:\\n15,8");

    ElementsCollection actualCharacteristics =
            productPageLogic.openProductPageById(route, "7999323")
                    .addProductToCart()
                    .closePopupOtherCategoryIfYes()
                    .cartClick()
                    .getCharacteristicsOfProduct();
    new CommonMethods().compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }


}
