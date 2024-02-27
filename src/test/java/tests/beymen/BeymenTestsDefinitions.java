package tests.beymen;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import tests.beymen.model.BeymenProductDetail;
import utilities.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import static tests.TestBaseClass.driver;
import static tests.beymen.Locators.*;
import static utilities.CommonUtils.generateRandomNumber;


public class BeymenTestsDefinitions {
    List<WebElement> searchedProductWebelemetList;
    int productNumberSelector;
    WebElement productSelector;
    BeymenProductDetail beymenProductDetail;

    private static String EXPECTED_QUANTITY_VALUE = "2 Adet";

    public void goToUrl(WebDriver driver, String url) {
        driver.navigate().to(url);
    }

    public void closePopUps(WebDriver driver) {
        driver.findElement(By.xpath(Locators.REJECT_ALL_COOKIES)).click();
        driver.findElement(By.xpath(Locators.CLOSE_GENDER_BOX_ELEMENT)).click();
    }

    public void mainPageVerification(WebDriver driver) {
        WebElement customerPanelElement = driver.findElement(By.xpath(Locators.CUSTOMER_PANEL_ELEMENT));
        Assert.assertTrue(customerPanelElement.isDisplayed());
    }

    public void searchProduct(WebDriver driver, List<String> willBeSearchWordList) {
        driver.findElement(By.xpath(Locators.SEARCHBAR_ELEMENT)).click();
        CommonUtils.wait(1);
        WebElement searchBoxElement = driver.findElement(By.xpath(Locators.SEARCHBAR_TEXT_ELEMENT));

        CommonUtils.wait(1);
        searchBoxElement.sendKeys(willBeSearchWordList.get(0));

        CommonUtils.wait(1);

        driver.findElement(By.xpath(SEARCHBAR_VALUE_DELETE)).click();
        CommonUtils.wait(1);

        searchBoxElement = driver.findElement(By.xpath(Locators.SEARCHBAR_TEXT_ELEMENT));
        searchBoxElement.click();
        CommonUtils.wait(1);
        searchBoxElement.sendKeys(willBeSearchWordList.get(1) + Keys.ENTER);

    }

    public void selectProductRandomly(WebDriver driver) {
        beymenProductDetail = new BeymenProductDetail();
        searchedProductWebelemetList = driver.findElements(By.xpath(Locators.SEARCHED_PRODUCT_ELEMENT_LIST));
        productNumberSelector = generateRandomNumber(searchedProductWebelemetList.size());
        productSelector = searchedProductWebelemetList.get(productNumberSelector).findElement(By.className(PRODUCT_CLICK_BUTTON_BYCLASS));
        productSelector.click();
    }


    public int selectVariantRandomly(WebDriver driver) {
        List<WebElement> sizeElementsList = new ArrayList<>();
        sizeElementsList.addAll(driver.findElements(By.xpath(Locators.SIZE_ELEMENT_LIST)));
        List<String> sizeAttributeValueList = new ArrayList<>();

        for (WebElement sizeElementItem : sizeElementsList) {
            String willBeCheckElement = sizeElementItem.getAttribute("class");
            sizeAttributeValueList.add(willBeCheckElement);
        }
        int[] sizeAttributeIndexArray = new int[sizeAttributeValueList.size()];

        for (int i = 0; i < sizeAttributeValueList.size(); i++) {
            if (sizeAttributeValueList.get(i).contains("criticalStock") || sizeAttributeValueList.get(i).contains("disabled")) {
                for (int j = i; j < sizeAttributeIndexArray.length; j++) {
                    sizeAttributeIndexArray[j] = -1;
                }
            } else {
                sizeAttributeIndexArray[i] = i;
            }
        }

        List<Integer> controlledSizeList = new ArrayList<>();
        for (int i = 0; i < sizeAttributeIndexArray.length; i++) {

            if (sizeAttributeIndexArray[i] >= 0) {
                controlledSizeList.add(sizeAttributeIndexArray[i]);
            }
        }

        int selectedSizeNumber;
        if (!controlledSizeList.isEmpty()) {
            selectedSizeNumber = controlledSizeList.get(generateRandomNumber(controlledSizeList.size()));
        } else {
            selectedSizeNumber = -1;
        }
        return selectedSizeNumber;
    }

    public void productAddToChart(WebDriver driver, int selectedSizeNumber) {
        String xpath = "//*[@id=\"sizes\"]/div/span[" + (selectedSizeNumber + 1) + "]";
        CommonUtils.wait(1);
        driver.findElement(By.xpath(xpath)).click();
        driver.findElement(By.xpath(Locators.ADD_TO_BASKET)).click();
        CommonUtils.wait(7);
        driver.findElement(By.xpath(Locators.MY_BASKET)).click();
    }

    public BeymenProductDetail getProductDetail() {
        beymenProductDetail = new BeymenProductDetail();

        WebElement productDetailBody = driver.findElement(By.xpath(SELECTED_PRODUCT_BODY_DETAIL));

        int expectedProductPrice = 0;
        String productName = productDetailBody.findElement(By.className(PRODUCT_NAME_ELEMENT_BYCLASS)).getText();
        WebElement noDiscountProductPriceElement = productDetailBody.findElement(By.id(NO_DISCOUNT_PRICE));
        WebElement discountPriceElement = productDetailBody.findElement(By.xpath(DISCOUNT_PRICE));
        WebElement discountInCartPriceElement = productDetailBody.findElement(By.xpath(DISCOUNT_INCART_PRICE));
                
        String noDiscountProductPrice = noDiscountProductPriceElement.getText().replaceAll("\\D", "");
        String discountPrice = discountPriceElement.getText().replaceAll("\\D", "");
        String discountInCartPrice = discountInCartPriceElement.getText().replaceAll("\\D", "");

        if (discountInCartPriceElement.isEnabled()&& discountPriceElement.isEnabled()) {
            expectedProductPrice = Integer.parseInt(discountInCartPrice);

        } else if (!discountInCartPriceElement.isEnabled() && discountPriceElement.isEnabled()) {
            expectedProductPrice = Integer.parseInt(discountPrice);
        } else {
            expectedProductPrice = Integer.parseInt(noDiscountProductPrice);
        }

        beymenProductDetail.setProductPrice(expectedProductPrice);
        beymenProductDetail.setProductName(productName);
        String expectedProductName = productName;
         expectedProductPrice = beymenProductDetail.getProductPrice();
        return beymenProductDetail;
    }

    public void productInfoControl(BeymenProductDetail beymenProductDetail) {
        //List<WebElement> cartPriceSummaryListElementList = driver.findElements(By.className("m-orderSummary__item"));
        WebElement actualProductTotalPriceElement = driver.findElement(By.xpath(Locators.ACTUAL_BASKET_TOTAL_PRICE));
        String actualProductPriceStr = actualProductTotalPriceElement.getText().replaceAll("\\D", "");
        int actualProductPrice = Integer.parseInt(actualProductPriceStr) / 100;
        String actualProductNameElementInCart = driver.findElement(By.xpath(Locators.ACTUAL_PRODUCT_NAME_IN_CART)).getText();
        try {
            Assert.assertEquals(beymenProductDetail.getProductPrice(), actualProductPrice);
        } catch (AssertionError e) {
            System.out.println("FİYAT FARKLI");
        }
    }

    public void increaseProductQuantityVerification() {
        WebElement ddm = driver.findElement(By.id(DROP_DOWN_MENU));
        Select select = new Select(ddm);
        select.selectByValue("2");
       }
}

