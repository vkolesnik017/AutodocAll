package PKW.SpecificTests;

import Common.Excel;
import PKW.CommonMethods;
import PKW.Main_page;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ex.ElementNotFound;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2337 extends Main_page {
    private CommonMethods commonMethods = new CommonMethods();

    private final String dataFile = "C://Autotests/files/data/QC_2337_data.xls";
    private final String result = "C://Autotests/files/res/QC_2337_data.txt";

//    private String shop = System.getenv("ShopFromJenkins").toLowerCase();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        Configuration.pageLoadStrategy = "normal";
    }

    @DataProvider(name = "data", parallel = false)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }


    @Test(dataProvider = "data")
    public void listing(String data) throws Exception {
        System.out.println(data);
        String maker_id = parseExcel(data)[0].trim();
        String model_id = parseExcel(data)[4].trim();
        String group_id = parseExcel(data)[2].trim();
        String car_id = parseExcel(data)[6].trim();

        String makerName = parseExcel(data)[1].trim();
        String groupName = parseExcel(data)[3].trim();
        String modelName = parseExcel(data)[5].trim();
        String carName = parseExcel(data)[7].trim();
        String yearBegin = parseExcel(data)[9].trim();
        String yearEnd = parseExcel(data)[10].trim();
        String kw = parseExcel(data)[11].trim();
        String hp = parseExcel(data)[12].trim();


        String startUrl = "https://www.buycarparts.co.uk/search?&maker_id=" + maker_id + "&model_id=" + model_id + "&group_id=" + group_id + "&car_id=" + car_id;
        openPage(startUrl);

        try {
            commonMethods.checkingMakerName(makerNameSelected(), makerName, result, startUrl);
            commonMethods.checkingGroupName(groupNameSelected(), groupName, result, startUrl);
            commonMethods.checkingModelName(modelNameSelected(), modelName, result, startUrl);
            commonMethods.checkingCarName(carNameSelected(), carName, yearBegin, yearEnd, kw, hp, result, startUrl);
        } catch (ElementNotFound element) {
            commonMethods.writerInFile(result, true, "Trouble with element in selector" + "#" + startUrl);
        }
        closeWebDriver();
    }
}

