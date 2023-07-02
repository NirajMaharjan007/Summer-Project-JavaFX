package javafx.project.database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmpDatabase {
    private final Connection connection = Database.getConnection();

    private int adminId;

    private ResultSet data;

    String dateString, timeString;

    public EmpDatabase(int adminId) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");

        this.adminId = adminId;
        this.dateString = formatter.format(date);
        this.timeString = timeFormatter.format(date);
    }

    public int count() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(*) AS emp_count FROM employees where admin_id=" + this.adminId);
            resultSet.next();

            int count = resultSet.getInt("emp_count");
            return count;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public ResultSet getData() {
        try {
            Statement statement = connection.createStatement();
            data = statement.executeQuery("Select * from employees where admin_id=" + this.adminId);
            return data;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int deleteEmployee(String id) {
        String sql = "DELETE FROM employees WHERE emp_id=? ";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int updateEmployee(int id, String name, String department, String address, String salary,
            String gender, String email, String phone) {
        String sql = "UPDATE employees SET name = ?, department = ?, address= ?,salary = ?, gender = ?,email=?,phone=? WHERE emp_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, address);
            statement.setString(4, salary);
            statement.setString(5, gender);
            statement.setString(6, email);
            statement.setString(7, phone);
            statement.setInt(8, id);
            int i = statement.executeUpdate();
            return i;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public ResultSet getData(int id) {
        try {
            String sql = "Select * from employees where emp_id=" + id;
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(sql);
            return data;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int setData(String name, String department, String address, String salary, String gender) {
        try {
            String sql = "INSERT INTO employees"
                    + "(name,department,address,salary,gender,emp_img,email,phone,admin_id)"
                    + " VALUES (?,?,?,?,?,?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, department);
            statement.setString(3, address);
            statement.setString(4, salary);
            statement.setString(5, gender);
            statement.setString(6, "src/main/resources/img/uploads/default-pic.png");
            statement.setString(7, "N/A");
            statement.setString(8, "N/A");
            statement.setInt(9, this.adminId);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int setStatus(String status, int id) {
        String sql = "INSERT INTO status(date_time,status,time_set,emp_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateString);
            statement.setString(2, status);
            statement.setString(3, timeString);
            statement.setInt(4, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int updateStatus(String status, int id) {
        String sql = "UPDATE status SET status=?  where emp_id=" + id + " and date_time='" + dateString + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public boolean isStatusEmpty(int id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "Select count(*) from status where emp_id=" + id + " and date_time='" + dateString + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            System.out.println("Status: " + rowCount);
            if (rowCount == 0)
                return false;
            else
                return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public ResultSet getStatus() {
        try (Statement statement = connection.createStatement()) {
            String sql = "Select * from status where date_time='" + dateString + "'";
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
