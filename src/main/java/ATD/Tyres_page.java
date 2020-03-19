package ATD;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Tyres_page {

  public SelenideElement imagesProductsTires() {
    return $(byCssSelector(".prod_img"));
  }

  SelenideElement seasonDropdown() { return $("select#form_Season"); }

  SelenideElement widthDropdown() { return $("select#form_Width"); }

  SelenideElement heightDropdown() { return $("select#form_CrossSections"); }

  SelenideElement diameterDropdown() { return $("#form_Size"); }

  SelenideElement typeDropdown() { return $("#form_R_ZR"); }

  SelenideElement submitTyresSelectorButton() { return $x("//*[@id='tyres_search']"); }

  SelenideElement tyresSelectorBlock() { return $(".reifen_select"); }

  SelenideElement tyresSelectorBlockPKW() { return $(".reifen_select_block_pkw"); }

  SelenideElement tyresSelectorBlockSUV() { return $(".reifen_select_block_suv"); }

  SelenideElement tyresSelectorBlockMOTO() { return $(".reifen_select_block_moto"); }

  SelenideElement tyresSelectorBlockLLKW() { return $(".reifen_select_block_llkw"); }

  SelenideElement tabSUVtype() { return $(".select_link_suv"); }

  SelenideElement tabLLKWtype() { return $(".select_link_llkw"); }

  SelenideElement tabMOTOtype() { return $(".select_link_moto"); }

  SelenideElement tyresValidationPopup() { return  $("#popup_car_tires_not_found > div.txt"); }

  SelenideElement closeTyresValidationPopupButton() { return  $("#popup_car_tires_not_found > a"); }

  SelenideElement brandDropdown() { return $x("(//*[@class='multiple-select__title'])[1]"); }

  SelenideElement brandApolloInDropdown() { return $x("//*[@id='Brand_apollo']/../label"); }

  SelenideElement speedIndexDropdown() { return $x("(//*[@class='multiple-select__title'])[2]"); }

  SelenideElement speedIndexHinDropdown() { return $x("//*[@id='SpeedIndex_h']/../label"); }

}
