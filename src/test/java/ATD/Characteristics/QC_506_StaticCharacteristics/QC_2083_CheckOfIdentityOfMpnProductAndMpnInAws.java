package ATD.Characteristics.QC_506_StaticCharacteristics;

import Common.SetUp;
import ATD.Tyre_form_page_Logic;
import ATD.Tyres_dimension_page_Logic;
import AWS.ProductSearch_aws;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2083_CheckOfIdentityOfMpnProductAndMpnInAws {
    private Tyre_form_page_Logic tyreFormPage = new Tyre_form_page_Logic();
    private Tyres_dimension_page_Logic dimensionPage = new Tyres_dimension_page_Logic();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyre_form5,tyres_season,offroad_tyres_brand,tyres_group_season_brand3,tyres_size10");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check of identity of MPN product number  and MPN in AWS")
    public void testCheckOfIdentityOfMpnProductAndMpnInAws(String route) {
        openPage(route);
        String mpnOfProduct = tyreFormPage.presenceOfListingBlock().getMpnNumberOfProduct(0);
        String brandOfProduct = tyreFormPage.getNameBrandOfProduct();
        List<String> mpnNumberOfProductFromAws = new ProductSearch_aws().openProductSearchPageAndLogin().inputMpnNumberAndBrandNameOfProduct(mpnOfProduct, brandOfProduct).getArtNumberOfProduct();
        Assert.assertTrue(mpnNumberOfProductFromAws.contains(mpnOfProduct));
    }

    @DataProvider(name = "routesDimension", parallel = true)
    Object[] dataProviderDimension() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_dimension9,tyres_season_size,tyres_season_dimension6,tyres_brand_size3,tyres_brand_dimension6");
    }

    @Test(dataProvider = "routesDimension")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check of identity of MPN product number  and MPN in AWS")
    public void testCheckOfIdentityOfMpnProductAndMpnInAwsDimension(String route) {
        openPage(route);
        String mpnOfProduct = dimensionPage.presenceOfListingBlock().getMpnNumberOfProduct(0);
        String brandOfProduct = tyreFormPage.getNameBrandOfProduct();
        List<String> mpnNumberOfProductFromAws = new ProductSearch_aws().openProductSearchPageAndLogin().inputMpnNumberAndBrandNameOfProduct(mpnOfProduct, brandOfProduct).getArtNumberOfProduct();
        Assert.assertTrue(mpnNumberOfProductFromAws.contains(mpnOfProduct));
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
