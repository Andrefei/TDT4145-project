
import java.io.Closeable;
import java.sql.*;
import java.util.Properties;

public class DBConn implements Closeable {
	protected Connection conn;
    public DBConn () {
    }
    public void connect() {
    	try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // Properties for user and password. Here the user and password are both 'paulr'
            Properties p = new Properties();
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection("jdbc:mysql://localhost/tdt4145?useSSL=false",user, password);
        } catch (Exception e)
    	{
            throw new RuntimeException("Unable to connect", e);
    	}
    }

    public Connection getConnection () {
        return conn;
    }

    public void close () {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
