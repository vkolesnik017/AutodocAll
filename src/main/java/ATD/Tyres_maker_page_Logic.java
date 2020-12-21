package ATD;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;

public class Tyres_maker_page_Logic extends Tyres_maker_page {

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsBlockVisibility() {
        new Tyres_feature_page_Logic().checkPopularBrandsBlockVisibility();
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic clickSecondPageInBrandSlider() {
        new Tyres_feature_page_Logic().clickSecondPageInBrandSlider();
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsSliderFirstPosition(int numberOfBrands, int numberOfBrandsInFirstSlide,
                                                                        ElementsCollection brandsInSlider, String attrName) {
        new Tyres_page_Logic().checkPopularBrandsSliderFirstPosition(numberOfBrands, numberOfBrandsInFirstSlide, brandsInSlider, attrName);
        return this;
    }

    @Step(": from Tyres_maker")
    public Tyres_maker_page_Logic checkPopularBrandsSliderSecondPosition(int numberOfBrands, int numberOfBrandsInFirstSlide,
                                                                         ElementsCollection brandsInSlider, String attrName) {
        new Tyres_page_Logic().checkPopularBrandsSliderSecondPosition(numberOfBrands, numberOfBrandsInFirstSlide, brandsInSlider, attrName);
        return this;
    }

    @Step("Check tyres season block presence. Tyres_maker")
    public Tyres_maker_page_Logic checkTyresSeasonBlockPresence() {
        seasonBlock().shouldBe(visible);
        seasonsInSeasonBlock().shouldHaveSize(3);
        return this;
    }
}
