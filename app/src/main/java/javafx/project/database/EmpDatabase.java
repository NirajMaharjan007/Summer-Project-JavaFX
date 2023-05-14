package javafx.project.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    // private static EmpDatabase instance;

    // private AdminDatabase admin = AdminDatabase.getInstance();

    private int adminId;

    private int id;

    private List<String> nameList = new ArrayList<>();
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
                nameList.add(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return this.id;
    }

    public List<String> getNames() {
        return this.nameList;
    }

    public String getName() {
        return this.name;
    }

}
