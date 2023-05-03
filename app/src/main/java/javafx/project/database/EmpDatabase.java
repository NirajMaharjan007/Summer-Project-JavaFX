package javafx.project.database;

import java.sql.*;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    private static EmpDatabase instance;

    private EmpDatabase() {
    }

    public static EmpDatabase getInstance() {
        if (instance == null)
            instance = new EmpDatabase();

        return instance;
    }

    public int count() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS emp_count FROM employees");
            resultSet.next();
            int count = resultSet.getInt("emp_count");
            return count;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }
}
