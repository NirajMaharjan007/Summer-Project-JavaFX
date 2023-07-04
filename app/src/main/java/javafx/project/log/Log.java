package javafx.project.log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    private static Log instance;
    private File file;

    private SimpleDateFormat formatter;
    private Date date;
    private String dateString;

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    private Log() {
        formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();
        dateString = formatter.format(date);

        try {
            file = new File("src/main/resources/log/activity.log");
            if (!file.exists()) {
                if (file.createNewFile())
                    System.out.println("file created");
                else
                    System.out.println("Failed to create");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void setLog(String text) {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(text + ";\tTime: " + dateString + "\n\n");
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public String getLog() {
        try (FileReader reader = new FileReader(file)) {
            StringBuilder builder = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                builder.append((char) c);
            }
            reader.close();
            return builder.toString();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void clear() {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write("");
            writer.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
