package ATD.Characteristics.QC_506_Characteristics;

import ATD.Listing_page_Logic;
import ATD.Product_page_Logic;
import AWS.OptionsOfCharacteristics_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_3359 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        new OptionsOfCharacteristics_aws().openOptionsOfCharacteristicsInAwsWithLogin()
                .setSkin()
                .setGeneric("215")
                .clickSearch()
                .checkVisibleElement("30003")
                .checkSelectedCheckBox("30003", "3");
        close();
    }


    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroutes("prod", "DE", "main", "listing_accessories3,search49");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the output of the characteristic 30003 in the product name")
    public void testCheckingOutputCharacteristic30003InProductName(String route) {
        openPage(route);
        String nameCharacteristic = new Listing_page_Logic().getCharacteristicDesiredProduct("Artikelnummer: 14941", "Modell");
        String titleName = new Listing_page_Logic().getTitleDesiredProduct("Artikelnummer: 14941");
        Assert.assertTrue(titleName.contains(nameCharacteristic));
    }



    @DataProvider(name = "routeProduct", parallel = true)
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroutes("prod", "DE", "main", "product96");
    }

    @Test(dataProvider = "routeProduct")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking the output of the characteristic 30003 in the product name")
    public void test2CheckingOutputCharacteristic30003InProductName(String route) {
        openPage(route);
        String nameCharacteristic = new Product_page_Logic().getCharacteristicProduct("Modell");
        String titleName = new Product_page_Logic().getTitleNameForProductPageAccessories();
        Assert.assertTrue(titleName.contains(nameCharacteristic));
    }



    @AfterMethod
    public void close() {
        closeWebDriver();
    }

}
