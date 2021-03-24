package ATD.Characteristics.QC_3432_PresencePlatePricePerPieceOrSet;

import ATD.Main_page_Logic;
import ATD.Product_page_Logic;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static ATD.CommonMethods.openPage;
import static Common.CommonMethods.waitWhileRouteBecomeExpected;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;



public class QC_3434 {


    ProductSearch_aws productSearchAws = new ProductSearch_aws();
    Product_page_Logic productPageLogic = new Product_page_Logic();


    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false); }


    @DataProvider(name = "routes", parallel = true)
    Object[] dataProvider() {
        return new SetUp("ATD").setUpShop("prod", "DE");
    }

    @Test(dataProvider = "routes")
    @Flaky
    @Owner(value = "Sergey_QA")
    @Description(value = "Test Displays the phrase (Unit Price) in the grocery store when AWS has one check or more 1")
    public void testChecksPhraseUnitPriceIsDisplayedOnProduct(String route) {
        openPage(route);
        productSearchAws.openProductSearchPageAndLogin()
                .selectFirstSearchFilter("no")
                .selectPieceOrSetEquipment("1")
                .setValueInFormControl("2")
                .clickSearch();
        String artNumAws = productSearchAws.getArticleProduct();
        openPage(route);
        new Main_page_Logic().inputTextInSearchBar(artNumAws)
                .clickTooltipInSearchByExactText("FERODO Bremsscheibe  (DDF1520)");
        waitWhileRouteBecomeExpected("product");
        String artNumProduct = productPageLogic.getArticleNumber();
        Assert.assertEquals(artNumProduct, artNumAws);
        productPageLogic.checkTextProductInfoPrice("(Artikelpreis)");
    }

    @AfterMethod
    public void close() {
        closeWebDriver();
    }
}
