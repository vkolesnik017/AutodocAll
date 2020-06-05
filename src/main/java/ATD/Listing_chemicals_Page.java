package ATD;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;

public class Listing_chemicals_Page {
    SelenideElement titleNameCategory () {
        return $x("//h2[@class='title_count_search']");
    }
}
