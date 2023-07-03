package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    private static Connection connection;

    public static void initDB() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connect();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:derby:c://testdb0;create=true");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed())
                connection = connect();
        } catch (SQLException ignored) {
        }
        return connection;
    }
}
