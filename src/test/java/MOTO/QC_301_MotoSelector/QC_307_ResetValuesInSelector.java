package MOTO.QC_301_MotoSelector;

import ATD.*;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_307_ResetValuesInSelector {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model2,moto_category_car_list2,moto_category_maker");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelector (String route) throws SQLException {
        openPage(route);

        new Moto_Category_car_list_page_Logic()
                .resetOfMotoSelector()
                .checkCurrentUrl("moto_category");
    }

    @DataProvider(name = "routesCategory", parallel = true)
    Object[] dataProviderCategory() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category");
    }

    @Test(dataProvider = "routesCategory")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks reset values in motorcycle selector ")
    public void testChecksResetValuesInSelectorCategory(String route) throws SQLException {
        openPage(route);

        new Moto_Category_page_Logic()
                .selectBrandOfMoto("4081").resetOfMotoSelector()
                .checkCurrentUrl("moto_category")
                .presenceOfEmptyValuesInSelector();

    }

    @AfterMethod
    private void tearDown() {
        close();
    }

}
