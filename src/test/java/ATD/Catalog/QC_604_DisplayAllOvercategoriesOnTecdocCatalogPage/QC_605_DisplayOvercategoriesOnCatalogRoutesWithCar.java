package ATD.Catalog.QC_604_DisplayAllOvercategoriesOnTecdocCatalogPage;


import ATD.Categories_page_Logic;
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

public class QC_605_DisplayOvercategoriesOnCatalogRoutesWithCar {

    private ArrayList<String> parentCategoriesTecdocCatalog;
    private ArrayList<String> parentCategoriesAWS;

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
    @Description(value = "Test Display Overcategories On Catalog Routes With Car")
    public void testDisplayOvercategoriesOnCatalogRoutesWithCar(String route) throws Exception {
        openPage(route);
        parentCategoriesTecdocCatalog = new Categories_page_Logic().getAllParentCategoriesFromTecdocCatalog();
        parentCategoriesAWS = new CatalogCategories_aws("prod"). getAllParentCategoriesNameFromAWS();
        new Categories_page_Logic().compareCategoriesFromCatalogAndAWS(parentCategoriesTecdocCatalog, parentCategoriesAWS);
    }

    @AfterMethod
    public void tearDown() {
        close();
    }
}
