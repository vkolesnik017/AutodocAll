package ATD.Catalog.QC_613_DisplayAllUndercategoriesTecdocCatalog;


import ATD.Categories_page_Logic;
import ATD.DataBase;
import ATD.SetUp;
import AWS.CatalogCategories_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.close;

public class QC_614_DisplayUndercategoriesInCatalogRouteWithCar {

    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();
    private ArrayList<String> notActiveCategories;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "maker_car_list3");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Display Undercategories In Catalog Route With Car")
    public void testDisplayUndercategoriesInCatalogRouteWithCar(String route) throws Exception {
        openPage(route);
        categoriesPageLogic.check200ResponseTecdoc();
        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "maker_car_list8"));
        categoriesPageLogic.check200ResponseTecdoc();
        notActiveCategories = new CatalogCategories_aws().getNotActiveUndercategoriesFromAWS();
        categoriesPageLogic.checkUrlUndercategoriesId404(notActiveCategories);
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
