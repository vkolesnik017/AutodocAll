package PKW;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Tyres_type_list_page {

    SelenideElement tiresSizeTable() {return $x("//div[@class='tires_sizes_table']");}
}
