package javafx.project.database;

import java.sql.*;

public class Session {
    private final Connection conn = Database.getConnection();

//    private String name, password;
    private static Session instance;

    private Session() {
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public String getName() {
        try (Statement statement = conn.createStatement()) {
            int id = this.getId();
            String sql = "Select name from admin where id=" + id;
            ResultSet rs = statement.executeQuery(sql);
            String name = "";
            while (rs.next()) {
                name = rs.getString("name");
            }
            return name;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String getPassword() {
        try (Statement statement = conn.createStatement()) {
            int id = this.getId();
            String sql = "Select password from admin where id=" + id;
            ResultSet rs = statement.executeQuery(sql);
            String password = "";
            while (rs.next()) {
                password = rs.getString("password");
            }
            return password;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int getId() {
        try (Statement statement = conn.createStatement()) {
            String sql = "Select admin_id from session where term='Yes'";
            ResultSet rs = statement.executeQuery(sql);
            int id = -1;
            while (rs.next()) {
                id = rs.getInt("admin_id");
            }

            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
//        return 0;
    }

    public void setSection(int admin_id) {
        String sql = "INSERT INTO session(term,admin_id) values (?,?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, "Yes");
            statement.setInt(2, admin_id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
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

    public boolean isSetTerm() {
        try (Statement statement = conn.createStatement()) {
            String sql = "Select admin_id from session where term='Yes'";
            ResultSet rs = statement.executeQuery(sql);
            return rs.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
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
