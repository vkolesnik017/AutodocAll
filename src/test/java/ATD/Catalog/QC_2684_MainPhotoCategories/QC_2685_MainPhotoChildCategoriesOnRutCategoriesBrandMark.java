package ATD.Catalog.QC_2684_MainPhotoCategories;

import ATD.Category_name_brand_page_Logic;
import ATD.Category_name_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.sql.SQLException;
import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class QC_2685_MainPhotoChildCategoriesOnRutCategoriesBrandMark {

    private Category_name_page_Logic categoryNamePageLogic = new Category_name_page_Logic();
    private Category_name_brand_page_Logic categoryNameBrandPageLogic = new Category_name_brand_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main",  "category_name12,category_maker7");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking main photo child categories on rut categories brand mark ")
    public void testMainPhotoChildCategoriesOnRutCategoriesBrandMark(String route) throws IOException {
        openPage(route);
        String idCategoryAndBrandFromCategoryPage = categoryNamePageLogic.concatIdCategoryAndBrand();
        String urlBrandFromCategoryPage = categoryNamePageLogic. getUrlBrandsFromBrandsBlock();
        categoryNamePageLogic.clickOnBrandFromBrandsBlock();
        String urlFromBrandPage = url();
        String idCategoryAndBrandMainImageFromBrandPage = categoryNameBrandPageLogic.getIdCategoryAndBrandMainImagePage();
        String sizeMainImageBrandPage = categoryNameBrandPageLogic.getSizeMainImagePage();
        Assert.assertEquals(idCategoryAndBrandFromCategoryPage, idCategoryAndBrandMainImageFromBrandPage);
        Assert.assertEquals(urlBrandFromCategoryPage, urlFromBrandPage);
        Assert.assertEquals(sizeMainImageBrandPage, "200x200");
        String urlMainImageFromBrandPage = categoryNameBrandPageLogic.getUrlFromMainImagePage();
        categoryNameBrandPageLogic.checkUrlForServerResponses200(urlMainImageFromBrandPage);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
