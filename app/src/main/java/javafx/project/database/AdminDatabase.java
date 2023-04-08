package javafx.project.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdminDatabase {
    private Connection conn = Database.getConnection();

    // private int id;
    // private String name, password;

    public boolean setLogin(String name, String password) {
        // this.name = name;
        // this.password = password;

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
                if (name.equals(n) && password.equals(p))
                    return true;
                else
                    return false;
            } else
                return false;
        } catch (Exception e) {
            System.err.println("AdminDatabase.AdminDatabase(): " + e.getMessage());
            return false;
        }
    }
}
