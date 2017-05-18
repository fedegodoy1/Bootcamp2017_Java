/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Atmosphere;
import mysql.LastId;
import mysql.MySqlConnect;

/**
 *
 * @author federico
 */
public class AtmosphereDAO implements WeatherDAO{
    public void insert(Object o){
        Atmosphere a =(Atmosphere) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="insert into forecast.atmosphere(atmosphere.idAtmosphere,atmosphere.humidity,atmosphere.pressure,atmosphere.visibility)\n" +
                    "values ("+ LastId.buscarUltimoId(connect,"atmosphere") +","+ a.getHumidity() +","+ a.getPressure() +","+ a.getVisibility()+")";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Object update(int id,Object o){
        Atmosphere a =(Atmosphere) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="update forecast.atmosphere"
                    + " set atmosphere.humidity="+a.getHumidity()+", atmosphere.pressure="+a.getPressure()+", atmosphere.visibility="+a.getVisibility()
                    + " where atmosphere.idAtmosphere="+id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public Object select(int id){
        Atmosphere a = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = connect.createStatement();
            String sqlAt="SELECT a.* "
                    + " FROM forecast.atmosphere a"
                    + " WHERE a.idAtmosphere ="+id;
            rs = st.executeQuery(sqlAt);
            float hum = 0;
            float pres = 0;
            float vis = 0;
            while(rs.next()){
                hum = rs.getFloat("humidity");
                pres = rs.getFloat("pressure");
                vis = rs.getFloat("visibility");
            }
            a = new Atmosphere(hum,pres,vis);
            }   
        catch (SQLException ex) {
            Logger.getLogger(AtmosphereDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return a;
    }
    
    public Object delete(int id){
        Atmosphere a = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        
        try{
            String sql = "DELETE a\n" +
                        " FROM forecast.atmosphere a\n" +
                        " WHERE a.idAtmosphere = "+id;
            a = (Atmosphere) select(id);
            st = connect.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
