package ATD.Characteristics.QC_3432_PresencePlatePricePerPieceOrSet;

import ATD.Category_name_page_Logic;
import ATD.LKW_Category_car_list_page_Logic;
import ATD.LKW_Category_page_Logic;
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

public class QC_3441 {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }


    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list76");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per set) element in the TOP products block")
    public void testCheckDisplayPricePerSetElementInTopProductsBlock(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .checkPresenceElementPricePerSetOrPiece(new Category_name_page_Logic().infoPriceForSetFromTopProduct());
    }


    @DataProvider(name = "routeLkw", parallel = true)
    Object[] dataProviderLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list54");
    }

    @Test(dataProvider = "routeLkw")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per set) element in the TOP products block")
    public void testLkwCheckDisplayPricePerSetElementInTopProductsBlock(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .checkPresenceElementPricePerSetOrPiece(new LKW_Category_page_Logic().infoPriceForSetFromTopProduct());
    }


    @DataProvider(name = "routeMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category24");
    }

    @Test(dataProvider = "routeMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Checking the display of the (Price per set) element in the TOP products block")
    public void testMotoCheckDisplayPricePerSetElementInTopProductsBlock(String route) {
        openPage(route);
        new LKW_Category_car_list_page_Logic()
                .checkPresenceElementPricePerSetOrPiece(new LKW_Category_page_Logic().infoPriceForSetFromTopProduct());
    }


    @AfterMethod
    public void close() {
        closeWebDriver();
    }


}
