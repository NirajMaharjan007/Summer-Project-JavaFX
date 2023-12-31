package javafx.project.database;

import java.sql.*;
import java.util.Random;

public class AdminDatabase {
    private static AdminDatabase instances;
    private final Connection conn = Database.getConnection();
    private int id;
    private String name, password;

    private AdminDatabase() {
    }

    public static AdminDatabase getInstance() {
        if (instances == null) {
            instances = new AdminDatabase();
        }
        return instances;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM admin where name = '"
                            + this.name
                            + "' and password = '"
                            + this.password
                            + "'");
            while (rs.next()) {
                this.id = rs.getInt(1);
            }
            return this.id;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public boolean setLogin(String name, String password) {
        this.name = name;
        this.password = password;

        String n = null, p = null;

        try {
            String sql = "SELECT * FROM admin WHERE name = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                n = rs.getString("name");
                p = rs.getString("password");
            }
            if (name != null && p != null) {
                if (name.equals(n) && password.equals(p)) {
                    this.name = name;
                    System.out.println("AdminDatabase.setLogin\nAdmin name:" + this.name);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("AdminDatabase.AdminDatabase(): " + e.getMessage());
            return false;
        }
    }

    public ResultSet getDetail() {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * from admin_detail where admin_id=" + getId();
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public int updateAdmin(String admin, String password) throws SQLException {
        String sql = "update admin set name=?, password=? where id=" + getId();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, admin);
        statement.setString(2, password);
        return statement.executeUpdate();
    }

    public int updateAdminDetail(String name, String email, String phone)
            throws SQLException {
        int id = new Random().nextInt(16, Integer.MAX_VALUE);
        String sql = "update admin_detail set id=?, picture=?,name=?,email=?,phone=? where admin_id=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, "src/main/resources/img/uploads/default-pic.png");
        statement.setString(3, name);
        statement.setString(4, email);
        statement.setString(5, phone);
        statement.setInt(6, getId());
        return statement.executeUpdate();
    }

    public int insertAdminDetail(String name, String email, String phone)
            throws SQLException {
        int id = new Random().nextInt(16, Integer.MAX_VALUE);
        String sql = "insert into admin_detail values(?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.setString(2, "src/main/resources/img/uploads/default-pic.png");
        statement.setString(3, name);
        statement.setString(4, email);
        statement.setString(5, phone);
        statement.setInt(6, getId());
        return statement.executeUpdate();
    }

    public boolean isNullAdminDetail() {
        try (Statement statement = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM admin_detail where admin_id=" + getId();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            System.out.println("AdminDatabase.isNullAdminDetail():" + rowCount);
            if (rowCount == 0) {
                System.out.println("admin_detail is empty");
                return false;
            } else {
                System.out.println("admin_detail is not empty");
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    public int setTodos(String title, String text) {
        String sql = "INSERT INTO todos (title, description,created_date,created_time,stat,admin_id) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setTime(4, new Time(System.currentTimeMillis()));
            statement.setString(5, "undone");
            statement.setInt(6, this.getId());
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int updateTodos(String title, String text, int id) {
        String sql = "Update todos set title = ?, description = ?,created_date=?,created_time=? "
                + "where id=?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, text);
            statement.setDate(3, new Date(System.currentTimeMillis()));
            statement.setTime(4, new Time(System.currentTimeMillis()));
            statement.setInt(5, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int deleteTodo(int id) {
        String sql = "DELETE FROM todos WHERE id=? ";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public ResultSet getTodos() {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM todos where admin_id=" + this.getId();
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public ResultSet getTodos(int id) {
        try {
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM todos where id=" + id;
            return statement.executeQuery(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void setDone(int id, boolean done) {
        String sql = "";
        if (done)
            sql = "Update todos set stat = 'done' where id=?";
        else
            sql = "Update todos set stat = 'undone' where id=?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println(sql);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (done)
                System.out.println("AdminDatabase.setDone() -> done");
            else
                System.out.println("AdminDatabase.setDone() -> un-done");
        }
    }

}
