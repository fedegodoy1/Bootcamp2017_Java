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
import main.Location;
import mysql.LastId;
import mysql.MySqlConnect;

/**
 *
 * @author federico
 */
public class LocationDAO implements WeatherDAO{
    public void insert(Object o){
        Location l = (Location) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="insert into forecast.location(location.idLocation, location.city, location.country,location.region)\n" +
                    "values ("+ LastId.buscarUltimoId(connect,"location") +",'"+ l.getCity() +"','"+ l.getCountry() +"','"+ l.getRegion()+"')";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object update(int id,Object o){
        Location l = (Location) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="update forecast.location"
                    + " set location.city='"+l.getCity()+"', location.country='"+l.getCountry()+"', location.region='"+l.getRegion()+"' "
                    + " where location.idLocation="+id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    public Object select(int id){
        
        Location l = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = connect.createStatement();
            String sqlLoc="SELECT l.* "
                    + "FROM forecast.location l"
                    + "WHERE l.idLocation ="+id;
            rs = st.executeQuery(sqlLoc);
            String city = "";
            String country = "";
            String region = "";
            while(rs.next()){
                city = rs.getString("city");
                country = rs.getString("country");
                region = rs.getString("region");
            }
            l = new Location(city,country,region);
            }   
        catch (SQLException ex) {
            Logger.getLogger(AtmosphereDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return l;
    }
    
    public Object delete(int id){
        Location l = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        
        try{
            String sql = "DELETE l\n" +
                        "FROM forecast.location l\n" +
                        "WHERE l.idLocation = "+id;
            l = (Location) select(id);
            st = connect.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }
    
}
