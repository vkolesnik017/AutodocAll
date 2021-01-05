package ATD.Plus.QC_2361_AtdPlus;

import AWS.Order_aws;
import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2725 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @Test()
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the correct operation of not the first re-order from the user")
    public void testCheckingCorrectOperationOfNotFirstReOrderFromUser() {
        new SearchOrders_page_aws().openSearchPageWithExpectedData(1, 1)
                .selectExpectedGroupField("AUTODOC PLUS (Services)")
                .clickSearchBtn()
                .togglesPaginationIfThereAreNoParentAndReOrderNumberBlocksInOrder(new Order_aws().blocksParentAndReOrderNumber());
        new Order_aws().checkCurrentStatusInOrder("Abgeschlossen");
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}
