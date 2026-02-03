package musicdb1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {

    private static final String DB_URL =
            "jdbc:mysql://localhost:3306/musicdb";
    private static final String DB_USER = "music_user";
    private static final String DB_PASSWORD = "music_pass";

    /* -------------------- CONNECTION -------------------- */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}