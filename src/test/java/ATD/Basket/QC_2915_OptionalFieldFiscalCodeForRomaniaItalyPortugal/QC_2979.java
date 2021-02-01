package ATD.Basket.QC_2915_OptionalFieldFiscalCodeForRomaniaItalyPortugal;

import AWS.Order_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2979 {

    private String user = "qc_2979_autotest@mailinator.com" + "userId = 22703867";  //TODO  If you need a new order, then do it with this user

    private String orderIdForIT = "42404636";
    private String orderIdForPT = "42405232";
    private String orderIdForRO = "42404963";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "orderID", parallel = true)
    Object[] dataProvider() {
        return new Object[][] { {orderIdForIT}, {orderIdForPT}, {orderIdForRO}
        };
    }


    @Test(dataProvider = "orderID")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Successful saving of changes, with a filled / empty field \"Fiscal code\" (editing an order in AWS)")
    public void testSuccessfulSavingOfChangesInFiscalCodeField(String orderID) {
        new Order_aws(orderID).openOrderInAwsWithLogin();


    }



    @AfterMethod
    private void close() {
        closeWebDriver();
    }

}



