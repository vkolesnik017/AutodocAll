package ATD.Selectors.QC_729_PopUpsOfSelectors;

import ATD.Main_page_logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.open;

public class QC_736_ErrorTooltipWhenUsingSelectorWithEmptyFields {

  private Main_page_logic mainPageLogic = new Main_page_logic();

  @BeforeClass
  void setUp() {
    setUpBrowser(false, "chrome", "77.0");
  }

  @DataProvider(name = "routes", parallel = true)
  Object[] dataProvider() throws SQLException {
    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,product");
  }

  @Test(dataProvider = "routes")
  @Flaky
  @Owner(value = "Evlentiev")
  @Description(value = "The presence of a tooltip with an error when using the selector with empty fields in the pop-up with selector")
  public void testErrorTooltipWhenUsingSelectorWithEmptyFields(String route) {
    open(route);
    mainPageLogic.fillNumberKba("0000", "000")
            .clickKbaBtn();

    mainPageLogic.suchenCarBtnInCarSelectorPopup().click();
    mainPageLogic.errorTooltipForChooseBrandInCarSelectorPopup().shouldHave(
            exactText("Um Autoteile für Ihren Wagen zu suchen, geben Sie bitte Ihr genaues Automodell an"));

    mainPageLogic.chooseBrandInPopupSelectorForChooseCar("VW")
            .suchenCarBtnInCarSelectorPopup().click();
    mainPageLogic.errorTooltipForChooseModelInCarSelectorPopup().shouldHave(
            exactText("Wählen Sie ein Modell aus"));

    mainPageLogic.chooseModelInPopupSelectorForChooseCar("4644")
            .suchenCarBtnInCarSelectorPopup().click();
    mainPageLogic.errorTooltipForChooseTypeInCarSelectorPopup().shouldHave(
            exactText("Wählen Sie eine Modifikation aus"));
  }

}
