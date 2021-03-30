package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MotoroilViscosity_page {


    SelenideElement selectorOil() {
        return $(By.id("oil-top-select"));
    }

    SelenideElement viscosityOilNameInSelector() {
        return $x("//*[@name='viscosity']//*[@value='sae-5w-30']");
    }

    SelenideElement viscosityOilFilter() {
        return $x("//*[@class='oil-listing-viscosity-filter__item slick-slide slick-current slick-active']//*[@class='oil-listing-viscosity-filter__item-text active']");
    }

    SelenideElement subNameOnListing() {
        return $x("//*[@class='subname']");
    }

}
