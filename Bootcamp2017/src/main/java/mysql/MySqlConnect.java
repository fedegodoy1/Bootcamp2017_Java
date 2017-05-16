package mysql;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author federico
 */
public class MySqlConnect {
    // init database constants
//    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
//    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/forecast";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "fedex10";
//    private static final String MAX_POOL = "250";

    // init connection object
    private static Connection connection = null;
    // init properties object
    private Properties properties;
    InputStream input;
    // create properties
    
    
    private MySqlConnect() {
        if (connection == null) {
            try {
                if (properties == null) {
                    try {
                        properties = new Properties();
                        input = new FileInputStream("C:/Users/federico/OneDrive/Documents/Bootcamp Globant/Bootcamp2017_Java/Bootcamp2017/target/classes/dbConfig.properties");
                        properties.load(input);

                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        if (input != null) {
                                try {
                                        input.close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                    }
        //            properties.setProperty("user", USERNAME);
        //            properties.setProperty("password", PASSWORD);
        //            properties.setProperty("MaxPooledStatements", MAX_POOL);
                }
                Class.forName(properties.getProperty("DATABASE_DRIVER"));
                connection = DriverManager.getConnection(properties.getProperty("DATABASE_URL"), properties.getProperty("USERNAME"), properties.getProperty("PASSWORD"));
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static Connection getConnection(){
        if (connection == null){
            new MySqlConnect();
        }
        return connection;
    }
}
