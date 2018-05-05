import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Test2 {
    WebDriver dr;

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        dr = new ChromeDriver();
        dr.manage().window().maximize();    //полный экран
        dr.get("http://www.sberbank.ru/ru/person");      //заходим на сайт
    }

    @After
    public void tearDown(){
        dr.quit();
    }

    @Test
    public void test(){

        //
        WebElement region = dr.findElement(By.xpath("//*[@id=\"main\"]/div/div/table/tbody/tr/td/div/div/div/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div/div/div/form/div[3]/a/span"));
        region.click();


        //вводим нужную область
        WebElement inp=dr.findElement(By.xpath("//input[@placeholder='Введите название региона']"));
        inp.sendKeys("Нижегородская");
        WebElement no=dr.findElement(By.xpath("/html/body/div[8]/div/div/div/span"));
        no.click();

        //проверяем, что установлена нужная область
        WebElement rr=dr.findElement(By.xpath("//*[@id=\"main\"]/div/div/table/tbody/tr/td/div/div/div/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div/div/div/form/div[3]/a/span"));
        Assert.assertEquals("Верная область",
                "Нижегородская область", rr.getText());

        //делаем прокрутку до подвала
        WebElement bsmn=dr.findElement(By.xpath("//*[@id=\"main\"]/div/div/table/tbody/tr/td/div/div/div/div/div/div[3]"));
        ((JavascriptExecutor)dr).executeScript("arguments[0].scrollIntoView();",bsmn);

        //проверяем, что есть соцсети
        WebElement netws=dr.findElement(By.cssSelector("#main > div > div > table > tbody > tr > td > div > div > div > div > div > div.sbrf-div-list-inner.--area.bp-area.footer-container > div > div.sbrf-div-list-inner.--area.bp-area.footer-white > div > div > div.sbrf-div-list-inner.--area.bp-area.footer-social > div > div > div > div > ul"));
        if(!netws.isEnabled()){
            System.err.print("Соц. сети недоступны!");
        }
}}
