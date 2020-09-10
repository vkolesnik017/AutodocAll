package ATD.Catalog.QC_604_DisplayAllOvercategoriesOnTecdocCatalogPage;


import ATD.Categories_page_Logic;
import Common.DataBase;
import Common.SetUp;
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

import static ATD.CommonMethods.comparingParentCategoriesWithAws;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_609_DisplayOvercategoriesFAQandCatalogRouteWithoutCar {

    private Categories_page_Logic categoriesPageLogic = new Categories_page_Logic();

    private ArrayList<String> parentCategoriesTecdocCatalogWithoutCar;
    private ArrayList<String> parentCategoriesCatalogFAQwithoutCar;
    private ArrayList<String> parentCategoriesAWS;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "categories");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test Display Overcategories FAQ and Catalog Route Without Car")
    public void testDisplayOvercategoriesFAQandCatalogRouteWithoutCar(String route) throws Exception {
        openPage(route);
        parentCategoriesTecdocCatalogWithoutCar = categoriesPageLogic.getAllParentCategoriesFromTecdocCatalog();

        openPage(new DataBase().getFullRouteByRouteAndSubroute("prod", "DE", "main", "faqHash"));
        parentCategoriesCatalogFAQwithoutCar = categoriesPageLogic.getAllParentCategoriesFromTecdocCatalog();

        parentCategoriesAWS = new CatalogCategories_aws("prod").getAllParentCategoriesNameFromAWS();
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesTecdocCatalogWithoutCar);
        comparingParentCategoriesWithAws(parentCategoriesAWS, parentCategoriesCatalogFAQwithoutCar);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
