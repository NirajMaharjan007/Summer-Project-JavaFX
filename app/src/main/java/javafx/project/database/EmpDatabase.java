package javafx.project.database;

import java.sql.*;
import java.util.*;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    private static EmpDatabase instance;

    private AdminDatabase admin = AdminDatabase.getInstance();

    private final int adminId;

    private EmpDatabase() {
        adminId = admin.getId();
    }

    public static EmpDatabase getInstance() {
        if (instance == null)
            instance = new EmpDatabase();

        return instance;
    }

    public int count() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(*) AS emp_count FROM employees where admin_id=" + adminId);
            resultSet.next();
            int count = resultSet.getInt("emp_count");
            return count;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public List<Map<String, Object>> getData() {
        List<Map<String, Object>> data = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM employees where admin_id=" + adminId;
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i);
                    row.put(columnName, value);
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

}
