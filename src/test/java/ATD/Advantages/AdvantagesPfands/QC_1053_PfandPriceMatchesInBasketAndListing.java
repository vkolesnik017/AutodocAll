package ATD.Advantages.AdvantagesPfands;

import ATD.*;
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
import static ATD.SetUp.setUpBrowser;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.close;


public class QC_1053_PfandPriceMatchesInBasketAndListing {

    private Listing_page listing_page = new Listing_page();
    private Cart_page_Logic cart_page_logic = new Cart_page_Logic();
    private CartAllData_page cartAllDataPage = new CartAllData_page();

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
    }

    @DataProvider(name = "product")
    Object[] dataProvider5() throws SQLException {
        return new SetUp().setUpShopWithSubroutes("prod", "DE", "main", "category_car_list18");
    }

    @Test(dataProvider = "product")
    @Flaky
    @Owner(value = "Chelombitko")
    @Description(value = "Compare the price of pfand on listing and in basket")
    public void testPfandPriceMatchesOnListingAndBasket(String route){
        String testMail = "atdautotest_qasys_569_advantagespfands@mailinator.com";
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
                .uncoverPriceInHead().click();
        cartAllDataPage.pfandPriceInHead().shouldHave(exactText(pfandPrice));
        cartAllDataPage.pfandPriceInProductBlock().shouldHave(exactText(pfandPrice));
        cartAllDataPage.pfandPriceInTotalPriceBlock().shouldHave(exactText(pfandPrice));
    }

    @AfterMethod
    private void tearDown() {
        close();
    }
}
