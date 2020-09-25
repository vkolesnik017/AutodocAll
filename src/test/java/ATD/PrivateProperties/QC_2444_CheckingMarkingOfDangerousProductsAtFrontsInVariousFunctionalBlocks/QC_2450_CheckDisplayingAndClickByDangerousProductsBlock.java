package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.Category_name_page_Logic;
import ATD.LKW_Category_page_Logic;
import ATD.Moto_Category_page_Logic;
import AWS.ProductCard_aws;
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

public class QC_2450_CheckDisplayingAndClickByDangerousProductsBlock {
    private LKW_Category_page_Logic categoryPage = new LKW_Category_page_Logic();
    private Moto_Category_page_Logic motoCategoryPage = new Moto_Category_page_Logic();
    private Category_name_page_Logic categoryNamePage = new Category_name_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category6");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlock(String route) {
        openPage(route);
        String idOfDangerousProduct = categoryPage.presenceOfTopProductsBlock().getIdOfDangerousProduct(0);
        String signalWord = categoryPage.presenceOfTopProductsBlock().getSignalWordFromFirstDangerousProduct(0);
        List<String> attributeOfWarningIconInPopUp = categoryPage.getAttributeOfWarningIconInPopUp(0);
        categoryPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category7");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockMoto(String route) {
        openPage(route);
        String idOfDangerousProduct =  motoCategoryPage.presenceOfTopProductsBlock().getIdOfDangerousProduct(0);
        String signalWord =  motoCategoryPage.presenceOfTopProductsBlock().getSignalWordFromFirstDangerousProduct(0);
        List<String> attributeOfWarningIconInPopUp =  motoCategoryPage.getAttributeOfWarningIconInPopUp(0);
        motoCategoryPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesCategoryName", parallel = true)
    Object[] dataProviderCategoryName() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_name9,category_name_brand8,group_list_body2,group_list_hp2,index_chemicals");
    }

    @Test(dataProvider = "routesCategoryName")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockCategoryName(String route) {
        openPage(route);
        String idOfDangerousProduct =  categoryNamePage.presenceOfTopProductsBlock().getIdOfDangerousProduct(0);
       String signalWord =  categoryNamePage.presenceOfTopProductsBlock().getSignalWordFromFirstDangerousProduct(0);
        List<String> attributeOfWarningIconInPopUp =  categoryNamePage.getAttributeOfWarningIconInPopUp(0);
          categoryNamePage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
      new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);

    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
