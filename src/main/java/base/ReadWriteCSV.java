package base;

import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class ReadWriteCSV {

    private static final String COMMA_DELIMITER = ",";

    static void writeCSV(String CSV_FILE_NAME, Set<News> data) {

        File csvOutputFile = new File(CSV_FILE_NAME);

        if (!csvOutputFile.exists()){
             csvOutputFile = new File(CSV_FILE_NAME);
        }

        try (
                PrintWriter pw = new PrintWriter(csvOutputFile)) {
            for (News news : data) {
                String row = createCSVrow(news);
                pw.println(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    static Set<News> readNewsFromCSV(String CSV_FILE_NAME) {
        Set<News> newsSet = new HashSet<>();

        File csvFile = new File(CSV_FILE_NAME);

        if (!csvFile.exists()){
            return newsSet;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                News news = new News();
                String[] values = line.split(COMMA_DELIMITER);
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

    public static String createCSVrow(News news) {
        String result = news.getTopic() +
                "," +
                news.getLink() +
                "," +
                news.getDescription();
        return escapeSpecialCharacters(result);
    }

    public static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
}






