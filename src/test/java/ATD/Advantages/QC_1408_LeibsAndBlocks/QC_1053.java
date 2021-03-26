package ATD.Advantages.QC_1408_LeibsAndBlocks;

import ATD.CartAllData_page;
import ATD.Cart_page_Logic;
import ATD.Listing_page_Logic;
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
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1053 {

    private final Listing_page_Logic listing_page = new Listing_page_Logic();
    private final Cart_page_Logic cart_page_logic = new Cart_page_Logic();
    private final CartAllData_page cartAllDataPage = new CartAllData_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0", false);
    }

    @DataProvider(name = "product")
    Object[] dataProviderProduct() throws SQLException {
        return new SetUp("ATD").setUpShopWithSubroutes("prod", "DE", "main", "category_car_list18");
    }

    @Test(dataProvider = "product")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Compare the price of pfand on listing and in basket")
    public void testPfandPriceMatchesOnListingAndBasket(String route){
        String testMail = "QC_1053_autotestATD@mailinator.com";
        openPage(route);
        listing_page.goToFirstPfandProduct()
                .addProductToCart()
                .closePopupOtherCategoryIfYes()
                .cartClick();
        String pfandPrice = cart_page_logic.pfandPriceInProductBlock().getText();
        cart_page_logic.pfandPriceInTotalPriceBlock().shouldHave(exactText(pfandPrice));
        cart_page_logic.nextButtonClick()
                .signIn(testMail, password)
                .nextBtnClick()
                .chooseVorkasse()
                .nextBtnClick()
                .btnOpenUpperBlockWithSummary().click();
        cartAllDataPage.pfandPriceInHead().shouldHave(exactText(pfandPrice));
        cartAllDataPage.pfandPriceInProductBlock().shouldHave(exactText(pfandPrice));
        cartAllDataPage.pfandPriceInTotalPriceBlock().shouldHave(exactText(pfandPrice));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
