package ATD.Selectors.QC_729_PopUpsOfSelectors;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class QC_735_PresenceOfElementsInCarSelectorPopup {

  private Main_page_Logic mainPageLogic = new Main_page_Logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes")
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_car_list,product,listing_accessories");
  }


  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "Presence of elements in popup of selector")
  public void testPresenceOfElementsInPopupOfSelector(String route) {
    open(route);
    mainPageLogic.fillNumberKba("0000", "000").clickKbaBtn();
    mainPageLogic.headingInCarSelectorPopup().shouldHave(exactText("Ihre KBA Eingabe 0000.000 ergab keine Übereinstimmung mit unserer Datenbank"));
    mainPageLogic.blockWithDropdownsOfChooseCarInCarSelectorPopup().shouldBe(visible);
    mainPageLogic.headingCarInCarSelectorPopup().shouldHave(exactText("Oder stellen Sie sicher, dass Sie die KBA-Nummer korrekt eingeben"));
    mainPageLogic.blockWithKbaFieldsInCarSelectorPopup().shouldBe(visible);
    mainPageLogic.linkForTooltipInCarSelectorPopup().hover();
    mainPageLogic.tooltipInCarSelectorPopup().shouldBe(visible);
  }
  @AfterMethod
  private void tearDown() {
    close();
  }
}
