package ATD.Characteristics.QC_3432_PresencePlatePricePerPieceOrSet;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_3440 {


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list74");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per item) element in the TOP products block")
    public void testCheckDisplayPricePerPieceElementInTopProductsBlock(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceTitleList()
                .checkPresenceLeastOneElementPricePerSetOrPiece("piece");
    }


    @DataProvider(name = "routeLkw", parallel = true)
    Object[] dataProviderLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list52");
    }

    @Test(dataProvider = "routeLkw")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per item) element in the TOP products block")
    public void testLkwCheckDisplayPricePerPieceElementInTopProductsBlock(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic().displaySoft404Form()
                .checkPresenceTopProductsBlock()
                .checkPresenceElementPricePerSetOrPiece("piece");

    }


    @DataProvider(name = "routeMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category23");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per item) element in the TOP products block")
    public void testMotoCheckDisplayPricePerPieceElementInTopProductsBlock(String route) {
        openPage(route);
        new Listing_page_Logic().checkPresenceLeastOneElementPricePerSetOrPiece("piece");
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
