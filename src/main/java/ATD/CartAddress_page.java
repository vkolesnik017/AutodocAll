package ATD;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class CartAddress_page {

    public Main_page logoClick() {
        $(By.xpath("//div[@class='cart-page-head__logo']")).click();
        return page(Main_page.class);
    }

    public SelenideElement nextButton() {
        return $(byCssSelector(".address-continue>a"));
    }

    public CartPayments_page nextBtnClick() {
        nextButton().click();
        return page(CartPayments_page.class);
    }

}
