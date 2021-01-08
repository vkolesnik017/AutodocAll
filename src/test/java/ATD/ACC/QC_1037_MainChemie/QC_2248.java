package ATD.ACC.QC_1037_MainChemie;

import ATD.Index_chemicals_page_Logic;
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

public class QC_2248 {

    private ArrayList<String> categoriesAndSeparateCategoriesName, logicalUnionName,  awsCategoriesAndSeparateCategoriesName, awsLogicalUnionName;
    private Index_chemicals_page_Logic indexChemicalsPageLogic = new Index_chemicals_page_Logic();
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();

    public QC_2248() throws SQLException {
    }


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_chemicals");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks comparison all categories and Logical Union between main chemicals page and AWS")
    public void testCheckCorrectDisplayQuantityCategoriesAndLogicalUnion(String route) {
        openPage(route);
        categoriesAndSeparateCategoriesName = indexChemicalsPageLogic.getIdCategoriesAndSeparateCategoriesThenWriteToList();
        logicalUnionName = indexChemicalsPageLogic.getIdLogicalUnionAndWriteToList();
        catalogCategoriesAws.openAwsLoginInAndTransitionCustomCatalog()
                .addFilterParentId("30000")
                .clickBtnSearch();
        awsCategoriesAndSeparateCategoriesName = catalogCategoriesAws.getAllIdActiveCategories();
        awsLogicalUnionName = catalogCategoriesAws.getAllIdGroupFromAWS();
        Assert.assertEquals(categoriesAndSeparateCategoriesName, awsCategoriesAndSeparateCategoriesName);
        Assert.assertEquals(logicalUnionName, awsLogicalUnionName);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
