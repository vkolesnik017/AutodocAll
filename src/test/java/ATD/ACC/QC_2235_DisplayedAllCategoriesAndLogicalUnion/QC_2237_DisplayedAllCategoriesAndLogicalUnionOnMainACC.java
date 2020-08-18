package ATD.ACC.QC_2235_DisplayedAllCategoriesAndLogicalUnion;

import ATD.Index_accessories_page_Logic;
import ATD.SetUp;
import AWS.CatalogCategories_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import java.util.ArrayList;

import static ATD.CommonMethods.openPage;
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2237_DisplayedAllCategoriesAndLogicalUnionOnMainACC {

    private ArrayList<String> categoriesName, logicalUnionName, awsCategoriesName, awsLogicalUnionName;
    private Index_accessories_page_Logic indexAccessoriesPageLogic = new Index_accessories_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks displayed all categories and Logical Union between main accessories page and AWS")
    public void testCheckCorrectDisplayQuantityCategoriesAndLogicalUnion(String route) throws SQLException {
        CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();
        openPage(route);
        categoriesName = indexAccessoriesPageLogic.getAndWriteAllCategoriesToList();
        logicalUnionName = indexAccessoriesPageLogic.getNameLogicalUnionAndWriteToList();
        catalogCategoriesAws.addFilterParentId("33000")
                .clickBtnSearch();
          awsCategoriesName = catalogCategoriesAws.getAllActiveCategories();
          awsLogicalUnionName = catalogCategoriesAws.getAllChildCategoriesNameFromAWS();
        Assert.assertEquals(categoriesName, awsCategoriesName);
        Assert.assertEquals(logicalUnionName, awsLogicalUnionName);

    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}