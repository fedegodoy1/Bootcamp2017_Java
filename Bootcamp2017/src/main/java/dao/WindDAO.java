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
import domain.Wind;
import mysql.LastId;
import mysql.MySqlConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class WindDAO extends BaseWeatherDAO implements WeatherDAO<Wind> {

    private MySqlConnect connect;

    public void insert(Wind o) {
        Wind w = o;
        String sql = "insert into forecast.wind(wind.idWind, wind.direction, wind.speed)\n"
                    + "values (" + LastId.buscarUltimoId(connect.getConnection(), "wind") + ",'" + w.getDirection() + "'," + w.getSpeed() + ")";
        executeUp(sql, connect.getConnection());
    }

    public Wind update(int id, Wind o) {
        Wind w = o;
        String sql = "update forecast.wind"
                    + " set wind.direction='" + w.getDirection() + "', wind.speed=" + w.getSpeed()
                    + " where wind.idWind=" + id;
        executeUp(sql, connect.getConnection());
        return w;
    }

    public Wind select(int id) {
        Wind w = null;
        String sqlWind = "SELECT w.* "
                    + "FROM forecast.wind w "
                    + "WHERE w.idWind =" + id;
        String dir = "";
        float spd = 0;
        ResultSet rs = executeQ(sqlWind, connect.getConnection());;
        try {
            while (rs.next()) {
                dir = rs.getString("direction");
                spd = rs.getFloat("speed");
            }
            w = new Wind(dir, spd);
        } catch (SQLException ex) {
            Logger.getLogger(WindDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    public Wind delete(int id) {
        Wind w = null;
        String sql = "DELETE w\n"
                    + " FROM forecast.wind a\n"
                    + " WHERE w.idWind = " + id;
        w = (Wind) select(id);
        executeUp(sql, connect.getConnection());
        return w;
    }
}
