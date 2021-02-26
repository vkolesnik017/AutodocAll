package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Accessories_listing_criteria_page {

    SelenideElement headlineOfGenericsBlock() {return $x("//div[@data-name='category']/div[1]");}
}
