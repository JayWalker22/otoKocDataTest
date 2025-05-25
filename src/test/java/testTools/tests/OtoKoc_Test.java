package testTools.tests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import testTools.pages.OtoKoc_Page;
import testTools.utilities.ConfigReader;
import testTools.utilities.ExcelUtils;
import testTools.utilities.ReusableMethods;

import java.io.*;
import java.time.Duration;

import static testTools.utilities.Driver.closeDriver;
import static testTools.utilities.Driver.getDriver;

public class OtoKoc_Test {
    OtoKoc_Page page = new OtoKoc_Page();
    ExcelUtils excelUtils;
    String path = "src/test/java/testTools/resources/otoKoc.xlsx";

    @BeforeMethod
    public void setup() {
        getDriver().get(ConfigReader.getProperty("otoKoc"));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        page = new OtoKoc_Page();
        ReusableMethods.clickByJavaScript(page.jsAlert);
        ReusableMethods.clickByJavaScript(page.alert);

    }
    @Test
    public void otoKocDataTest() throws IOException {
        page.detayliArama.click();
        ReusableMethods.wait(2);

        ReusableMethods.scrollToElementInContainer(page.scrollBar, page.fiatCheckbox);
        page.fiatCheckbox.click();

        ReusableMethods.scrollToElementInContainer(page.scrollBar, page.egeaCheckbox);
        page.egeaCheckbox.click();
        ReusableMethods.wait(2);

        ReusableMethods.scrollDown();
        ReusableMethods.wait(2);
        page.performansButton.click();
        page.dizelButton.click();
        page.manuelButton.click();
        page.araButton.click();
        ReusableMethods.wait(3);

        excelUtils = new ExcelUtils(path, "OtoKoc");
        excelUtils.setCellData("TITLE", 0, 0);
        excelUtils.setCellData("PRICE", 0, 1);
        excelUtils.setCellData("KM", 0, 2);

        // Her ilanı Excel’e yaz
        for (int i = 0; i < page.titles.size(); i++) {
            excelUtils.setCellData(page.titles.get(i).getText(), i + 1, 0);
            excelUtils.setCellData(page.prices.get(i).getText(), i + 1, 1);
            excelUtils.setCellData(page.km.get(i).getText(), i + 1, 2);
        }

        // Kaydet ve kapa
        excelUtils.closeWorkbook();
    }
    @AfterMethod
    public void tearDown() {
        closeDriver();
    }
}

