package javafx.project.database;

import java.sql.*;

public class Session {
    private Connection conn = Database.getConnection();

    private String name, password;

    public Session(String name, String password) {
        this.name = name;
        this.password = password;
        boolean flag = AdminDatabase.getInstance().setLogin(name, password);

        if (!flag) {
            throw new NullPointerException("Not fount admin-name and password");
        }
    }

    public int setSection(String term, int admin_id) {
        boolean flag = this.isNull();
        String sql = "INSERT INTO session(term,admin_id) values (?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            if (!flag) {
                statement.setString(1, term);
                statement.setInt(2, admin_id);
            }
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public int setLogout(String term) {
        boolean flag = this.isNull();
        int admin_id = AdminDatabase.getInstance().getId();
        String sql = "UPDATE session set term=? where admin_id=" + admin_id;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            if (!flag) {
                statement.setString(1, term);
            }
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    protected boolean isNull() {
        int admin_id = AdminDatabase.getInstance().getId();
        try (Statement statement = conn.createStatement()) {
            String sql = "SELECT COUNT(*) FROM session where admin_id=" + admin_id;
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int rowCount = rs.getInt(1);
            if (rowCount == 0) {
                System.out.println("session is empty");
                return true;
            } else {
                System.out.println("session is not empty");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
