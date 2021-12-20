package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.*;

public class BaseTests {


    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");

        //create object of chrome options
        ChromeOptions options = new ChromeOptions();

        //add the headless argument
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.bbc.com");

        List<WebElement> allSummaries = driver.findElements(By.className("media__summary"));
        List<News> newsList = new ArrayList<>();

        for(WebElement summary:allSummaries) {

            News news = new News();

            //save description
            String news_description = summary.getText();

            //find and save topic
            WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].parentNode;", summary);

            String news_topic = ((RemoteWebElement) parent).findElementByClassName("media__link").getText();
            news.setTopic(news_topic);

            //find and save link
            String news_link = ((RemoteWebElement) parent).findElementByClassName("media__link").getAttribute("href");
            news.setLink(news_link);

            newsList.add(news);
        }


        //Close the driver
        driver.close();
    }

}
