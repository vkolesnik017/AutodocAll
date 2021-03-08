package ATD.Basket.QC_3090_BanningWordPackstationInStreetFieldAt_DE_Shop;

import ATD.CartAddress_page_Logic;
import ATD.CartPayments_page_Logic;
import ATD.Product_page_Logic;
import Common.DataBase;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.CommonMethods.password;
import static Common.CommonMethods.checkingContainsUrl;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3079 {

    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();
    private CartPayments_page_Logic cartPayments_page_logic = new CartPayments_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0", false);
        String mail = "QC_3079_autotest@mailinator.com";
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("prod", "DE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
    }

    @DataProvider(name = "blockingWords")
    Object[] blockingWords() {
        return new Object[][]{
                {"post"}, {"Swiss"}, {"Baris"}, {"Deutsche"}, {"Pack"}, {"Post"}, {"International"},
                {"filiala"}, {"Paket"}, {"Station"}, {"Nr"}, {"express"}
        };
    }

    @Owner("Chelombitko")
    @Test(dataProvider = "blockingWords")
    @Flaky
    @Description(value = "Checking that the word Packstation is not blocked in the Street field on billing with split billing and shipping")
    public void testCheckingWordPackstationIsNotBlockedOnBilling(String blockingWords) {
        cartAddress_page_logic.fillAllFieldsForShipping("autotest", "autotest", blockingWords, "autotest",
                        "DE", "city", "200+002")
                .nextBtnClick();
        checkingContainsUrl("/basket/payments");
        cartPayments_page_logic.clickBtnReturnTheAddressPage();
    }

    @AfterClass
    private void close() {
        closeWebDriver();
    }
}
