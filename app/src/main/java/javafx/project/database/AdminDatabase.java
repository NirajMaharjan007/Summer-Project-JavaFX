package javafx.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDatabase {
    private static AdminDatabase instances;
    private final Connection conn = Database.getConnection();
    // private int id;
    private String name;

    private AdminDatabase() {
    }

    public static AdminDatabase getInstance() {
        if (instances == null)
            instances = new AdminDatabase();
        return instances;
    }

    public boolean setLogin(String name, String password) {
        String n = null, p = null;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM admin where name = '" + name +
                    "' and password = '" + password + "'");
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
