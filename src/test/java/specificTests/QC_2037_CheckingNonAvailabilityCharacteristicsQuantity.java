package specificTests;

import Common.Excel;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.UIAssertionError;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class QC_2037_CheckingNonAvailabilityCharacteristicsQuantity {

    private final String dataFile = "C://Autotests/files/data/QC_2037_data.xls";
    private final String result = "C://Autotests/files/res/QC_2037_result.txt";


    private SelenideElement characteristicBlock = $(By.xpath("//div[@class='product-block__description']"));

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "data", parallel = true)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile);
    }

    @Owner(value = "Alex")
    @Test(dataProvider = "data")
    @Description(value = "В блоке характеристик товара, проверить отсутствие характеристики Menge")
    public void testDeleteTiresFromBasket(String url) throws IOException {
        try {
            open(url);
            characteristicBlock.shouldNotHave(Condition.text("Menge"));
        }catch (Throwable e){
            writer(result, true, url);
        }
    }

    @AfterMethod
    private void tearDown() {
        closeWebDriver();
    }


    private void closePopup() {
        try {
            $(byXpath("//span[@class='popup-review__close']")).click();
        } catch (UIAssertionError e) {
            System.out.println("Popup doesn't appear");
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
