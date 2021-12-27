package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReadNewsFromSite {

    public Set<News> readAll() {

        //Selenium setup
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.bbc.com");

        List<WebElement> allSummaries = driver.findElements(By.className("media__summary"));
        Set<News> newsSet = new HashSet<>();

        for (WebElement summary : allSummaries) {

            News news = new News();

            //save description
            String news_description = summary.getText();
            news.setDescription(news_description);

            //find and save topic
            WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
                    "return arguments[0].parentNode;", summary);
            String news_topic = (parent.findElement(By.className("media__link")).getText());
            news.setTopic(news_topic);

            //find and save link
            String news_link = (parent.findElement(By.className("media__link")).getAttribute("href"));
            news.setLink(news_link);

            newsSet.add(news);

        }
        //Close the driver
        System.out.println("Closing the driver");
        driver.close();

        return newsSet;
    }
}
