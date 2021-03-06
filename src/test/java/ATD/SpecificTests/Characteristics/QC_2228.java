package ATD.SpecificTests.Characteristics;

import Common.DataBase;
import Common.Excel;
import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static ATD.CommonMethods.openPage;
import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;

public class QC_2228 {

    private final String dataFile = "D://FilesFromAutotest/data/QC_2228_data.xls";
    private final String result = "D://FilesFromAutotest/data/QC_2228_result.txt";
    private String characteristicName = "Brandneu";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "data", parallel = false)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }

    @Owner(value = "Chelombitko")
    @Test(dataProvider = "data")
    @Description("Checks output status 'new' for MAPCO items")
    public void testDisplayStatusNewForMapcoGoodsATD(String data) throws Exception {
        String articleNum = parseExcel(data)[0].trim();
        try {
            openPage(new DataBase("ATD").getFullRouteByRouteName("prod", "DE", "main"));
            new Main_page_Logic().inputTextInSearchBar(articleNum)
                    .headerSearchSubmitBtn().click();
            new Listing_page_Logic().checkListingWithSelectingFilterByBrand("133", "MAPCO")
                    .checkNameOfFeatureStateIfArticleNumMatchesExpectedOne(articleNum, characteristicName)
                    .goToProductPageAndCheckThatNameOfCharacteristicFeatureIsExpected(articleNum, characteristicName);
        } catch (Throwable e) {
            writer(result, true, articleNum + "#" + "Fail");
        }
    }

    private void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file" + " " + fileName);
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }
}

