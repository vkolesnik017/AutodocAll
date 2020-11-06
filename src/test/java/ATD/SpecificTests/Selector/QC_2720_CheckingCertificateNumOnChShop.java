package ATD.SpecificTests.Selector;

import ATD.CommonMethods;
import ATD.Main_page;
import ATD.Main_page_Logic;
import Common.Excel;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.Wait;
import static com.codeborne.selenide.Selenide.sleep;

public class QC_2720_CheckingCertificateNumOnChShop {

    private final String result = "C://Autotests/qc_2720_result.txt";
    private final String file = "C://Autotests/qc_2720_data.xlsx";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "num", parallel = false)
    Object[] dataProvider() {
        return new Excel().setUpFromExcel(file, 0);
    }

    @Test(dataProvider = "num")
    public void checkingNum(String data) throws Exception {
        openPage("https://www.auto-doc.ch/");
        new Main_page_Logic().fillNumberKba(data).clickKbaBtn();
        try {
            Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals("maker_car_list"));
        } catch (Exception e) {
            new CommonMethods().writerInFile(result, true, getNameRouteFromJSVarInHTML() + "#" + data);
        }
    }
}
