/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author federico
 */
public class LastId {
    
    public static String buscarUltimoId(Connection conexion, String tipo){
        String ultimo = "";
        Statement stObtener;
        
        switch(tipo){
            case "atmosphere":
                try {
                    stObtener = conexion.createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(a.idAtmosphere) as Cantidad " +
                                                          "FROM forecast.atmosphere a");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LastId.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "location":
                try {
                    stObtener = conexion.createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(l.idLocation) as Cantidad " +
                                                          "FROM forecast.location l");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LastId.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "temperature":
                try {
                    stObtener = conexion.createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(t.idTemperature) as Cantidad " +
                                                          "FROM forecast.temperature t");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LastId.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "wind":
                try {
                    stObtener = conexion.createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(w.idWind) as Cantidad " +
                                                          "FROM forecast.wind w");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LastId.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "day":
                try {
                    stObtener = conexion.createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(d.idDay) as Cantidad " +
                                                          "FROM forecast.day d");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(LastId.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        
        return ultimo;
    }
}
