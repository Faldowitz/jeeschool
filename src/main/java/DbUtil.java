import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbUtil {
    private static DataSource dataSource;

    private DbUtil() {

    }

    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }

    //tutaj zaczyna się Singleton
    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/school");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }
    /*tutaj się kończy Singleton; dzięki temu nie musimy zmieniać aplikacji (przerwa w działaniu)
    tylko przekazujemy dane, które mają być zmienione w aplikacji*/
}
