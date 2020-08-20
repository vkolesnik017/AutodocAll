package ATD.SpecificTests;

import ATD.CommonMethods;
import ATD.Excel;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static ATD.CommonMethods.openPage;
import static ATD.Excel.parseExcel;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2337_addingNewPkwMotoInSelector {
    private CommonMethods commonMethods = new CommonMethods();

    private final String dataFile = "C://Autotests/files/data/QC_2337_data.xls";
    private final String result = "C://Autotests/files/res/QC_2337_data.txt";

    private String shop = System.getenv("ShopFromJenkins").toLowerCase();
//    private String shop = "de";

    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0");
        Configuration.pageLoadStrategy = "normal";
        commonMethods.writerInFile(result, true, shop);
    }

    @DataProvider(name = "data", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile, shop);
    }


    @Test(dataProvider = "data")
    public void listing(String data) throws Exception {
        System.out.println(data);
        String maker_id = parseExcel(data)[0].trim();
        String model_id = parseExcel(data)[0].trim();
        String group_id = parseExcel(data)[0].trim();
        String car_id = parseExcel(data)[0].trim();

        String makerName = parseExcel(data)[0].trim();
        String groupName = parseExcel(data)[2].trim();
        String modelName = parseExcel(data)[3].trim();
        String carName = parseExcel(data)[3].trim();
        String yearBegin = parseExcel(data)[3].trim();
        String yearEnd = parseExcel(data)[3].trim();
        String kw = parseExcel(data)[3].trim();
        String hp = parseExcel(data)[3].trim();


        String startUrl = "https://test.buycarparts.co.uk/search&maker_id=" + maker_id + "&model_id=" + model_id + "&group_id=" + group_id + "&car_id=" + car_id;
        openPage(startUrl);

        try {

        } catch (ElementNotFound element) {
            commonMethods.writerInFile(result, true, "Trouble with element in selector" + "#" + startUrl);
        }
        closeWebDriver();

    }


}

