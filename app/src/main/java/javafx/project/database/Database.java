package javafx.project.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection conn;

    private Database() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "root", "");
            } catch (Exception e) {
                System.err.println("Database.getConnection(); => " + e.getMessage());
            }
        }
        return conn;
    }
}
