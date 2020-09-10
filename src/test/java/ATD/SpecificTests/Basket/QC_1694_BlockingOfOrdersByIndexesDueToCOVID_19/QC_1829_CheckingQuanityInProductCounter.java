package ATD.SpecificTests.Basket.QC_1694_BlockingOfOrdersByIndexesDueToCOVID_19;

import Common.Excel;
import ATD.Product_page_Logic;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.open;

public class QC_1829_CheckingQuanityInProductCounter {
    private final String dataFile = "C://Autotests/files/data/QC_1829_data.xls";
    private final String result = "C://Autotests/files/res/QC_1829_result.txt";

    private final String url = "https://test.autodoc.de/bosch";


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
        Configuration.pageLoadStrategy = "none";
    }

    @DataProvider(name = "data", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }


    @Test(dataProvider = "data")
    public void page(String data) throws Exception {
        String urlId = parseExcel(data)[0].trim();
        String expectedQuanity = parseExcel(data)[1].trim();
        try {
            open(url.concat("/").concat(urlId));
            System.out.println(url.concat("/").concat(urlId));
            String quanityOnPage = new Product_page_Logic().getProductQuanity();
            if (!expectedQuanity.equals(quanityOnPage)) writer(result, true, urlId);
        } catch (Throwable e) {
            writer(result, true, urlId + "#" + "Fail");
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
