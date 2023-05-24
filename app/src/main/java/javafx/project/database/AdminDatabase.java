package javafx.project.database;

import java.sql.*;

public class AdminDatabase {
    private static AdminDatabase instances;
    private final Connection conn = Database.getConnection();
    private int id;
    private String name, password;

    private AdminDatabase() {
    }

    public static AdminDatabase getInstance() {
        if (instances == null)
            instances = new AdminDatabase();
        return instances;
    }

    public int getId() {
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM admin where name = '" + this.name +
                    "' and password = '" + this.password + "'");
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
                    System.out.println("AdminDatabase.setLogin\nAdmin name:"
                            + this.name);
                    return true;
                } else
                    return false;
            } else
                return false;
        } catch (Exception e) {
            System.err.println("AdminDatabase.AdminDatabase(): " + e.getMessage());
            return false;
        }
    }
}
