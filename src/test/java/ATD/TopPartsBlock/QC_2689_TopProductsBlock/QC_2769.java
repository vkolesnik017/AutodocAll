package ATD.TopPartsBlock.QC_2689_TopProductsBlock;

import ATD.Main_page_Logic;
import Common.DataBase;
import Common.SetUp;
import files.Car;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2769 {

    private final String file = "C://Autotests/files/data/QC_2769.xls";
    private Main_page_Logic mainPage = new Main_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "ES", "main", "main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test Checking the availability of top products for top-250 cars")
    public void testCheckingAvailabilityOfTopProductsForTop250Cars(String route) throws SQLException {
        openPage(route);

        List<Car> fullFile = mainPage.getAllCarValuesFromFile(file);
        List<Car> topCars = mainPage.getSpecificValuesFromFile(fullFile, 0, 250);
        List<Car> notTopCars = mainPage.getSpecificValuesFromFile(fullFile, 251, 500);
        int randomNumber = mainPage.getRandomNumber(250);
        mainPage.selectRandomCarFromFile(topCars, randomNumber).presenceOfTopProductsBlock().checkSizeOfTopProducts(12).checkingAbilityToAddProductToBasket()
                .compareTopProducts(12);
        openPage(new DataBase("ATD").getFullRouteByRouteName("prod", "ES", "main"));
        mainPage.resetVerticalCarSelector();
        mainPage.selectRandomCarFromFile(notTopCars, randomNumber).absenceOfTopProductsBlock();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
