package PKW.FiltersSorting.QC_147_FiltersSorting_interaction;

import Common.SetUp;
import PKW.Car_parts_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static Common.SetUp.setUpBrowser;
import static PKW.CommonMethods.openPage;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.refresh;

public class QC_152 {

    private Car_parts_Logic carPartsLogic = new Car_parts_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProviderProducts() throws SQLException {
        return new SetUp("PKW").setUpShopWithSubroutes("prod", "DE", "main", "car_parts5");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test check bremsscheibenart and by side filter interaction")
    public void testBremsscheibenartAndBySideFilterInteraction(String route) {
        openPage(route);
        refresh();
        String criteriaFrontAxle = carPartsLogic.getNameFrontAxle().toLowerCase();
        carPartsLogic.clickFrontAxle();
        String criteriaBremsscheibenart = carPartsLogic.getTextCriteriaBremsscheibenart().toLowerCase();
        carPartsLogic.clickCriteriaBremsscheibenart()
                .checkCharacteristicInProducts(carPartsLogic.characteristicAxleSideFomProductBlock(), criteriaFrontAxle)
                .checkCharacteristicInProductsIfHasMultipleValues(carPartsLogic.characteristicBremsscheibenartFomProductBlock(), criteriaBremsscheibenart);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
