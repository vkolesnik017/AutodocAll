package ATD.Tyres.QC_2066_CheckProductsOutOfStock;

import ATD.SetUp;
import ATD.Tyre_item_page_Logic;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class QC_2069_PresenceOfFeedBackPopUpByClickOnGreyButton {
    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "main", "tyre_item2,tyre_item3,tyre_item5,tyre_item6,tyre_item7,tyre_item8,tyre_item9,tyre_item10,tyre_item11");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks presence of feedback pop-up by click on grey button ")
    public void testChecksPresenceOfFeedBackPopUpByClickOnGreyButton(String route) {
        open(route);

        new Tyre_item_page_Logic().presenceOfHorizontalSelector()
                .presenceOfFeedBackPopUpByLackOfProduct();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
