package testTools.utilities;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.assertTrue;
import static testTools.utilities.Driver.getDriver;

public class ReusableMethods {
    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        getDriver().quit();
    }
    public static void wait(int seconds){
        try{
            Thread.sleep(seconds*1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
    /**
     * bu metot ile bir alert gorunene kadar kodlar bekletilir
     //* @param saniye yerine beklenicek sure int degeri olarak atanmali
     */
    public static void alertWait(int second){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));
        wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     * alertten gelen texti dogrulamak icin kullanilir
     * @param str expected metindir
     * @param atr actual metindir
     */
    public static void assertTextContainsAssertTrue(String str, String atr){
        assertTrue(str.contains(atr));
    }
    /**
     * alerte text gondermek icin kullanilir
     * @param str alertin icine gonderilecek metindir
     */
    public static void sendKeyToAlert(String str) {
        getDriver().switchTo().alert().sendKeys(str);
    }
    /**
     * alerti kabul edecek metot
     */
    public static void acceptAlert(){
        getDriver().switchTo().alert().accept();
    }
    /**
     * alerti reddedecek metot
     */
    public static void dismissAlert(){
        getDriver().switchTo().alert().dismiss();
    }
    /**
     * bu metot ile açık olan pencerelerden indexi verilene geçiş yapılır
     * @param window geçilmek istenen pencerenin indexi
     */
    public static void switchToWindow(int window){
        List<String> allWindowHandles = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(allWindowHandles.get(window));
    }
    /**
     * tum sayfanin screenshoot alinmasini saglar
     */
    public static void allPageScreenShoot(String name){
        String date = new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu = "TestOutput/screenshot" + date + name + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        try {
            FileUtils.copyFile(ts.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /** Bir web elementin screenshoot alinmasini saglar
     * @param element screenshoot alinacak olan elementin locate verilir
     */
    public  static void webElementScreenShoot(WebElement element){

        String tarih= new SimpleDateFormat("_hh_mm_ss_ddMMyyyy").format(new Date());
        String dosyaYolu= "TestOutput/screenshot"+ tarih+ ".png";

        try {
            FileUtils.copyFile(element.getScreenshotAs(OutputType.FILE), new File(dosyaYolu));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void scrollToElementWithActions(WebDriver driver, WebElement element){
        Actions actions= new Actions(driver);
        actions.scrollToElement(element).perform();
    }
    /**
     * herokuapp sitesindeki webtable dan istenen hucredeki datayi dondurur
     * @param satir istenen satirin int cinsinden degeri girilir
     * @param sutun istenen sutun un int cinsinden degeri girilir
     * @return
     */
    public static String giveSpecificCell(int satir, int sutun){
        WebElement specificCell= getDriver().findElement(By.xpath("//table[@id='table1']//tbody//tr["+satir+"]//td["+sutun+"]"));
        String data= specificCell.getText();
        return data;
    }
    /**
     * bu metot bir elemente javascript ile click yapar
     * @param element yerine click yapilacak elementin locate i girilir
     */
    public static void clickByJavaScript(WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    /**
     * bu metot ile webelemente sendkey yapilir
     * @param string yerine sendkey yapilacak text girilir
     * @param element yerine de locate girilir
     */
    public static void sendKeyByJavascript(String string, WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].value= '"+string+"';", element);
    }
    public static void scrollDown() {
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        jsx.executeScript("window.scrollBy(0, 800)");
    }
    /**
     * bu metot ile bir elementin value'suna deger atanir.
     * @param element deger atanacak elementin locate verilmeli
     * @param text elemente gönderilecek value verilmeli
     */
    public static void sendAttributeJS(String text, WebElement element){
        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].setAttribute('value', '"+text+"')", element);
    }
    /**
     * bu metot ile javascript kullanarak bir elemente sendKey yapılır
     * @param element sendKey yapılacak elementin locate verilmeli
     * @param text elemente gönderilecek değer verilmeli
     */
    public static void sendKeysJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].value='" + text + "'", element);
    }
    /*
    bu metot ile Javascript kullanarak sayfanin en altına scroll yapar
     */
    public static void scrollEndByJavascript(){
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        jsx.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }
     /*
    bu metot ile Javascript kullanarak sayfanin en üstüne scroll yapar
     */

    public static void scrollTopByJavascript(){
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        jsx.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
    }
    /*
    bu metot ile js kullanrak bir lementin değeri okunur ve string olarak bize döner
    @param idText bunun yerine değeri okunacak elementin id değeri text olarak verilir
    @param value yerine okunacak attribute verilir
    @return
     */
    public static String getValueByJavascript(String idText,String value){
        JavascriptExecutor jsx = (JavascriptExecutor) getDriver();
        String getAttribute = jsx.executeScript("return document.getElementById('"+idText+"')."+value).toString();
        return getAttribute;
    }
    /*
    bu metot dev tarafından gizlenen elementin görünür hale getirilmesine yarar
    @param element yerine gizlenen elementin locate verilir
     */
    public static void setElementVisible(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].style.opacity='1';",element);
    }
    /*
   bu metot ile elementin class name değeri verilerek o classtaki text değeri alınıyor
    */
    public static String getTextWithJavaScript(WebElement element){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        String text = (String) javascriptExecutor.executeScript("return arguments[0].textContent;", element);
        return text;
    }
    public static void moveToElement(WebElement element){
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element).perform();
    }
    /**
     * scrollBar elementinin içinde, hedef element (targetElement) görünene kadar
     * scrollTop değerini ayarlar.
     * @param scrollBar     Scroll yapılacak container WebElement'i
     * @param targetElement Scroll edilmek istenen hedef WebElement
     */
    public static void scrollToElementInContainer(WebElement scrollBar, WebElement targetElement) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        // scrollTop değerini, hedef elementin container içindeki konumuna göre ayarla
        js.executeScript(
                "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
                scrollBar, targetElement
        );
    }
}
