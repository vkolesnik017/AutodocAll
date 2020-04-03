package specificTests;

import ATD.CommonMethods;
import ATD.Excel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static ATD.SetUp.setUpBrowser;

public class QASYS_515_CheckingCodeResponseAnswer {

    private final String result = "C://Autotests/files/victorUrlRespons.txt";
    private final String file = "D://ACC-499.xls";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "url", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpFromExcel(file, 0);
    }

    @Test(dataProvider = "url")
    public void CheckingCodeResponseAnswer(String data) throws Exception {
        URL url = new URL(data);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setInstanceFollowRedirects(true);
        int responseCode = http.getResponseCode();
        System.out.println(data);
        System.out.println(responseCode);
        new CommonMethods().writerInFile(result, true, data + "#" + responseCode);
    }
}
