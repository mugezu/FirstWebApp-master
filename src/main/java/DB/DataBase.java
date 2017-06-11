package DB;


import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Роман on 10.06.2017.
 */
public class DataBase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";
    final static MysqlConnectionPoolDataSource poolDataSource;

    static {
        poolDataSource = new MysqlConnectionPoolDataSource();
        poolDataSource.setURL(DB_URL);
        poolDataSource.setPassword(PASS);
        poolDataSource.setUser(USER);
    }

    public Connection getConnection() throws SQLException {
        return poolDataSource.getPooledConnection().getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
