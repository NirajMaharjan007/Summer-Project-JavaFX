package javafx.project.database;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.scene.chart.XYChart;

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
            ResultSet resultSet = statement.executeQuery(
                    "SELECT COUNT(*) AS emp_count FROM employees where admin_id="
                            + this.adminId);
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
            data = statement.executeQuery("Select * from employees where admin_id="
                    + this.adminId);
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

    public int updateEmployee(
            int id,
            String name,
            String department,
            String address,
            String salary,
            String gender,
            String email,
            String phone) {
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

    public int setData(
            String name,
            String department,
            String address,
            String salary,
            String gender) {
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
        String sql = "INSERT INTO status(date_time,categories,time_set,emp_id) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateString);
            statement.setString(2, status);
            statement.setString(3, timeString);
            statement.setInt(4, id);
            System.out.println("EmpDatabase.setStatus()=> set status");
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int updateStatus(String status, int id) {
        String sql = "UPDATE status SET categories=?  where emp_id="
                + id
                + " and date_time='"
                + dateString
                + "'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            System.out.println("EmpDatabase.setStatus()=> updated status");
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public boolean isStatusEmpty(int id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "Select count(*) from status where emp_id="
                    + id
                    + " and date_time='"
                    + dateString
                    + "'";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            System.out.println("Status: " + rowCount);
            if (rowCount == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public String getStatus(int id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "Select * from status where date_time='"
                    + dateString
                    + "' and emp_id="
                    + id;
            ResultSet rs = statement.executeQuery(sql);
            String result = "";
            while (rs.next()) {
                result = rs.getString("categories");
            }
            return result;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public XYChart.Series<String, Number> getPresent() {
        XYChart.Series<String, Number> presentSeries = new XYChart.Series<>();
        presentSeries.setName("Present");
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT date_time, count(categories) as nums FROM status " +
                    "LEFT JOIN employees ON status.emp_id = employees.emp_id WHERE employees.admin_id = "
                    + this.adminId
                    + " AND categories = 'Present' "
                    + "GROUP BY DATE(date_time) ORDER BY DATE(date_time)";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Date date = resultSet.getDate("date_time");
                int attendance = resultSet.getInt("nums");
                presentSeries
                        .getData()
                        .add(new XYChart.Data<>(date.toString(), attendance));
            }
            return presentSeries;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public XYChart.Series<String, Number> getAbsent() {
        XYChart.Series<String, Number> absentSeries = new XYChart.Series<>();
        absentSeries.setName("Present");
        try (Statement statement = connection.createStatement()) {
            String query = "SELECT date_time, count(categories) as nums FROM status " +
                    "LEFT JOIN employees ON status.emp_id = employees.emp_id WHERE employees.admin_id = "
                    + this.adminId
                    + " AND categories = 'Absent' "
                    + "GROUP BY DATE(date_time) ORDER BY DATE(date_time)";
            ResultSet resultSet = statement.executeQuery(query);

            // Fetch data from the result set and add it to the series
            while (resultSet.next()) {
                Date date = resultSet.getDate("date_time");
                int attendance = resultSet.getInt("nums");

                absentSeries.getData()
                        .add(new XYChart.Data<>(date.toString(), attendance));
            }
            return absentSeries;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
