package base;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ReadWriteCSV {

    private String DELIMITER = ";";
    private String CSV_FILE_NAME = ("bbc_News.csv");

    public void write(Set<News> data) {
        if (data.isEmpty()) {
            return;
        }
        File csvOutputFile = new File(CSV_FILE_NAME);
        PrintWriter out = null;

        if (csvOutputFile.exists() && !csvOutputFile.isDirectory()) {
            try {
                out = new PrintWriter(new FileOutputStream(CSV_FILE_NAME, true));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {
                out = new PrintWriter(CSV_FILE_NAME);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        for (News news : data) {
            String row = createCSVrow(news);
            out.println(row);
        }
        out.flush();

    }

    public Set<News> read() {
        Set<News> newsSet = new HashSet<>();
        File csvFile = new File(CSV_FILE_NAME);
        if (!csvFile.exists()) {
            return newsSet;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                News news = new News();
                String[] values = line.split(DELIMITER);
                news.setTopic(values[0]);
                news.setLink(values[1]);
                news.setDescription(values[2]);
                newsSet.add(news);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsSet;
    }

    public String createCSVrow(News news) {
        String result = news.getTopic() +
                DELIMITER +
                news.getLink() +
                DELIMITER +
                news.getDescription();
        return escapeSpecialCharacters(result);
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}






