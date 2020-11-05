package ATD.GrayButton.QC_1014_OutOfStockProducts;

import ATD.Tyre_item_page_Logic;
import AWS.ProductCard_aws;
import AWS.ProductSearch_aws;
import Common.SetUp;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import mailinator.WebMail;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static ATD.CommonMethods.openPage;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static mailinator.WebMail.passwordForMail;

public class QC_2069_PresenceOfFeedBackPopUpByClickOnGreyButton {

    private ProductCard_aws productPageAws = new ProductCard_aws();
    private Tyre_item_page_Logic tyreItemPage = new Tyre_item_page_Logic();
    private String email = "QC_2069_autotest@autodoc.si";
    private WebMail webMailPage = new WebMail();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "route")
    Object[] dataProvider() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "tyres");
    }

    @Owner(value = "Kolesnik")
    @Test(dataProvider = "route")
    @Flaky
    @Description(value = "Test checks presence of feedback pop-up by click on grey button")
    public void testChecksPresenceOfFeedBackPopUpByClickOnGreyButton(String route) {
        webMailPage.openMail(email, passwordForMail);
        webMailPage.deleteAllLetters();
        new ProductSearch_aws().openProductSearchPageAndLogin().selectCategory("100001")
                .selectFirstSearchFilter("no").selectAvailabilityAtSupplierFilter("no").clickOnSearchButton().goToProductCartByClickOnTitle(0);
        String brand = productPageAws.getTitleOfBrandProduct();
        String ean = productPageAws.getEanOfProduct();
        String artNum = productPageAws.getArtNumOfProduct();
        openPage(route + "/" + brand + "-" + ean + "-" + artNum);
        tyreItemPage.presenceOfHorizontalSelector().appearsOfOutOfStockProductPopUp()
                .setValueInEmailFieldOfPopUp(email).clickOnGetMailingLabel().clickOnBtnSubscription();
        webMailPage.openMail(email, passwordForMail);
        Assert.assertEquals(webMailPage.getTotalCountOfLetters(), 1);
        webMailPage.deleteAllLetters();
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
