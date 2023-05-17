package javafx.project.database;

import java.sql.*;

import javafx.collections.*;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    // private static EmpDatabase instance;

    // private AdminDatabase admin = AdminDatabase.getInstance();

    private ObservableList<String> row = FXCollections.observableArrayList();

    private int adminId;

    private int id;

    private String name = "";

    public EmpDatabase(int adminId) {
        this.adminId = adminId;

        this.getData();
    }

    public int count() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(*) AS emp_count FROM employees where admin_id=" + this.adminId);
            resultSet.next();
            System.out.println("EmpDatabase.count(): " + this.adminId);
            int count = resultSet.getInt("emp_count");
            return count;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    private void getData() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM employees where admin_id=" + adminId;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.id = rs.getInt(1);
                this.name = rs.getString(2);

                for (int i = 1; i <= 6; i++)
                    row.add(rs.getString(i));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
