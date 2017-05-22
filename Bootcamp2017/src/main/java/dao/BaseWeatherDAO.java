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

/**
 *
 * @author federico
 */
public class BaseWeatherDAO {
    
    public void executeUp(String sql, Connection connect){
        try {
            Statement st = connect.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println("Fail to update");
            Logger.getLogger(BaseWeatherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet executeQ(String sql, Connection connect){
        ResultSet rs = null;
        try {
            Statement st = connect.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(BaseWeatherDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
