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

public class QC_507_PresenceStaticCharacteristicOnProductPage {

  private Product_page_Logic productPageLogic = new Product_page_Logic();
  private CommonMethods commonMethods = new CommonMethods();

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
  @Description(value = "Checks presence static characteristics on product page")
  public void testPresenceStaticCharacteristicOnProductPage(String route) {
    ArrayList<String> expectedCharacteristics = new ArrayList<>();
    expectedCharacteristics.add("Einbauseite:\\nHinterachse");
    expectedCharacteristics.add("Durchmesser \\[mm]:\\n230");
    expectedCharacteristics.add("Bremsscheibenart:\\nVoll");
    expectedCharacteristics.add("Zentrierungsdurchmesser \\[mm]:\\n65,0");
    expectedCharacteristics.add("Bohrbild/Lochzahl:\\n05/06");
    expectedCharacteristics.add("Lochkreis-Ø \\[mm]:\\n100,0");
    expectedCharacteristics.add("Bremsscheibendicke \\[mm]:\\n9,0");

    ElementsCollection actualCharacteristics = productPageLogic.openProductPageById(route, "7998901")
            .getCharacteristicsOfProduct();
    commonMethods.compareCharacteristics(actualCharacteristics, expectedCharacteristics);
  }
}
