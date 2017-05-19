
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Atmosphere;
import main.Day;
import main.Location;
import main.Temperature;
import main.Wind;
import mysql.MySqlConnect;
import mysql.LastId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class DayDAO implements WeatherDAO{
    
    @Autowired
    private MySqlConnect connect;
    
    public void insert(Object o){
        Day d = (Day) o;
        Statement st;
        try {
            st = connect.getConnection().createStatement();
            int idAt = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(),"atmosphere"));
            int idLoc = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(),"location"));
            int idTemp = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(),"temperature"));
            int idW = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(),"wind"));
            if(idAt != 0 && idLoc != 0 && idTemp!=0 && idW != 0){
                idAt --;
                idLoc --;
                idTemp --;
                idW --;
            }
            
            String sql="insert into forecast.day(day.idDay, day.name, day.date, day.description,day.idAtmosphere,day.idLocation,day.idTemperature,day.idWind) \n" +
                    "values ("+ LastId.buscarUltimoId(connect.getConnection(),"day") +",'"+d.getName()+"','"+d.getDate()+"','"+ d.getDescription()+"',"+ idAt +","+ idLoc +","+ idTemp +","+ idW +")";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public Object update(int id, Object o){
        Day d = (Day) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="update forecast.day"
                    + "set day.name='"+d.getName()+"', day.date='"+d.getDate()+"', day.description='"+d.getDescription()+"'"
                    + "where day.idDay="+id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public Object select(int id){
        Day d = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = connect.createStatement();
            String sqlAt="SELECT a.* "
                    + "FROM forecast.atmosphere a, forecast.day d "
                    + "WHERE d.idDay ="+id+" and d.idAtmosphere = a.idAtmosphere";
            rs = st.executeQuery(sqlAt);
            float hum = 0;
            float pres = 0;
            float vis = 0;
            while(rs.next()){
                hum = rs.getFloat("humidity");
                pres = rs.getFloat("pressure");
                vis = rs.getFloat("visibility");
            }
            Atmosphere a = new Atmosphere(hum,pres,vis);
            
            String sqlLoc="SELECT l.* "
                    + "FROM forecast.location l, forecast.day d "
                    + "WHERE d.idDay ="+id+" and d.idLocation = l.idLocation";
            rs = st.executeQuery(sqlLoc);
            String city = "";
            String country = "";
            String region = "";
            while(rs.next()){
                city = rs.getString("city");
                country = rs.getString("country");
                region = rs.getString("region");
            }
            Location l = new Location(city,country,region);
            
            String sqlTemp="SELECT t.* "
                    + "FROM forecast.temperature t, forecast.day d "
                    + "WHERE d.idDay ="+id+" and d.idTemperature = t.idTemperature";
            rs=st.executeQuery(sqlTemp);
            float cTemp = 0;
            float hTemp = 0;
            float lTemp = 0;
            while(rs.next()){
                cTemp = rs.getFloat("currentTemperature");
                hTemp = rs.getFloat("highTemperature");
                lTemp = rs.getFloat("lowTemperature");
            }
            Temperature t = new Temperature(cTemp,hTemp,lTemp);
            
            String sqlWind="SELECT w.* "
                    + "FROM forecast.wind w, forecast.day d "
                    + "WHERE d.idDay ="+id+" and d.idWind = w.idWind";
            rs = st.executeQuery(sqlWind);
            String dir = "";
            float spd = 0;
            while(rs.next()){
                dir = rs.getString("direction");
                spd = rs.getFloat("speed");
            }
            Wind w = new Wind(dir,spd);
            
            String sql="SELECT d.* \n"+
                       "FROM forecast.day d\n" +
                       "WHERE d.idDay = "+id;
            rs = st.executeQuery(sql);
            String name = "";
            String date = "";
            String descr = "";
            while(rs.next()){
                name = rs.getString("name");
                date = rs.getString("date");
                descr = rs.getString("description");
            }
            d = new Day(date,descr,name,l,a,w,t);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    public Object delete(int id){
        Day d = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        
        try{
            String sql = "DELETE d,a,l,t,w\n" +
                        "FROM forecast.day d INNER JOIN forecast.atmosphere a INNER JOIN forecast.location l INNER JOIN forecast.temperature t INNER JOIN forecast.wind w\n" +
                        "WHERE d.idDay = "+id+" and d.idAtmosphere = a.idAtmosphere and d.idLocation = l.idLocation and d.idWind = w.idWind and d.idTemperature = t.idTemperature";
            d = (Day) select(id);
            st = connect.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
}
