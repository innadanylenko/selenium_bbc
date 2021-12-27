package base;

import com.google.common.collect.Sets;

import java.util.Set;

public class NewsCrawlerService {

    ReadWriteCSV readWriteCSV = new ReadWriteCSV();

    public void appendNews() {

        //Read all news from site and file
        Set<News> newsFromSite = new ReadNewsFromSite().readAll();
        Set<News> newsFromFile = readWriteCSV.read();

        //Find difference of news between file and site
        System.out.println("Comparing news on site and in file");
        Set<News> difference = Sets.difference(newsFromSite, newsFromFile);

        //append diff news to file
        System.out.println("Appending difference to file");
        readWriteCSV.write(difference);
    }

}
