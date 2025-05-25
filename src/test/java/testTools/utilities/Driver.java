package testTools.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;

public class Driver {
    private Driver() {

    }

    static WebDriver driver;

    public static WebDriver getDriver (){
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")){
                case "chrome":

                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless"); // Headless test yapmak için
                    options.addArguments("--disable-gpu"); // GPU kullanımını devre dısı bırakır
                    //driver = new ChromeDriver(options); // bu kodu sadece headless testte aç

                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                case "edge":
                    driver = new EdgeDriver(new EdgeOptions().addArguments("--remote-allow-origins=*"));
                    break;
                default:
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--remote-allow-origins=*"));

            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }
    public static void closeDriver() {
        if (driver != null){
            driver.close();
            driver = null;
        }
    }
    public static void quitDriver() {
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
