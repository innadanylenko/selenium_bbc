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

import com.google.common.collect.Sets;

public class BaseTests {

    public static void main(String[] args) {

        News news1 = new News();
        news1.setDescription("d");
        news1.setTopic("b");
        news1.setLink("c");

        News news2 = new News();
        news2.setDescription("a");
        news2.setTopic("b");
        news2.setLink("c");

        boolean diff = news1.equals(news2);



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

        //retrieve news from file
        String CSV_FILE_NAME = ("bbc_News.csv");
        Set<News> newsFromCSV = new HashSet<>();
        newsFromCSV = ReadWriteCSV.readNewsFromCSV(CSV_FILE_NAME);

        //Find difference of news between file and site
        Set<News> difference = Sets.difference(newsFromCSV, newsSet);

        System.out.println(difference);

        //append diff news to file
        ReadWriteCSV.writeCSV(CSV_FILE_NAME, difference);

        HashSet<News> setWithAddedDiff = new HashSet<>();
        setWithAddedDiff = (HashSet<News>) ReadWriteCSV.readNewsFromCSV(CSV_FILE_NAME);


        //Close the driver
        driver.close();
    }


}
