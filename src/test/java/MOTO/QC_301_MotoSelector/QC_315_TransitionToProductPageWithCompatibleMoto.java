package MOTO.QC_301_MotoSelector;

import ATD.Moto_Category_car_list_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.*;

public class QC_315_TransitionToProductPageWithCompatibleMoto {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list2");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks transition to product page with compatible motorcycle")
    public void testChecksTransitionToProductPageWithCompatibleMoto(String route) {
        open(route);

        new Moto_Category_car_list_page_Logic()
                .clickOnArtNumOfProduct("HF163")
                .presenceOfMotoCompatibilityMessage("BMW MOTORCYCLES K");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
