package ATD.ACC.QC_2235_DisplayedAllCategoriesAndLogicalUnion;


import ATD.Index_instruments_page_Logic;
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

public class QC_2249 {

    private ArrayList<String> categoriesAndSeparateCategoriesName, logicalUnionName,  awsCategoriesAndSeparateCategoriesName, awsLogicalUnionName;
    private Index_instruments_page_Logic indexInstrumentsPageLogic = new Index_instruments_page_Logic();
    private CatalogCategories_aws catalogCategoriesAws = new CatalogCategories_aws();

    public QC_2249() throws SQLException {
    }


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main","index_instruments");
    }

    @Test(dataProvider = "route")
    @Flaky
    @Owner(value = "Sergey-QA")
    @Description(value = "Test Checks comparison all categories and Logical Union between main instruments page and AWS")
    public void testCheckCorrectDisplayQuantityCategoriesAndLogicalUnion(String route) {
        openPage(route);
        categoriesAndSeparateCategoriesName = indexInstrumentsPageLogic.getIdCategoriesAndSeparateCategoriesThenWriteToList();
        logicalUnionName = indexInstrumentsPageLogic.getIdLogicalUnionAndWriteToList();
        catalogCategoriesAws.openAwsLoginInAndTransitionCustomCatalog()
                .addFilterParentId("36000")
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
