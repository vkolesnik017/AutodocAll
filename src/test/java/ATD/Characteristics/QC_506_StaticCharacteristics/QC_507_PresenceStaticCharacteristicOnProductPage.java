package ATD.Characteristics.QC_506_StaticCharacteristics;

import ATD.Product_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_507_PresenceStaticCharacteristicOnProductPage {

    private Product_page_Logic productPageLogic = new Product_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Checks presence static characteristics on product page")
    public void testPresenceStaticCharacteristicOnProductPage(String route) {
        List<String> expectedCharacteristics = Arrays.asList("Einbauseite: Hinterachse", "Durchmesser [mm]: 230,0", "Bremsscheibenart: Voll",
                "Zentrierungsdurchmesser [mm]: 65,0", "Lochanzahl: 5", "Lochkreis-Ø [mm]: 100,0", "Bremsscheibendicke [mm]: 9,0");
        List<String> actualCharacteristics = productPageLogic.openProductPageById(route, "7998901")
                .getCharacteristics();
        productPageLogic.compareActualAndExpectedCharacteristic(expectedCharacteristics, actualCharacteristics);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
