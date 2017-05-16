package mysql;

import main.Atmosphere;
import main.Day;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author federico
 */
public class H2DataBase {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
    
    public static void insert()throws SQLException{
        Connection connection = getDBConnection();
        Statement stmt = null;
        ResultSet rs=null;
        try{
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            String createAtmosphere = "CREATE TABLE `atmosphere` (\n" +
                                "  `idAtmosphere` int(11) NOT NULL,\n" +
                                "  `humidity` float NOT NULL,\n" +
                                "  `pressure` float NOT NULL,\n" +
                                "  `visibility` float NOT NULL,\n" +
                                "  PRIMARY KEY (`idAtmosphere`)\n" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            
            String createLocation="CREATE TABLE `location` (\n" +
                                "  `idLocation` int(11) NOT NULL,\n" +
                                "  `city` varchar(45) NOT NULL,\n" +
                                "  `country` varchar(45) NOT NULL,\n" +
                                "  `region` varchar(45) NOT NULL,\n" +
                                "  PRIMARY KEY (`idLocation`)\n" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            
            String createTemp = "CREATE TABLE `temperature` (\n" +
                                "  `idTemperature` int(11) NOT NULL,\n" +
                                "  `currentTemperature` float NOT NULL,\n" +
                                "  `highTemperature` float NOT NULL,\n" +
                                "  `lowTemperature` float NOT NULL,\n" +
                                "  PRIMARY KEY (`idTemperature`)\n" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            
            String createWind = "CREATE TABLE `wind` (\n" +
                                "  `idWind` int(11) NOT NULL,\n" +
                                "  `direction` varchar(45) NOT NULL,\n" +
                                "  `speed` float NOT NULL,\n" +
                                "  PRIMARY KEY (`idWind`)\n" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            
            
            String createDay = "CREATE TABLE `day` (\n" +
                            "  `idDay` int(11) NOT NULL,\n" +
                            "  `date` varchar(45) NOT NULL,\n" +
                            "  `description` varchar(45) NOT NULL,\n" +
                            "  `name` varchar(45) NOT NULL,\n" +
                            "  `idLocation` int(11) NOT NULL,\n" +
                            "  `idAtmosphere` int(11) NOT NULL,\n" +
                            "  `idWind` int(11) NOT NULL,\n" +
                            "  `idTemperature` int(11) NOT NULL,\n" +
                            "  PRIMARY KEY (`idDay`),\n" +
                            "  CONSTRAINT `idLocation` FOREIGN KEY (`idLocation`) REFERENCES `location` (`idLocation`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                            "  CONSTRAINT `idAtmosphere` FOREIGN KEY (`idAtmosphere`) REFERENCES `atmosphere` (`idAtmosphere`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                            "  CONSTRAINT `idWind` FOREIGN KEY (`idWind`) REFERENCES `wind` (`idWind`) ON DELETE NO ACTION ON UPDATE NO ACTION,\n" +
                            "  CONSTRAINT `idTemperature` FOREIGN KEY (`idTemperature`) REFERENCES `temperature` (`idTemperature`) ON DELETE NO ACTION ON UPDATE NO ACTION)";
            stmt.execute(createAtmosphere);
            stmt.execute(createLocation);
            stmt.execute(createTemp);
            stmt.execute(createWind);
            stmt.execute(createDay);
//            //trae todos los datos de la bd en mysql para insertarlos en la bd de h2
//            Connection sqlConn = MySqlConnect.getConnection();
//            Statement stmtMySQL = sqlConn.createStatement();
//            
//            String selectAtmosphere = "SELECT * FROM forecast.atmosphere";
//            rs = stmtMySQL.executeQuery(selectAtmosphere);
//            while(rs.next()){
//                int id=rs.getInt("idAtmosphere");
//                float hum = rs.getFloat("humidity");
//                float press = rs.getFloat("pressure");
//                float visib = rs.getFloat("visibility");
//                String insert = "insert into atmosphere(idAtmosphere,humidity,pressure,visibility)\n" +
//                                "values ("+id+","+hum+","+press+","+visib+")";
//                stmt.execute(insert);
//            }
//            
//            String selectLocation = "SELECT * FROM forecast.location";
//            rs = stmtMySQL.executeQuery(selectLocation);
//            while(rs.next()){
//                int id=rs.getInt("idLocation");
//                String city = rs.getString("city");
//                String country = rs.getString("country");
//                String region = rs.getString("region");
//                
//                String insert = "insert into location(idLocation,city,country,region)\n" +
//                                "values ("+id+",'"+city+"','"+country+"','"+region+"')";
//                stmt.execute(insert);
//            }
//            
//            String selectTemp = "SELECT * FROM forecast.temperature";
//            rs = stmtMySQL.executeQuery(selectTemp);
//            while(rs.next()){
//                int id=rs.getInt("idTemperature");
//                float cTemp = rs.getFloat("currentTemperature");
//                float hTemp = rs.getFloat("highTemperature");
//                float lTemp = rs.getFloat("lowTemperature");
//                
//                String insert = "insert into temperature(idTemperature,currentTemperature,highTemperature,lowTemperature)\n" +
//                                "values ("+id+","+cTemp+","+hTemp+","+lTemp+")";
//                stmt.execute(insert);
//            }
//            
//            String selectWind = "SELECT * FROM forecast.wind";
//            rs = stmtMySQL.executeQuery(selectWind);
//            while(rs.next()){
//                int id=rs.getInt("idWind");
//                String dir = rs.getString("direction");
//                float spd = rs.getFloat("speed");
//                
//                String insert = "insert into wind(idWind,direction,speed)\n" +
//                                "values ("+id+",'"+dir+"',"+spd+")";
//                stmt.execute(insert);
//            }
//            
//            String selectDay = "SELECT * FROM forecast.day";
//            rs = stmtMySQL.executeQuery(selectDay);
//            while(rs.next()){
//                int id=rs.getInt("idDay");
//                String date = rs.getString("date");
//                String descr = rs.getString("description");
//                String name = rs.getString("name");
//                int loc = rs.getInt("idLocation");
//                int at = rs.getInt("idAtmosphere");
//                int w = rs.getInt("idWind");
//                int te = rs.getInt("idTemperature");
//                String insert = "insert into day(idDay,name,date,description,idAtmosphere,idLocation,idTemperature,idWind)\n" +
//                                "values ("+id+",'"+name+"','"+date+"','"+descr+"',"+loc+","+at+","+w+","+te+")";
//                stmt.execute(insert);
//            }
            
            
//            
//            stmt.execute("DROP TABLE day");
//            stmt.execute("DROP TABLE location");
//            stmt.execute("DROP TABLE atmosphere");
//            stmt.execute("DROP TABLE wind");
//            stmt.execute("DROP TABLE temperature");
//            stmt.close();
//            connection.commit();
            
        }catch(SQLException e){
            System.out.println("Exception message: "+e.getLocalizedMessage());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            connection.close();
        }
    }
    
    
    public static void h2ConnectionClose(Statement stmt, Connection connection) throws SQLException{
        stmt.execute("DROP TABLE day");
        stmt.execute("DROP TABLE location");
        stmt.execute("DROP TABLE atmosphere");
        stmt.execute("DROP TABLE wind");
        stmt.execute("DROP TABLE temperature");
        stmt.close();
        connection.commit();
    }
    
}
