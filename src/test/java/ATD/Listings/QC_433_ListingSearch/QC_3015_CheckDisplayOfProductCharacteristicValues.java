package ATD.Listings.QC_433_ListingSearch;

import AWS.OptionsOfCharacteristics_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3015_CheckDisplayOfProductCharacteristicValues {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "test Checking the display of product characteristic values in the main title on the search listing")
    public void testCheckDisplayOfProductCharacteristicValues(String route) {
        List<String> options = new OptionsOfCharacteristics_aws()
                .openOptionsOfCharacteristicsInAwsWithLogin()
                .setSkin()
                .setGeneric("854")
                .clickSearch()
                .getIdMainParameters();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
