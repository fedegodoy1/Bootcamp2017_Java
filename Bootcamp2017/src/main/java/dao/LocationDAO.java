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
import domain.Location;
import mysql.LastId;
import mysql.MySqlConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class LocationDAO extends BaseWeatherDAO implements WeatherDAO<Location> {

    private MySqlConnect connect;

    public void insert(Location o) {
        Location l = o;
        String sql = "insert into forecast.location(location.idLocation, location.city, location.country,location.region)\n"
                    + "values (" + LastId.buscarUltimoId(connect.getConnection(), "location") + ",'" + l.getCity() + "','" + l.getCountry() + "','" + l.getRegion() + "')";
        executeUp(sql, connect.getConnection());
    }

    public Location update(int id, Location o) {
        Location l = o;
        String sql = "update forecast.location"
                    + " set location.city='" + l.getCity() + "', location.country='" + l.getCountry() + "', location.region='" + l.getRegion() + "' "
                    + " where location.idLocation=" + id;
        executeUp(sql, connect.getConnection());
        return l;
    }

    public Location select(int id) {
        Location l = null;
        String city = "";
        String country = "";
        String region = "";
        String sqlLoc = "SELECT l.* "
                + "FROM forecast.location l"
                + "WHERE l.idLocation =" + id;
        ResultSet rs = executeQ(sqlLoc, connect.getConnection());;
        try {
            while (rs.next()) {
                city = rs.getString("city");
                country = rs.getString("country");
                region = rs.getString("region");
            }
            l = new Location(city, country, region);
        } catch (SQLException ex) {
            Logger.getLogger(LocationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

    public Location delete(int id) {
        Location l = null;
        String sql = "DELETE l\n"
                + "FROM forecast.location l\n"
                + "WHERE l.idLocation = " + id;
        executeUp(sql, connect.getConnection());
        l = (Location) select(id);
        return l;
    }

}
