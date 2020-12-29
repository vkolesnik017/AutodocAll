package ATD.SpecificTests.Selector;

import ATD.CommonMethods;
import ATD.Main_page_Logic;
import Common.Excel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.getNameRouteFromJSVarInHTML;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_2720_CheckingCertificateNumOnChShop {

    private final String result = "C://Autotests/qc_2720_result.txt";
    private final String file = "C://Autotests/qc_2720_data.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "num", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpFromExcel(file, 0);
    }

    @Test(dataProvider = "num")
    public void checkingNum(String data) throws Exception {
        openPage("https://www.auto-doc.ch/");
        refresh();
        sleep(4000);
        new Main_page_Logic().fillNumberKba(data).clickKbaBtn();
        try {
            Wait().until(WebDriver -> getNameRouteFromJSVarInHTML().equals("maker_car_list"));
        } catch (Exception e) {
            new CommonMethods().writerInFile(result, true, getNameRouteFromJSVarInHTML() + "#" + data);
        }
        closeWebDriver();
    }
}
