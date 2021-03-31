package ATD;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;

public class Tyres_season_page {

    ElementsCollection topCarTyreSizeLinks() {return $$x("//*[self::div[@class='order_wahlen  '] or self::div[@class='order_wahlen four']]//li//a");}
}
