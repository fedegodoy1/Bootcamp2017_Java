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
import main.Wind;
import mysql.LastId;
import mysql.MySqlConnect;

/**
 *
 * @author federico
 */
public class WindDAO implements WeatherDAO{
    public void insert(Object o){
        Wind w = (Wind) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="insert into forecast.wind(wind.idWind, wind.direction, wind.speed)\n" +
                    "values ("+ LastId.buscarUltimoId(connect,"wind") +",'"+ w.getDirection() +"',"+ w.getSpeed() +")";

            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object update(int id,Object o){
        Wind w = (Wind) o;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        try {
            st = connect.createStatement();
            String sql="update forecast.wind"
                    + " set wind.direction='"+w.getDirection()+"', wind.speed="+w.getSpeed()
                    + " where wind.idWind="+id;
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }
    
    public Object select(int id){
        Wind w = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        ResultSet rs;
        try {
            st = connect.createStatement();
            String sqlWind="SELECT w.* "
                    + "FROM forecast.wind w "
                    + "WHERE w.idWind ="+id;
            rs = st.executeQuery(sqlWind);
            String dir = "";
            float spd = 0;
            while(rs.next()){
                dir = rs.getString("direction");
                spd = rs.getFloat("speed");
            }
            w = new Wind(dir,spd);
            }   
        catch (SQLException ex) {
            Logger.getLogger(AtmosphereDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return w;
    }
    public Object delete(int id){
        Wind w = null;
        Connection connect = MySqlConnect.getConnection();
        Statement st;
        
        try{
            String sql = "DELETE w\n" +
                        " FROM forecast.wind a\n" +
                        " WHERE w.idWind = "+id;
            w = (Wind) select(id);
            st = connect.createStatement();
            st.executeUpdate(sql);
        }catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }
}
