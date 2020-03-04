package ATD;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

import static ATD.CommonMethods.getPriceFromElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.source;

public class CartAllData_page {

    @Step
    public SelenideElement searchProductByID(String idProduct) {
        return $(byCssSelector("[data-article_id='" + idProduct + "']"));
    }

    public SelenideElement addressInfo() {
        return $(byCssSelector(".info-user-cart__info"));
    }

    public SelenideElement freeDeliveryIcon() {
        return $(byXpath("//*[@class='alldata-bottom']//*[@class='free_icon']"));
    }

    private SelenideElement fieldWithQuantityOfProducts() {
        return $(byCssSelector(".qty>input"));
    }

    private SelenideElement counterPlusBtn() {
        return $(byCssSelector(".plus"));
    }

    private SelenideElement counterMinusBtn() {
        return $(byCssSelector(".minus"));
    }

    private SelenideElement nextBtn() {
        return $(byCssSelector(".order-summary__button"));
    }

    public Payment_handler_page nextBtnClick() {
        nextBtn().click();
        return page(Payment_handler_page.class);
    }

    // locator only for CH
    public SelenideElement vatPriceInHead() {
        return $(byXpath("//*[contains(@class,'top')]/div[4]/span[2]"));
    }

    public SelenideElement vatPriceInTotalOrder() {
        return $(byXpath("//*[@class='alldata-bottom']//div[6]/span[2]"));
    }

    // locators of prices with Currencies

    public SelenideElement uncoverPriceInHead() {
        return $(".order-summary__open");
    }

    public SelenideElement totalOrderPriceInHead() {
        return $(byXpath("//*[contains(@class,'top')]/div[2]/span[2]"));
    }

    public SelenideElement pfandPriceInHead() {
        return $x("(//span[contains(text(),'Pfand')]/following-sibling::span)[1]");
    }

    public SelenideElement productPrice() {
        return $(byCssSelector(".price"));
    }

    public SelenideElement pfandPriceInProductBlock() {
        return $x("(//td[@class='price'])[2]");
    }

    public SelenideElement totalProductPrice() {
        return $(byCssSelector(".total-price"));
    }

    public SelenideElement pfandPriceInTotalPriceBlock() {
        return $x("(//span[contains(text(),'Pfand')]/following-sibling::span)[2]");
    }

    public SelenideElement priceOfAllProducts() {
        return $(byXpath("//*[@class='order-summary ']/div[2]/span[2]"));
    }

    public SelenideElement deliveryPrice() {
        return $(byXpath("//*[@class='order-summary ']/div[3]/span[2]"));
    }

    public SelenideElement totalOrderPrice() {
        return $(byXpath("//*[@class='alldata-bottom']//*[contains(@class,'total')]/span[2]"));
    }

    public SelenideElement priceOfSafeOrder() {
        return $(byCssSelector(".bestelen-block__col>label"));
    }

    // locators of popup of dangerous product
    public SelenideElement popupOfDangerousProduct() {
        return $(byCssSelector(".delivery-limit-popup"));
    }

    public SelenideElement closePopupBtn() {
        return $(byCssSelector(".close_popup.close"));
    }

    public SelenideElement areaOutOfPopup() {
        return $(byId("overlay"));
    }

    public SelenideElement deleteProductBtnInPopup() {
        return $(byCssSelector(".delete_items"));
    }

    public SelenideElement changeAddressBtnInPopup() {
        return $(byCssSelector(".change_address"));
    }

    private SelenideElement popupOfEmptyBasket() {
        return $(By.xpath("//div[@class='cart-popup close-redirect']"));
    }

    private SelenideElement closeBtnPopupOfEmptyBasket() {
        return $(By.xpath("//div[@class='cart-popup close-redirect']//a[@class='color close_popup']"));
    }

    //locators for tyres
    SelenideElement tyresAreNotDeliveredToCountryPopup() { return $(".delivery_limit_tyres"); }

    SelenideElement closeTyresNotDeliveredPopupButton() { return $("close_popup"); }

    @Step("Check tyres are not delivered popup and clicking close popup, after that checking one more popup and after clicking close must redirect us on main page")
    public Main_page checkTyresNotDeliveredPopupAndRedirect() {
        tyresAreNotDeliveredToCountryPopup().shouldBe(visible);
        closePopupBtn().click();
        popupOfEmptyBasket().shouldBe(visible);
        closeBtnPopupOfEmptyBasket().click();
        new Main_page().logoInHeader().shouldBe(visible);
        String pageSource = source();
        if(!pageSource.contains("ROUTE_NAME\":\"main\"")) Assert.fail("Wrong page. Must open Main Page");
        return page(Main_page.class);
    }

    @Step("Check removing tyres on alldata with other products with delivery to other country")
    public CartAllData_page checkRemovingTyresFromAlldataWithOtherProducts(String productId) {
        tyresAreNotDeliveredToCountryPopup().shouldBe(visible);
        closePopupBtn().click();
        searchProductByID(productId).shouldNotBe(visible);
        return this;
    }

    @Step("Checking popup that appear when delivery impossible and clicking close popup,after that checking one more popup and after clicking close must redirect us on main page ")
    public Main_page closePopupDeliveryImpossibleAndCheckEmptyCart() {
        popupOfDangerousProduct().shouldBe(appear);
        closePopupBtn().click();
//        areaOutOfPopup(), deleteProductBtnInPopup();
        popupOfEmptyBasket().shouldBe(appear);
        closeBtnPopupOfEmptyBasket().click();
        new Main_page().logoInHeader().shouldBe(appear);
        String pageSource = source();
        if(!pageSource.contains("ROUTE_NAME\":\"main\"")) Assert.fail("Wrong page. Must open Main Page");
        return page(Main_page.class);
    }


    @Step
    public CartAllData_page makeAndCheckLimitPriceForFreeDelivery(float deliveryLimit) {
        // An increase in the quantity of products for checking the limit of free delivery
        float totalPrice = getPriceFromElement(totalProductPrice());
        while (!freeDeliveryIcon().isDisplayed() && totalPrice < deliveryLimit) {
            String beforeClickPrice = totalProductPrice().text();
            sleep(1000);
            counterPlusBtn().click();
            totalProductPrice().shouldHave(not(text(beforeClickPrice)));
            totalPrice = getPriceFromElement(totalProductPrice());
            if (totalPrice < deliveryLimit) {
                freeDeliveryIcon().shouldBe(not(visible));
            } else if (totalPrice > deliveryLimit) {
                freeDeliveryIcon().shouldBe(visible);
                break;
            }
        }
        return this;
    }

    @Step
    public CartAllData_page counterIncrease(String startValue) {
        new CommonMethods().checkingCounterIncrease(startValue, fieldWithQuantityOfProducts(), counterPlusBtn());
        return this;
    }

    @Step
    public CartAllData_page counterDecrease(String startValue) {
        new CommonMethods().checkingCounterDecrease(startValue, fieldWithQuantityOfProducts(), counterMinusBtn());
        sleep(1000);
        return this;
    }

    @Step("Check delivery price on alldata page")
    public CartAllData_page checkDeliveryPriceAlldata(String deliveryPrice) {
        freeDeliveryIcon().shouldHave(text(deliveryPrice));
        return this;
    }
}
