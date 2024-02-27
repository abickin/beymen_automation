package tests.beymen;

public interface Locators {
    //String DYNAMIC_XPATH_SIZE_SELECTOR = "//*[@id=\"sizes\"]/div/span[" + ("%s%" + 1) + "]";

    String FILE_PATH = "src/test/java/tests/files/beymenData.xlsx";
    String SEARCHED_PRODUCT_ELEMENT_LIST = "//div[@id='productList']/div/*";
    String REJECT_ALL_COOKIES = "//button[@id='onetrust-reject-all-handler']";
    String CLOSE_GENDER_BOX_ELEMENT = "//button[@class='o-modal__closeButton bwi-close']";
    String CUSTOMER_PANEL_ELEMENT = "//div[@class='o-header__userInfo']";
    String SEARCHBAR_ELEMENT = "//input[@placeholder='Ürün, Marka Arayın']";
    String SEARCHBAR_TEXT_ELEMENT = "//input[@id='o-searchSuggestion__input' and @placeholder='Ürün, Marka Arayın']";
    String SEARCHBAR_VALUE_DELETE = "//button[@class='o-header__search--close -hasButton']";
    String SIZE_ELEMENT_LIST = "//div[@class='m-variation']/span";
    String ADD_TO_BASKET = "//button[@id='addBasket']";
    String MY_BASKET = "//a[@title='Sepetim']";
    String ACTUAL_BASKET_TOTAL_PRICE = "//li[@class='m-orderSummary__item -grandTotal']//span[2]";
    String ACTUAL_PRODUCT_NAME_IN_CART = "//span[@class='m-basket__productInfoName']";
    String PRODUCT_CLICK_BUTTON_BYCLASS = "m-productImageList";
    String SELECTED_PRODUCT_BODY_DETAIL = "//div[@class='col-md-6 col-lg-4 -detailWrapper']";
    String PRODUCT_NAME_ELEMENT_BYCLASS = "o-productDetail__description";
    String NO_DISCOUNT_PRICE="priceNew";
    String DISCOUNT_PRICE = "//div[@class='m-price__lastPrice']";
    String DISCOUNT_INCART_PRICE = "//span[@class='m-price__campaignPrice']";
    String DROP_DOWN_MENU = "quantitySelect0-key-0";
    String EXPECTED_DDM_OPTION_VALUE = "//*[@id=\"quantitySelect0-key-0\"]/option[2]";



}
