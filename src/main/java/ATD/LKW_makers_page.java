package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LKW_makers_page {
    SelenideElement iconOfTruckInHeadlineOfSelector() {return $x("//span[@class='car-icon']");}

    SelenideElement titleOfTruckInHeadlineOfSelector() {return $x("//div[contains(@class,'block-select-car__head-car--lkw')]/span[2]");}
}
