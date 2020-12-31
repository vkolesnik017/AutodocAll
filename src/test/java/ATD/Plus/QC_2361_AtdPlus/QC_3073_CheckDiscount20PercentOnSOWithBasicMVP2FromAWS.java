package ATD.Plus.QC_2361_AtdPlus;

import ATD.Versand_static_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3073_CheckDiscount20PercentOnSOWithBasicMVP2FromAWS {

    private Float safeOrderPrice, allDataSafeOrderPriceFromBlock, allDataSafeOrderPriceFromSummary;
    private String mail = "qc_3073_plusBasicAutotest@mailinator.com";



    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Checking the 20% discount on SO for a user with the Basic MVP2 package when ordering from the AWS")
    public void testCheckDiscount20PercentOnSOWithBasicMVP2FromAWS(String route) throws Exception {
        openPage(route);
        safeOrderPrice = new Versand_static_page_Logic().getSafeOrderPriceWithAnyDiscountAndSubscription(mail);






    }

        @AfterMethod
        private void close () {
            closeWebDriver();
        }

}
