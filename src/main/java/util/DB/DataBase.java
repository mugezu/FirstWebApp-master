package util.DB;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Роман on 10.06.2017.
 */
public class DataBase {

    private static DataBase dataBase;

    private static DataSource poolDataSource = null;

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
        return connection;
    }
}
