package ATD.StaticPage;

import ATD.Main_page_Logic;
import ATD.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_243_StaticPage_versand {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }

    @Test(dataProvider = "route")
    @Owner(value = "Chelombitko")
    @Flaky
    @Description(value = "Test checks elements on versand page")
    public void checkVersandPageElements(String route) {
        openPage(route);
        new Main_page_Logic().clickVersand()
                .checkBlockTop()
                .checkPricesBlock()
                .checkCountryPricesBlock()
                .checkDeliveryTimeBlock()
                .checkRecommendationBlock()
                .checkSafeOrderBlock()
                .checkTyresDeliveryBlock()
                .checkOversizeShippingProductBlock();
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}


