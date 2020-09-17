package ATD.Characteristics.QC_526_RecoveryCharacteristics;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_529_PresenceRecoveryCharacteristicsInBasket {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] test1() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product39,product40");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Evlentiev")
    @Description(value = "Test-3. Checks output characteristic recovery in basket")
    public void testPresenceRecoveryCharacteristicsInBasket(String route) {
        openPage(route);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .getCharacteristicsOfProduct().filter(matchText("Zustand:\nWiederaufbereitet")).shouldHaveSize(1);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
