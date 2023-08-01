package javafx.project.database;

import java.sql.*;

public class Session {
    private final Connection conn = Database.getConnection();

    // private String name, password;

    private static Session instance;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public int setSection(int admin_id) {
        String sql = "INSERT INTO session(term,admin_id) values (?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "Yes");
            statement.setInt(2, admin_id);
            return statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    public void setLogin(int admin_id) {
        String sql = "UPDATE session set term='Yes' where admin_id= " + admin_id;
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void setLogout() {
        int admin_id = AdminDatabase.getInstance().getId();
        String sql = "UPDATE session set term='No' where admin_id= " + admin_id;
        try {
            Statement statement = conn.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public String getTerm() {
        int admin_id = AdminDatabase.getInstance().getId();
        try (Statement statement = conn.createStatement()) {
            String term = "";
            String sql = "SELECT term FROM session where admin_id=" + admin_id;
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                term = rs.getString("term");
            }
            return term;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public boolean isNull() {
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
