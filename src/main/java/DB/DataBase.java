package DB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Роман on 10.06.2017.
 */
public class DataBase {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC";

    static final String USER = "root";
    static final String PASS = "root";

    private static DataBase dataBase;

    static DataSource poolDataSource = null;

    private DataBase() {
        // Class.forName(JDBC_DRIVER);
        InitialContext context = null;
        try {
            context = new InitialContext();
            poolDataSource = (DataSource) context.lookup("java:/comp/env/jdbc/mydbtest");
        } catch (NamingException e) {
            e.printStackTrace();
        }
        //  poolDataSource.set
      /*  poolDataSource = new MysqlConnectionPoolDataSource();
        poolDataSource.setURL(DB_URL);
        poolDataSource.setPassword(PASS);
        poolDataSource.setUser(USER);*/
    }

    public static synchronized DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }
    //final static MysqlConnectionPoolDataSource poolDataSource;


    public Connection getConnection() throws SQLException {
        Connection connection = poolDataSource.getConnection();
        connection.setAutoCommit(false);
        return connection;
    }

    public void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
