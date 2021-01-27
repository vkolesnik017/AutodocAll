package ATD;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class Supplier_brand_line_page_Logic extends Supplier_brand_line_page {

    @Step("presence Another Brands Block.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic presenceAnotherBrandsBlock() {
        anotherBrandsBlock().shouldBe(visible);
        anotherBrandLinks().shouldHave(sizeGreaterThan(0));
        return this;
    }

    @Step("transition To All Another Brands.  Supplier_brand_line_page")
    public Supplier_brand_line_page_Logic transitionToAllAnotherBrands(String brand) {
        for (int i = 0; i < anotherBrandLinks().size(); i++) {
            String currentBrand = anotherBrandLinks().get(i).getText().replaceAll(brand + " ", "");
            String currentMainHeadline = mainHeadline().getText();
            anotherBrandLinks().get(i).click();
            mainHeadline().shouldNotHave(exactText(currentMainHeadline));
            List<String> currentLinks = anotherBrandLinks().stream().map(n -> n.getText().replaceAll(brand + " ", "")).collect(Collectors.toList());
            Assert.assertFalse(currentLinks.contains(currentBrand.toUpperCase()));
            currentLinks.clear();
        }
        return this;
    }
}
