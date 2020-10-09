package ATD.SEO.QC_2549_InformationBlockOfCategoryNameRoute;

import ATD.Category_name_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2552_ProductsArticleBlock {
    private Category_name_page_Logic categoryNamePage = new Category_name_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test checks products article block")
    public void testChecksProductsArticleBlock(String route) {
        openPage(route);
        categoryNamePage.presenceOfHeadlineProductArtBlock().checkFormatOfLinksInProductArtBlock();
        List<String> valuesOfProductArtBlock = categoryNamePage.getValuesFromProductArtNumBlock();
        String artNumOfProduct = categoryNamePage.getLinkFromProductArtBlock(0);
        categoryNamePage.checkOfDuplicateInBrandsBlock(valuesOfProductArtBlock)
                .checkNotEmptyValuesOfProductArtBlock().clickOnProductArtBlockLink(0).compareArtNumOfProduct(artNumOfProduct);
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
