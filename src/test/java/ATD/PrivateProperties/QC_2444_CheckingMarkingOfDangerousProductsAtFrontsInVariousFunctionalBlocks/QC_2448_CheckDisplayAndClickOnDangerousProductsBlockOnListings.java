package ATD.PrivateProperties.QC_2444_CheckingMarkingOfDangerousProductsAtFrontsInVariousFunctionalBlocks;

import ATD.LKW_Category_car_list_page_Logic;
import ATD.LKW_Search_page_Logic;
import ATD.Listing_chemicals_Page_Logic;
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

public class QC_2448_CheckDisplayAndClickOnDangerousProductsBlockOnListings {
    private LKW_Category_car_list_page_Logic carListPage = new LKW_Category_car_list_page_Logic();
    private LKW_Search_page_Logic lkwSearchPage = new LKW_Search_page_Logic();
    private Listing_chemicals_Page_Logic chemicalsPage = new Listing_chemicals_Page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list40,lkw_category_car_list41");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlock(String route) {
        openPage(route);
        String idOfDangerousProduct = carListPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = carListPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = carListPage.getAttributeOfWarningIconInPopUp(0);
        carListPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesSearch", parallel = true)
    Object[] dataProviderSearch() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_search13");
    }

    @Test(dataProvider = "routesSearch")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockSearch(String route) {
        openPage(route);
        String idOfDangerousProduct = lkwSearchPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(1);
        String signalWord = lkwSearchPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(1);
        List<String> attributeOfWarningIconInPopUp = lkwSearchPage.getAttributeOfWarningIconInPopUp(1);
        lkwSearchPage.clickOnDangerousLabelAndCompareElements(1, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list14");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockMoto(String route) {
        openPage(route);
        String idOfDangerousProduct = lkwSearchPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = lkwSearchPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = lkwSearchPage.getAttributeOfWarningIconInPopUp(0);
        lkwSearchPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesMotoSecond", parallel = true)
    Object[] dataProviderMotoSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list_model5");
    }

    @Test(dataProvider = "routesMotoSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockMotoSecond(String route) {
        openPage(route);
        String idOfDangerousProduct = lkwSearchPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = chemicalsPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = lkwSearchPage.getAttributeOfWarningIconInPopUp(0);
        lkwSearchPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesCarList", parallel = true)
    Object[] dataProviderCarList() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list45,category_car_list46");
    }

    @Test(dataProvider = "routesCarList")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockCarList(String route) {
        openPage(route);
        String idOfDangerousProduct = carListPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = carListPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = carListPage.getAttributeOfWarningIconInPopUp(0);
        carListPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesSearchMain", parallel = true)
    Object[] dataProviderSearchMain() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "search35");
    }

    @Test(dataProvider = "routesSearchMain")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockSearchMain(String route) {
        openPage(route);
        String idOfDangerousProduct = lkwSearchPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = lkwSearchPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = lkwSearchPage.getAttributeOfWarningIconInPopUp(0);
        lkwSearchPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesChemical", parallel = true)
    Object[] dataProviderChemical() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "listing_chemicals5");
    }

    @Test(dataProvider = "routesChemical")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockChemical(String route) {
        openPage(route);
        String idOfDangerousProduct = carListPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = chemicalsPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = carListPage.getAttributeOfWarningIconInPopUp(0);
        carListPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @DataProvider(name = "routesOEN", parallel = true)
    Object[] dataProviderOEN() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_oen20,category_oen21,category_oen22");
    }

    @Test(dataProvider = "routesOEN")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check displaying and click by Dangerous products block")
    public void testCheckDisplayingAndClickByDangerousProductsBlockOEN(String route) {
        openPage(route);
        String idOfDangerousProduct = carListPage.visibilityOfTecDocListingBlock().getIdOfDangerousProduct(0);
        String signalWord = chemicalsPage.visibilityOfTecDocListingBlock().getSignalWordFromFirstDangerousProductListingView(0);
        List<String> attributeOfWarningIconInPopUp = carListPage.getAttributeOfWarningIconInPopUp(0);
        carListPage.clickOnDangerousLabelAndCompareElements(0, signalWord, attributeOfWarningIconInPopUp);
        new ProductCard_aws(idOfDangerousProduct).openProductCardPageAndLogin().presenceOfDangerousIconBlock().compareElementsOfDangerousProduct(attributeOfWarningIconInPopUp, signalWord);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
