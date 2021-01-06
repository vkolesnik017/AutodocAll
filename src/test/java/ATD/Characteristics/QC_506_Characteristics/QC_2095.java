package ATD.Characteristics.QC_506_Characteristics;

import ATD.Tyre_form_page_Logic;
import ATD.Tyre_item_page_Logic;
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
import static ATD.CommonMethods.password;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class QC_2095 {
    private Tyre_form_page_Logic tyreFormPage = new Tyre_form_page_Logic();
    private Tyre_item_page_Logic tyreItemPage = new Tyre_item_page_Logic();
    String email = "qc_2095_autotest@mailinator.com";

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
        tyreFormPage.goToProductpage(0).compareMpnNumOfProduct(mpnOfProduct).addProductToBasket();
        String mpnNumOfProduct = tyreItemPage.getMpnNumOfProduct();
        tyreItemPage.compareMpnNumOfProductInBasketPopUp(mpnNumOfProduct).goToBasket().nextButtonClick()
                .signIn(email, password).fillAllFields("DE").nextBtnClick().chooseVorkasse().nextBtnClick().compareArtNumOfProduct(mpnNumOfProduct);
    }


    @DataProvider(name = "routesSecond", parallel = true)
    Object[] dataProviderSecond() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres_season_size,tyres_brand_size3,tyres_brand_dimension7,tyres_dimension14,tyres_season_dimension10");
    }

    @Test(dataProvider = "routesSecond")
    @Flaky
    @Owner(value = "Kolesnik")
    @Description(value = "Test check of identity of MPN product number  and MPN in AWS")
    public void testCheckOfIdentityOfMpnProductAndMpnInAwsSecond(String route) {
        openPage(route);
        String mpnOfProduct = tyreFormPage.presenceOfListingBlock().getMpnNumberOfProduct(0);
        tyreFormPage.goToProductpage(0).compareMpnNumOfProduct(mpnOfProduct).addProductToBasket();
        String mpnNumOfProduct = tyreItemPage.getMpnNumOfProduct();
        tyreItemPage.compareMpnNumOfProductInBasketPopUp(mpnNumOfProduct).goToBasket().nextButtonClick()
                .signIn(email, password).fillAllFields("DE").nextBtnClick().chooseVorkasse().nextBtnClick().compareArtNumOfProduct(mpnNumOfProduct);
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
