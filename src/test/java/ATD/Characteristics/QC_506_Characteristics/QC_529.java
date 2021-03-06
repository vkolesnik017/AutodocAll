package ATD.Characteristics.QC_506_Characteristics;

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

public class QC_529 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] test1() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product40,product93,product94");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Kolesnik")
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
