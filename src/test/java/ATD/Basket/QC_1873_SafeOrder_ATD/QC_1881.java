package ATD.Basket.QC_1873_SafeOrder_ATD;

import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.*;
import java.sql.SQLException;
import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_1881 {

    private String mail = "QC_1881_autotest@mailinator.com";
    private String[] listOfCountry = {"CH","NO"};

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "shop", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopsWithSubroute("prod", "BE,DE,FR", "main", "product32");
    }

    @Test(dataProvider = "shop")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks disabling SO block after changing delivery")
    public void testDisablingSO_blockAfterChangingDelivery(String shop) throws SQLException {
        openPage(shop);
        new Product_page_Logic().addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(mail, password)
                .checkAbsenceSafeOrderBlockAfterChangeDeliveryCountry(listOfCountry);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}