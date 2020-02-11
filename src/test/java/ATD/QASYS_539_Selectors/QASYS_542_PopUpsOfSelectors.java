//package ATD.QASYS_539_Selectors;
//
//import ATD.Main_page;
//import ATD.Main_page_logic;
//import ATD.SetUp;
//import io.qameta.allure.Description;
//import io.qameta.allure.Flaky;
//import io.qameta.allure.Owner;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.sql.SQLException;
//
//import static ATD.SetUp.setUpBrowser;
//import static com.codeborne.selenide.Condition.text;
//import static com.codeborne.selenide.Condition.visible;
//import static com.codeborne.selenide.Selenide.open;
//
//public class QASYS_542_PopUpsOfSelectors {
//
//  private Main_page mainPage = new Main_page();
//  private Main_page_logic mainPageLogic = new Main_page_logic();
//
//  @BeforeClass
//  void setUp() {
//    setUpBrowser(false, "chrome", "77.0");
//  }
//
//  @DataProvider(name = "routes")
//  Object[] dataProvider() throws SQLException {
//    return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","main,category_car_list,product,listing_accessories");
//  }
//
//  @Test(dataProvider = "routes")
//  @Flaky
//  @Owner(value = "Evlentiev")
//  @Description(value = "Appears popup when input wrong kba number")
//  public void testAppearsPopupWhenWrongKbaNumber(String route) {
//    open(route);
//    mainPageLogic.fillNumberKba("0000", "000")
//            .clickKbaBtn();
//    mainPage.kbaPopupError().shouldBe(visible);
//    mainPage.headerInPopupOfKbaError().shouldBe(visible).shouldHave(text("0000.000"));
//    mainPage.brandSelectorInPopupOfKbaError().shouldBe(visible);
//    mainPage.modelSelectorInPopupOfKbaError().shouldBe(visible);
//    mainPage.typeSelectorInPopupOfKbaError().shouldBe(visible);
//    mainPage.headerSelectorCarInPopupOfKbaError().shouldBe(visible);
//    mainPage.firstKbaFieldInPopupOfKbaError().shouldBe(visible);
//    mainPage.secondKbaFieldInPopupOfKbaError().shouldBe(visible);
//    mainPage.selectorKbaBtnInPopupOfKbaError().click();
//    mainPage.tooltipErrorKbaInPopupOnKbaError().shouldBe(visible);
//  }
//}
