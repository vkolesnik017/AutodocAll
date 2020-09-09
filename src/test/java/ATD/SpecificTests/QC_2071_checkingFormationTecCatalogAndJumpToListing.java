package ATD.SpecificTests;

import Common.Excel;
import ATD.Main_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static ATD.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class QC_2071_checkingFormationTecCatalogAndJumpToListing {

    private final String dataFile = "C://Autotests/files/data/QC_2071_data.xls";
    private final String result = "C://Autotests/files/res/QC_2071_result.txt";


    //    private String shop = System.getenv("ShopFromJenkins").toLowerCase();
    private String shop = "it";

    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "data", parallel = false)
    Object[] dataProvider() {
        return new Excel().setUpAllCellFromExcel(dataFile, shop);
    }

    @Owner(value = "Alex")
    @Test(dataProvider = "data")
    @Description(value = "")
    public void testCheckingFormationTecCatalogAndJumpToListing(String data) throws IOException {
        System.out.println(data);
        String makerId = parseExcel(data)[0].trim();
        System.out.println("makerId" + makerId);
        String modelId = parseExcel(data)[1].trim();
        System.out.println("modelId" + modelId);
        String carId = parseExcel(data)[2].trim();
        System.out.println("carId" + carId);

        SelenideElement carImage = $(By.xpath("//div[@class='car_image'][2]"));
        SelenideElement categoryFrirst = $(By.xpath("//ul[@class='expand list_ersats_row']//li[@class=' ctg']"));
        SelenideElement childCategoryFrirst = $(By.xpath("//li[@class='ctg active']//li[1]"));

        String url = calculateUrlByShop(this.shop.trim());
        System.out.println(url);
        open(url);
        new Main_page_Logic().selectVehicleInSelectorOfMyGarage(makerId, modelId, carId);
        waitWhileRouteBecomeExpected("maker_car_list");
        carImage.scrollTo();
        categoryFrirst.click();
        childCategoryFrirst.click();
        waitWhileRouteBecomeExpected("category_car_list");

    }

    private void writer(String fileName, boolean append, String write) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName, append), StandardCharsets.UTF_8));
        System.out.println("Write in file" + " " + fileName);
        bufferedWriter.newLine();
        bufferedWriter.write(write);
        bufferedWriter.close();
    }


    private String calculateUrlByShop(String shopFromTest) {
        String shop = null;
        switch (shopFromTest) {
            case "es":
                shop = "https://www.autodoc.es";
                break;
            case "uk":
                shop = "https://www.autodoc.co.uk";
                break;
            case "it":
                shop = "https://www.auto-doc.it";
                break;
        }
        return shop;
    }
}