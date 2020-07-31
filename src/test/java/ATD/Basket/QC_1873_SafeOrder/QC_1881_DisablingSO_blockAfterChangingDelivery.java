package ATD.Basket.QC_1873_SafeOrder;

import ATD.CartAddress_page_Logic;
import ATD.DataBase;
import ATD.Product_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;

import java.sql.SQLException;

import static ATD.CommonMethods.*;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_1881_DisablingSO_blockAfterChangingDelivery {

    private String mail = "QC_1881_autotest@mailinator.com";
    private CartAddress_page_Logic cartAddress_page_logic = new CartAddress_page_Logic();

    @BeforeClass
    void setUp() throws SQLException {
        setUpBrowser(false, "chrome", "77.0");
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "BE", "main", "product32"));
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password);
    }

    @DataProvider(name = "shop", parallel = false)
    Object[] dataProviderShop() {
        return new Object[][]{
                {"CH"},
                {"EE"},
                {"LV"},
                {"LT"},
                {"SI"},
                {"NO"}
        };
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks disabling SO block after changing delivery")
    public void testDisablingSO_blockAfterChangingDelivery(String shop) throws SQLException {
        cartAddress_page_logic.fillAllFields(shop)
                .nextBtnClick()
                .choosePayPal()
                .nextBtnClick()
                .checkAbsenceSafeOrderBlock()
                .clickBtnReturnToCartAddressPage();
        waitingElementVisibility(cartAddress_page_logic.shippingForm(), 5);
    }

    @AfterClass
    private void tearDown() {
        close();
    }
}