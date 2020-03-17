package ATD.General_Common.QC_1571_Header;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.close;


public class QC_1606_BlockHeaderMain {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route", enabled = true)
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks for elements and logic in the Header_Main block")
    public void testChecksForElementsAndLogicInHeaderMainBlock(String route) {
        openPage(route);
        new Main_page_Logic().checkCatalogMenuInHeader()
                .checkPopUpBlockWithHintsWhenClickingOnSearch()
                .checkInfoPopUpForSearch()
                .cartClick().emptyCart().shouldBe(visible);
    }

    @AfterMethod
    private void teatDown() {
        close();
    }
}
