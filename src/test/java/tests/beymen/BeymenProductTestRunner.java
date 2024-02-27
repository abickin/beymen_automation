package tests.beymen;

import org.junit.Test;
import tests.TestBaseClass;
import tests.beymen.model.BeymenProductDetail;
import utilities.CommonUtils;

import java.io.IOException;
import java.util.List;

import static utilities.FileUtils.readSearchProductNameFromExcell;

public class BeymenProductTestRunner extends TestBaseClass {

    private static String BEYMEN_URL = "https://www.beymen.com/";

    @Test
    public void BeymenSeleniumTest() throws IOException, InterruptedException {
        BeymenTestsDefinitions beymenTestsDefinitions = new BeymenTestsDefinitions();

        beymenTestsDefinitions.goToUrl(driver, BEYMEN_URL);
        beymenTestsDefinitions.closePopUps(driver);
        beymenTestsDefinitions.mainPageVerification(driver);
        List<String> willBeSearchWordList = readSearchProductNameFromExcell();
        CommonUtils.wait(1);
        beymenTestsDefinitions.searchProduct(driver, willBeSearchWordList);
        CommonUtils.wait(1);
        beymenTestsDefinitions.selectProductRandomly(driver);
        CommonUtils.wait(1);
        int selectedSizeNumber = beymenTestsDefinitions.selectVariantRandomly(driver);
        CommonUtils.wait(1);

        while (selectedSizeNumber == -1) {
            beymenTestsDefinitions.searchProduct(driver, willBeSearchWordList);
            CommonUtils.wait(1);
            beymenTestsDefinitions.selectProductRandomly(driver);
            CommonUtils.wait(1);
            selectedSizeNumber = beymenTestsDefinitions.selectVariantRandomly(driver);
            CommonUtils.wait(2);
        }
        //TODO:Ürün detay bilgileri alınırken kontrol mekznizması kurulacak ve dönen değer addToChart'a parametre geçilecek
        //BeymenProductDetail beymenProductDetail = beymenTestsDefinitions.getProductDetail();
        beymenTestsDefinitions.productAddToChart(driver, selectedSizeNumber);
        CommonUtils.wait(1);
        beymenTestsDefinitions.increaseProductQuantityVerification();
    }
}
