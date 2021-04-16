package PKW.SpecificTests.Selector;

import Common.Excel;
import PKW.CommonMethods;
import PKW.Search_page_Logic;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_3592 {

    private CommonMethods commonMethods = new CommonMethods();

    private final String dataFile = "C://Autotests/files/data/QC_3592.xls";
    private final String result = "C://Autotests/files/data/QC_3592.txt";

    /*НУЖНА РЕАЛИЗАЦИЯ ПОДКЛЮЧЕНИЯ ЯЗЫКОВ ЧЕРЕЗ БД*/
    //
    List<String> languages = Arrays.asList("https://test.pkwteile.at", "https://test.avtochastionline24.bg", "https://test.pkwteile.ch", "https://test.automobilovedily24.cz", "https://test.pkwteile.de",
            "https://test.bildeleshop.dk", "https://test.buycarparts.co.uk", "https://test.recambioscoches.es", "https://test.autonvaraosat24.fi", "https://test.piecesauto24.com", "https://test.antallaktikaonline.gr",
            "https://test.autoparti.it", "https://test.auto-onderdelen24.nl", "https://test.autodeler.co.no", "https://test.czesciauto24.pl", "https://test.autopecasonline24.pt", "https://test.autopieseonline24.ro",
            "https://test.bildelaronline24.se");

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
        Configuration.pageLoadStrategy = "normal";
    }

    @DataProvider(name = "data", parallel = false)
    Object[] dataProvider() {

       //    return new Excel().setUpAllCellFromExcel(dataFile);
        return new Object[][]{{"https://wwww.pkwteile.de" , "https://www.avtochastionline24.bg"},{new Excel().setUpAllCellFromExcel(dataFile)}};
    }


    @Test(dataProvider = "data")
    @Description(value = "test Check Changed Title Of Model Name In Selector")
    public void testCheckChangedTitleOfModelNameInSelector(List<String> data, String ex) throws Exception {
    //    openPage(data);

        System.out.println(data);
        String currentUrl = data.get(0).replaceAll("^.+\\.", "").toUpperCase();
        String maker_id = parseExcel(currentUrl)[0].trim();
        String model_id = parseExcel(currentUrl)[4].trim();
        String group_id = parseExcel(currentUrl)[2].trim();



        String startUrl = data+"/search?&maker_id=" + maker_id + "&model_id=" + model_id + "&group_id=" + group_id ;
        openPage(startUrl);







        new Search_page_Logic().compareSelectorWithFile(languages, dataFile, result);
        closeWebDriver();
    }
}
