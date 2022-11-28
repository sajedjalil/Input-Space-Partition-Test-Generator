package isp.database;
import isp.constants.IConstant;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLiteConnection {

    public static Connection connect() {
        String url = "jdbc:sqlite:" + IConstant.dbURL + File.separator + IConstant.dbName;

        java.sql.Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
