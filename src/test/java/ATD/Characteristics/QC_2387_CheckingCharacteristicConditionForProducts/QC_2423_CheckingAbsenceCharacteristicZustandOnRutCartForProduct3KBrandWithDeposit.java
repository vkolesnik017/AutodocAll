package ATD.Characteristics.QC_2387_CheckingCharacteristicConditionForProducts;

import ATD.CartAccount_page_Logic;
import ATD.Cart_page_Logic;
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
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2423_CheckingAbsenceCharacteristicZustandOnRutCartForProduct3KBrandWithDeposit {

    String mail = "QC-2423_autoTest@mailinator.com";

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product41");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Checking for the absence of the deposit characteristic on the ruts of the basket for 3k brand goods with a deposit")
    public void checkingDepositCharacteristicInCartFor3kBrandGoodsWithDeposit(String route) {
        openPage(route);
        new Product_page_Logic().checkingPresencePfandBlock().addedProductToBasket()
                .checksPresentProductInCartPopup().clickBtnGoToCartFromCartDropMenu();
        new CartAccount_page_Logic().clickBtnReturnToBasket();
        new Cart_page_Logic().checkingAbsenceOfZustandCharacteristic();

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
