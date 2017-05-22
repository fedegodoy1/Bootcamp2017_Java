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

    private static String DATABASE_DRIVER;
    private static String DATABASE_URL;
    private static String USERNAME;
    private static String PASSWORD;

    @Override
    public String toString() {
        return "MySqlConnect{" + "DATABASE_DRIVER=" + DATABASE_DRIVER + ", DATABASE_URL=" + DATABASE_URL + ", USERNAME=" + USERNAME + ", PASSWORD=" + PASSWORD + '}';
    }

    private static Connection connection = null;
    //private Properties properties;
    //InputStream input;

    public MySqlConnect(){
        
    }
    
    public void setDATABASE_DRIVER(String DATABASE_DRIVER) {
        this.DATABASE_DRIVER = DATABASE_DRIVER;
    }

    public void setDATABASE_URL(String DATABASE_URL) {
        this.DATABASE_URL = DATABASE_URL;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

//    public MySqlConnect(String dbDriver, String dbUrl, String dbUser, String dbPass){
//        this.DATABASE_DRIVER = dbDriver;
//        this.DATABASE_URL = dbUrl;
//        this.PASSWORD = dbPass;
//        this.USERNAME = dbUser;
//        
//        try {
//            Class.forName(dbDriver);
//            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    private MySqlConnect() {
//        if (connection == null) {
//            try {
//
//                Class.forName("com.mysql.jdbc.Driver");
//                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
//
//            } catch (ClassNotFoundException | SQLException ex) {
//                Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//
//    }
//    public MySqlConnect() {
//        if (connection == null) {
//            try {
//                if (properties == null) {
//                    try {
//                        properties = new Properties();
//                        input = new FileInputStream("C:/Users/federico/OneDrive/Documents/Bootcamp Globant/Bootcamp2017_Java/Bootcamp2017/target/classes/dbConfig.properties");
//                        properties.load(input);
//
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (IOException ex) {
//                        Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
//                    } finally {
//                        if (input != null) {
//                                try {
//                                        input.close();
//                                } catch (IOException e) {
//                                        e.printStackTrace();
//                                }
//                        }
//                    }
//                }
//                Class.forName(properties.getProperty("DATABASE_DRIVER"));
//                connection = DriverManager.getConnection(properties.getProperty("DATABASE_URL"), properties.getProperty("USERNAME"), properties.getProperty("PASSWORD"));
//            } catch (ClassNotFoundException | SQLException e) {
//                e.printStackTrace();
////            }
//        }
//    }
//    public void disconnect() {
//        if (connection != null) {
//            try {
//                connection.close();
//                connection = null;
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(MySqlConnect.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}

