package ATD.Banners.QC_2799_CheckTransitionFromBannersAutodocClub;


import ATD.Group_list_page_Logic;
import ATD.LKW_Product_page_Logic;
import ATD.Moto_Product_page_Logic;
import ATD.Product_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.sql.SQLException;
import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;


public class QC_2804_TransitionFromBannersAutodocClub {

    private Product_page_Logic productPageLogic = new Product_page_Logic();
    private Moto_Product_page_Logic motoProductPageLogic = new Moto_Product_page_Logic();
    private LKW_Product_page_Logic lkwProductPageLogic = new LKW_Product_page_Logic();
    private Group_list_page_Logic groupListPageLogic = new Group_list_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "product43");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking transition from banners autodoc club")
    public void testTransitionFromBannersAutodocClub(String route) {
        openPage(route);
        String urlAutodocClub = productPageLogic.getUrlAutodocClubFromBannerAutodocClub();
        productPageLogic.clickBannerAutodocClub()
                .checkPresenceIconAutodocClubInHeader();
        Assert.assertTrue(url().contains(urlAutodocClub));
    }


    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_product5,moto_categories_maker,moto_catalog_model6");
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking transition from banners autodoc club")
    public void testMoto_TransitionFromBannersAutodocClub(String route) {
        openPage(route);
        String urlAutodocClub = motoProductPageLogic.getUrlAutodocClubFromBannerAutodocClub();
        motoProductPageLogic.clickBannerAutodocClub()
                .checkPresenceIconAutodocClubInHeader();
        Assert.assertTrue(url().contains(urlAutodocClub));
    }


    @DataProvider(name = "routesLkw", parallel = true)
    Object[] dataProviderLkw() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_product,lkw_categories_maker3,lkw_maker_car_list2");
    }

    @Test(dataProvider = "routesLkw")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking transition from banners autodoc club")
    public void testLkw_TransitionFromBannersAutodocClub(String route) {
        openPage(route);
        String urlAutodocClub = lkwProductPageLogic.getUrlAutodocClubFromBannerAutodocClub();
        lkwProductPageLogic.clickBannerAutodocClub()
                .checkPresenceIconAutodocClubInHeader();
        Assert.assertTrue(url().contains(urlAutodocClub));
    }


    @DataProvider(name = "routesVW", parallel = true)
    Object[] dataProviderVW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "categories_maker,group_list,model_maker_list,category_group");
    }

    @Test(dataProvider = "routesVW")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test checking transition from banners autodoc club")
    public void testVW_TransitionFromBannersAutodocClub(String route) {
        openPage(route);
        String urlAutodocClub = groupListPageLogic.getUrlAutodocClubFromBannerAutodocClub();
        groupListPageLogic.clickBannerAutodocClub()
                .checkPresenceIconAutodocClubInHeader();
        Assert.assertTrue(url().contains(urlAutodocClub));
        checkingContainsUrl("vw");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
