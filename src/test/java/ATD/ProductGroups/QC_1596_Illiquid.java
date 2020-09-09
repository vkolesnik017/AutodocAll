package ATD.ProductGroups;

import ATD.Product_page_Logic;
import Common.SetUp;
import AWS.ProductSearch_aws;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Flaky;
import io.qameta.allure.Owner;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static ATD.CommonMethods.*;
import static Common.SetUp.setUpBrowser;
import static com.codeborne.selenide.Selenide.closeWebDriver;


public class QC_1596_Illiquid {

    private ProductSearch_aws product_page_aws = new ProductSearch_aws();
    private String idAndBrand;

    @BeforeClass
    void setUp() {
        setUpBrowser(false, "chrome", "77.0");
        idAndBrand = product_page_aws.openProductSearchPageAndLogin().chooseIlliquidProductAndGetId();
        close();
    }

    @DataProvider(name = "route", parallel = true)
    Object[] dataProvider() {
        return new SetUp().setUpShop("prod", "DE");
    }


    @Owner(value = "Chelombitko")
    @Test(dataProvider = "route")
    @Description(value = "Test check making order with illiquid product")
    @Flaky
    public void checkingOrderWithIlliquid(String route) {
        String[] url = idAndBrand.split("#");
        String shop = getShopFromRoute(route);
        openPage(route + "/" + url[1] + "/" + url[0]);
        String testMail = "QC_1596_autotest@mailinator.com";
        new Product_page_Logic().addProductToCart().closePopupOtherCategoryIfYes()
                .cartClick()
                .nextButtonClick()
                .signIn(testMail, password)
                .fillAllFields(shop).nextBtnClick()
                .chooseVorkasse().nextBtnClick()
                .nextBtnClick()
                .closePopupAfterOrder().successTextInHeader().shouldHave(Condition.text("Vielen Dank"));
    }

    @AfterMethod
    private void close() {
        closeWebDriver();
    }
}
