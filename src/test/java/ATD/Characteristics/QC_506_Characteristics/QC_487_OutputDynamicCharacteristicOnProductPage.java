package ATD.Characteristics.QC_506_Characteristics;

import Common.DataBase;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.matchText;

public class QC_487_OutputDynamicCharacteristicOnProductPage {

  private Product_page_Logic product_page_logic = new Product_page_Logic();
  private DataBase db = new DataBase("ATD");


  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0", false);
  }

  @DataProvider(name = "route")
  Object[] dataProvider() {
    return new SetUp("ATD").setUpShop("prod", "DE");
  }

  @Test(dataProvider = "route")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Checks output dynamic characteristic on product page")
  public void testOutputDynamicCharacteristicOnProductPage(String route) throws SQLException {
    String desiredCharacteristicRegEx = "einzustellender Elektrodenabstand \\[mm]:\\n0,7";
    openPage(route + "/" + db.getRouteByRouteName(getShopFromRoute(route), "maker_car_list2"));
    product_page_logic.openProductPageById(route, idProductWithDynamicChar)
            .getCharacteristicsOfProduct()
            .filter(matchText(desiredCharacteristicRegEx))
            .shouldHaveSize(1);
  }

}
