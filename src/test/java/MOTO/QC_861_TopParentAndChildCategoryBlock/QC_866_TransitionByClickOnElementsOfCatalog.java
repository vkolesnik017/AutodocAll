package MOTO.QC_861_TopParentAndChildCategoryBlock;

import Common.DataBase;
import ATD.Moto_Catalog_page_Logic;
import ATD.Moto_main_page_Logic;
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
import static com.codeborne.selenide.Selenide.*;

public class QC_866_TransitionByClickOnElementsOfCatalog {

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_main");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks parent category block components")
    public void testChecksParentCategoryBlockComponents(String route) throws SQLException {
        openPage(route);

        new Moto_main_page_Logic()
                .checkTransitionByClickOnImageParentCategory("moto_parent_category")
                .checkTransitionByClickOnChildCategory("moto_category");
        open(new DataBase().getFullRouteByRouteAndSubroute("subprod", "DE", "moto_main", "moto_catalog"));
        new Moto_Catalog_page_Logic()
                .clickOnMainLogoInHeader()
                .checkSuccessfullyMotoPageLoading()
                .checkTransitionByClickOnChildCategoryWithMoto("moto_category_car_list3");

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
