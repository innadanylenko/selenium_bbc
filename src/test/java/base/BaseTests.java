package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.List;
//TODO: switch to headless browser

public class BaseTests {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        //create object of chrome options
        ChromeOptions options = new ChromeOptions();

        //add the headless argument
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.bbc.com");
        System.out.println(driver.getTitle());

        //Get list of web-elements with tagName  - a
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        //Traversing through the list and printing its text along with link address
        for(WebElement link:allLinks) {
            System.out.println(link.getText() + " - " + link.getAttribute("href"));

       }
        //Close the driver
        driver.close();
    }
}
