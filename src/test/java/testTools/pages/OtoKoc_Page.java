package testTools.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import testTools.utilities.Driver;

import javax.xml.xpath.XPath;
import java.util.List;

import static testTools.utilities.Driver.getDriver;

public class OtoKoc_Page {
    public OtoKoc_Page() {
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy (xpath = "//button[text()='Tümünü Reddet']")
    public WebElement jsAlert;
    @FindBy (id = "web_push_hayir")
    public WebElement alert;
    @FindBy (xpath = "//span[text()='Daha Detaylı Ara']")
    public WebElement detayliArama;
    @FindBy (xpath = "//label[contains(text(),'FIAT')]")
    public WebElement fiatCheckbox;
    @FindBy (css = "ul.ml-1.max-h-64.overflow-y-scroll")
    public WebElement scrollBar;
    @FindBy (xpath = "//div//label[contains(text(), 'EGEA')]")
    public WebElement egeaCheckbox;
    @FindBy (xpath = "//div//b[contains(text(), 'Performans')]")
    public WebElement performansButton;
    @FindBy (xpath = "//div//label[contains(text(), 'Dizel')]")
    public WebElement dizelButton;
    @FindBy (xpath = "(//div//div//input[@type='checkbox'])[48]")
    public WebElement manuelButton;
    @FindBy (xpath = "(//button[contains(text(), 'Ara')])[4]")
    public WebElement araButton;
    @FindBy (xpath = "//span[contains(text(), 'km')]")
    public List<WebElement> km;
    @FindBy (className = "style_advert__LfFH5")
    public List<WebElement> titles;
    @FindBy (xpath = "//div[@class='text-xs undefined']/span")
    public List<WebElement> prices;
}
