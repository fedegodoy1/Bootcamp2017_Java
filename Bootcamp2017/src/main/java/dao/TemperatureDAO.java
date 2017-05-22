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
import main.Temperature;
import mysql.LastId;
import mysql.MySqlConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class TemperatureDAO extends BaseWeatherDAO implements WeatherDAO<Temperature> {

    private MySqlConnect connect;

    public void insert(Temperature o) {
        Temperature t = o;
        String sql = "insert into forecast.temperature(temperature.idTemperature, temperature.currentTemperature, temperature.highTemperature, temperature.lowTemperature)\n"
                + "values (" + LastId.buscarUltimoId(connect.getConnection(), "temperature") + "," + t.getCurrentTemperature() + "," + t.getHighTemperature() + "," + t.getLowTemperature() + ")";
        executeUp(sql, connect.getConnection());
    }

    public Object update(int id, Temperature o) {
        Temperature t = o;
        String sql = "update forecast.temperature"
                + " set temperature.currentTemperature=" + t.getCurrentTemperature() + ", temperature.highTemperature=" + t.getHighTemperature() + ", temperature.lowTemperature=" + t.getLowTemperature()
                + " where temperature.idTemperature=" + id;
        executeUp(sql, connect.getConnection());
        return t;
    }

    public Object select(int id) {
        Temperature t = null;
        float cTemp = 0;
        float hTemp = 0;
        float lTemp = 0;
        String sqlTemp = "SELECT t.* "
                + " FROM forecast.temperature t "
                + " WHERE t.idTemperature =" + id;
        ResultSet rs = executeQ(sqlTemp, connect.getConnection());
        try {
            while (rs.next()) {
                cTemp = rs.getFloat("currentTemperature");
                hTemp = rs.getFloat("highTemperature");
                lTemp = rs.getFloat("lowTemperature");
            }
            t = new Temperature(cTemp, hTemp, lTemp);
        } catch (SQLException ex) {
            Logger.getLogger(TemperatureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public Object delete(int id) {
        Temperature t = null;
        String sql = "DELETE t\n"
                + " FROM forecast.temperature t\n"
                + " WHERE t.idTemperature = " + id;
        t = (Temperature) select(id);
        executeUp(sql, connect.getConnection());
        return t;
    }
}
