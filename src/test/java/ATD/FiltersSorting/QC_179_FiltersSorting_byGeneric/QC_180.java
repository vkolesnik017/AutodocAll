package ATD.FiltersSorting.QC_179_FiltersSorting_byGeneric;


import Common.DataBase;
import ATD.Listing_page_Logic;
import ATD.Main_page_Logic;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_180 {
    private Listing_page_Logic listingPage = new Listing_page_Logic();

    private String genericName;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list4,search,listing_accessories,search23,search40,search41");
    }

    @DataProvider(name = "routesLKW", parallel = true)
    Object[] dataProviderLKW() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "lkw_main", "lkw_category_car_list,lkw_search8,lkw_search9,lkw_search14,lkw_search15");
    }

    @DataProvider(name = "routesMoto", parallel = true)
    Object[] dataProviderMoto() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("subprod", "DE", "moto_main", "moto_category_car_list16,moto_search2,moto_category_car_list_model8");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks generic position in generic block on listing (Tecdoc, ACC, search)")
    public void checkGenericPosition(String route) {
        openPage(route);
        genericName = listingPage.getTextFromGeneric();
        listingPage.checkFirstGenericApplying(genericName)
                    .checkSecondGenericApplying();
    }

    @Test(dataProvider = "routesLKW")
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks generic position in generic block on listing LKW")
    public void checkGenericPositionLKW(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        genericName = listingPage.getTextFromGeneric();
        listingPage.checkFirstGenericApplying(genericName)
                    .checkSecondGenericApplyingLKW();
    }

    @Test(dataProvider = "routesMoto")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Test checks generic position in generic block on listing Moto")
    public void checkGenericPositionMoto(String route) {
        openPage(route);
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        genericName = listingPage.getTextFromGeneric();
        listingPage.checkFirstGenericApplying(genericName)
                .checkSecondGenericApplying();
    }

    @Test
    @Flaky
    @Owner(value = "Romaniuta")
    @Description(value = "Test checks generic position in generic block on listing LKW model")
    public void checkGenericPositionLKWmodel() throws SQLException {
        openPage(new DataBase("ATD").getFullRouteByRouteAndSubroute("subprod", "DE", "lkw_main", "lkw_category_car_list4"));
        new Main_page_Logic().closeCarSelectorTooltipIfVisible();
        listingPage.checkGenericFilterApplyingLKWmodelRoute();
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
