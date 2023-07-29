package javafx.project.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static Connection conn;

    private static final String host = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6635251",
            username = "sql6635251", password = "QydUFrBAgz";

    private Database() {
    }

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                /*
                 * conn = DriverManager.getConnection(
                 * "jdbc:mysql://localhost:3306/hr_data",
                 * "root",
                 * "");
                 */
                conn = DriverManager.getConnection(host, username, password);
            } catch (Exception e) {
                System.err.println("Database.getConnection(); => " + e.getMessage());
            }
        }
        return conn;
    }
}

/*
 * 1.
 * conn = DriverManager.getConnection(
 * "jdbc:mysql://localhost:3306/hr_data",
 * "root",
 * ""
 * );
 * 
 * 2.
 * conn = DriverManager.getConnection(
 * "jdbc:mysql://bfgj93vkpswbuk9ozeav-mysql.services.clever-cloud.com:3306/bfgj93vkpswbuk9ozeav",
 * "u6lzhdcupvy4tqcu",
 * "QNALcQd7InDiAGwGSfSG");
 * 
 */