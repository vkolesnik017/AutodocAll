package ATD.Orders_AWS_Delivery.QC_2994_FilterByOrdersWithPaylinksInOrderSearch;

import AWS.SearchOrders_page_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static Common.CommonMethods.generationRandomDates;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2998_CheckingSearchBy_GroupField_FilterLinkSurcharges_FillingDataPeriod {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "random", parallel = true)
    Object[] dataRandom() {
        String data1 = generationRandomDates(1);
        String data2 = generationRandomDates(1);
        String data3 = generationRandomDates(1);
        return new Object[][]{
                {data1, data1},
                {data2, data2},
                {data3, data3}
        };
    }

    @Test(dataProvider = "random")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Checking the operation of the search using the Group Field filter: Link Surcharges + filling in the date period in AWS")
    public void testCheckingSearchBy_GroupField_Filter_FilterLinkSurcharges_FillingDataPeriod(String dataFrom, String dataBefore) {
        new SearchOrders_page_aws().openSearchPageWithExpectedData(dataFrom, dataBefore)
                .selectExpectedGroupField("Линк Доплаты")
                .clickSearchBtn()
                .clickOnRandomOrderID(1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}