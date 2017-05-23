/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Atmosphere;
import mysql.LastId;
import mysql.MySqlConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class AtmosphereDAO extends BaseWeatherDAO implements WeatherDAO<Atmosphere> {

    private MySqlConnect connect;

    public void setConnect(MySqlConnect connect) {
        this.connect = connect;
    }

    public void insert(Atmosphere o) {
        Atmosphere a = o;
        String sql = "insert into forecast.atmosphere(atmosphere.idAtmosphere,atmosphere.humidity,atmosphere.pressure,atmosphere.visibility)\n"
                + "values (" + LastId.buscarUltimoId(connect.getConnection(), "atmosphere") + "," + a.getHumidity() + "," + a.getPressure() + "," + a.getVisibility() + ")";
        executeUp(sql, connect.getConnection());
    }

    public Atmosphere update(int id, Atmosphere o) {
        Atmosphere a = o;
        String sql = "update forecast.atmosphere"
                + " set atmosphere.humidity=" + a.getHumidity() + ", atmosphere.pressure=" + a.getPressure() + ", atmosphere.visibility=" + a.getVisibility()
                + " where atmosphere.idAtmosphere=" + id;
        executeUp(sql, connect.getConnection());
        return a;
    }

    public Atmosphere select(int id) {
        float hum = 0;
        float pres = 0;
        float vis = 0;
        Atmosphere a = null;
        String sqlAt = "SELECT a.* "
                + " FROM forecast.atmosphere a"
                + " WHERE a.idAtmosphere =" + id;
        try {
            ResultSet rs = executeQ(sqlAt, connect.getConnection());
            while (rs.next()) {
                hum = rs.getFloat("humidity");
                pres = rs.getFloat("pressure");
                vis = rs.getFloat("visibility");
            }
            a = new Atmosphere(hum, pres, vis);
        } catch (SQLException ex) {
            Logger.getLogger(AtmosphereDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public Atmosphere delete(int id) {
        Atmosphere a = null;
        String sql = "DELETE a\n"
                + " FROM forecast.atmosphere a\n"
                + " WHERE a.idAtmosphere = " + id;
        a = (Atmosphere)select(id);
        executeUp(sql, connect.getConnection());
        return a;
    }
}
