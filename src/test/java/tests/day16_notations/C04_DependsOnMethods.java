package tests.day16_notations;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.security.Key;
import java.time.Duration;

public class C04_DependsOnMethods  {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void tearDown(){
        driver.close();
    }

    @Test
    public void test01() {
        // amazon anasayfaya gidelim
        driver.get("https://www.amazon.com");
    }

    @Test (dependsOnMethods ="test01")
    public void test02() {
        // Nutella aratalim
        WebElement aramaKutusu=driver.findElement(By.id("twotabsearchtextbox"));
        aramaKutusu.sendKeys("Nutella" + Keys.ENTER);

    }

    @Test (dependsOnMethods ="test02")
    public void test03() {
        // Sonuc yazisinin Nutella icerdigini test edelim
        WebElement sonucYaziElementi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        Assert.assertTrue(sonucYaziElementi.getText().contains("Nutella"));

    }

    @Test
    public void test04(){
        System.out.println("Bu calisti");
    }
}
