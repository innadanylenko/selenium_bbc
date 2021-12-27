package base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewsCrawler {

    static ScheduledExecutorService executorService;

    public static void main(String[] args) {
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Running: " + new java.util.Date());
            new NewsCrawlerService().appendNews();
        }, 0, 100, TimeUnit.SECONDS);
    }
}
