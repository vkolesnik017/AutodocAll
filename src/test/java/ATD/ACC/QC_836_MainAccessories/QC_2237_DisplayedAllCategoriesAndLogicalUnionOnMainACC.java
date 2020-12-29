package ATD.ACC.QC_836_MainAccessories;

import ATD.Index_accessories_page_Logic;
import Common.SetUp;
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
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2237_DisplayedAllCategoriesAndLogicalUnionOnMainACC {

    private ArrayList<String> categoriesName, logicalUnionName, awsCategoriesName, awsLogicalUnionName;
    private Index_accessories_page_Logic indexAccessoriesPageLogic = new Index_accessories_page_Logic();
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();

    public QC_2237_DisplayedAllCategoriesAndLogicalUnionOnMainACC() throws SQLException {
    }


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_accessories");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks displayed all categories and Logical Union between main accessories page and AWS")
    public void testCheckCorrectDisplayQuantityCategoriesAndLogicalUnion(String route) {
        openPage(route);
        categoriesName = indexAccessoriesPageLogic.getAndWriteAllIdCategoriesToList();
        logicalUnionName = indexAccessoriesPageLogic.getIdLogicalUnionAndWriteToList();
        catalogCategoriesAws.openAwsLoginInAndTransitionCustomCatalog()
                .addFilterParentId("33000")
                .clickBtnSearch();
          awsCategoriesName = catalogCategoriesAws.getAllIdActiveCategories();
          awsLogicalUnionName = catalogCategoriesAws.getAllIdGroupFromAWS();
        Assert.assertEquals(categoriesName, awsCategoriesName);
        Assert.assertEquals(logicalUnionName, awsLogicalUnionName);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
