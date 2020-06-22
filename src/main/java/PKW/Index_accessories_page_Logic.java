package PKW;


import io.qameta.allure.Step;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;


public class Index_accessories_page_Logic extends Index_accessories_page {


    @Step("Checking presence title name on main page. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceTitleNamePage() {
        Assert.assertFalse(titleNamePage().text().isEmpty());
        return this;
    }


    @Step("Checking presence block Top brands. Index_accessories_page")
    public Index_accessories_page_Logic checkingPresenceBlockTopBrands() {
        blockTopBrands().shouldBe(visible);
        return this;
    }

    @Step("Get name first brand from URL in block Top brands. Index_accessories_page")
    public String getNameFirstBrandFromURLInBlockTopBrands() {
        String nameBrands = firstBrandInBlockTopBrands().getAttribute("href").replaceAll("[\\s\\S]*\\/", "");
        return nameBrands;
    }

    @Step("Click on first brand in block top brands. Index_accessories_page")
    public Supplier_page_Logic clickOnFirstBrandInBlockTopBrands() {
        firstBrandInBlockTopBrands().click();
        return page(Supplier_page_Logic.class);
    }


    @Step("Checking open popup with details after hover on first product in block top products . Index_accessories_page")
    public Index_accessories_page_Logic checkingPopupWithDetailsInBlockTopProducts() {
        firstProductInBlockTopProducts().scrollTo().hover();
        btnDetailsInPopupBlockTopProducts().shouldBe(visible);
        return this;
    }




}
