package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class BaseTests {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        //create object of chrome options
        ChromeOptions options = new ChromeOptions();

        //add the headless argument
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.bbc.com");

        //Get list of web-elements with tagName  - a (Media news)

        WebElement firstSummary = driver.findElement(By.className("media__summary"));

        //System.out.println(firstSummary.getText());


        List<WebElement> allSummaries = driver.findElements(By.className("media__summary"));

        //Traversing through the list and printing its text along with link address
        for(WebElement summary:allSummaries) {
            System.out.println(summary.getText());
            System.out.println();

       }
        //Close the driver
        driver.close();
    }

}
