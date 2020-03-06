package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_Category_page {
    protected SelenideElement imageOfChildCategory() {
        return $x("//div[@class='autoteile-top-content__image']");
    }
}
