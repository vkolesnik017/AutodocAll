package ATD;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class Supplier_brand_line_page {

    SelenideElement anotherBrandsBlock() {return $(".summary-table__table");}

    ElementsCollection anotherBrandLinks() {return $$(".summary-table__table a");}

    SelenideElement mainHeadline() {return $(".catalog-title__block h1");}
}
