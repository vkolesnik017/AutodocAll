package PKW.SpecificTests;

import Common.Excel;
import PKW.Main_page_Logic;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static Common.Excel.parseExcel;
import static Common.SetUp.setUpBrowser;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class QC_2071 {

    private final String dataFile = "C://Autotests/files/data/QC_2071_data.xls";
    private final String result = "C://Autotests/files/res/QC_2071_result.txt";


    //    private String shop = System.getenv("ShopFromJenkins").toLowerCase();
    private String shop = "uk";

    @BeforeClass
    void setUp() throws IOException {
        setUpBrowser(false, "chrome", "77.0", false);
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

        SelenideElement categoryFirst = $(By.xpath("//div[@class='all-autoparts__categories-item-row']/div[3]"));
        SelenideElement childCategoryFirst = $(By.xpath("//div[@class='all-autoparts__item-lists js-all-autoparts__item-lists']//li"));

        String url = calculateUrlByShop(this.shop.trim());
        System.out.println(url);
        open(url);
        new Main_page_Logic().selectVehicleInSelector(makerId, modelId, carId);
        waitWhileRouteBecomeExpected("catalog");
        categoryFirst.click();
        childCategoryFirst.click(-3, 0);
        waitWhileRouteBecomeExpected("car_parts");

    }


    private String calculateUrlByShop(String shopFromTest) {
        String shop = null;
        switch (shopFromTest) {
            case "es":
                shop = "https://www.recambioscoches.es";
                break;
            case "uk":
                shop = "https://www.buycarparts.co.uk";
                break;
            case "it":
                shop = "https://www.autoparti.it";
                break;
        }
        return shop;
    }
}