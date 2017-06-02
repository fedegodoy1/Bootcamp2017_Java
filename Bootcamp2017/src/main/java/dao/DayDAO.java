package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import domain.Atmosphere;
import domain.Day;
import domain.Location;
import domain.Temperature;
import domain.Wind;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mysql.MySqlConnect;
import mysql.LastId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author federico
 */
@Repository
public class DayDAO extends BaseWeatherDAO implements WeatherDAO<Day>, CountOffDAO {

    private MySqlConnect connect;

    @Autowired
    AtmosphereDAO atDao;
    @Autowired
    LocationDAO locDao;
    @Autowired
    TemperatureDAO tempDao;
    @Autowired
    WindDAO windDao;

    public void insert(Day o) {
        Day d = o;
        atDao.insert(d.getAtmosphere());
        locDao.insert(d.getLocation());
        tempDao.insert(d.getTemp());
        windDao.insert(d.getWind());

        int idAt = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(), "atmosphere"));
        int idLoc = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(), "location"));
        int idTemp = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(), "temperature"));
        int idW = Integer.parseInt(LastId.buscarUltimoId(connect.getConnection(), "wind"));
        if (idAt != 0 && idLoc != 0 && idTemp != 0 && idW != 0) {
            idAt--;
            idLoc--;
            idTemp--;
            idW--;
        }
        String sql = "insert into forecast.day(day.idDay, day.name, day.date, day.description,day.idAtmosphere,day.idLocation,day.idTemperature,day.idWind) \n"
                + "values (" + LastId.buscarUltimoId(connect.getConnection(), "day") + ",'" + d.getName() + "','" + d.getDate() + "','" + d.getDescription() + "'," + idAt + "," + idLoc + "," + idTemp + "," + idW + ")";
        executeUp(sql, connect.getConnection());
    }

    public Day update(int id, Day o) {
        Day d = o;
        int idAt = 0, idLoc = 0, idTemp = 0, idWind = 0;
        int[] vec = idsComponentsDay(d.getDate(), d.getLocation().getCity(), d.getLocation().getCountry()); //0:idLoc 1:idAt 2:idTemp 3:idWind
        if (d.getAtmosphere() == null) {
            idTemp = vec[2];
            tempDao.update(idTemp, d.getTemp());
            String sql = "update forecast.day "
                    + "set day.name='" + d.getName() + "', day.description='" + d.getDescription() + "'"
                    + " where day.idDay=" + id;
            executeUp(sql, connect.getConnection());
        } else {
            idLoc = vec[0];
            idAt = vec[1];
            idTemp = vec[2];
            idWind = vec[3];

            atDao.update(idAt, d.getAtmosphere());
            locDao.update(idLoc, d.getLocation());
            tempDao.update(idTemp, d.getTemp());
            windDao.update(idWind, d.getWind());
            String sql = "update forecast.day "
                    + "set day.name='" + d.getName() + "', day.description='" + d.getDescription() + "'"
                    + " where day.idDay=" + id;
            executeUp(sql, connect.getConnection());
        }
        return d;
    }

    public ArrayList<Day> selectAllDaysForALocation(String location, String country) {
        ArrayList<Day> list = new ArrayList();
        Day d = null;
        String sqlComplete = "SELECT * FROM forecast.day d,forecast.location l, forecast.temperature t, forecast.wind w, forecast.atmosphere a \n"
                + "WHERE l.idLocation=d.idLocation and t.idTemperature=d.idTemperature and w.idWind=d.idWind and a.idAtmosphere=d.idAtmosphere and l.city='" + location + "' and l.country='" + country + "';";
        ResultSet rs = executeQ(sqlComplete, connect.getConnection());
        try {
            
            float hum = 0;
            float pres = 0;
            float vis = 0;
            
            String region = "";
            
            float cTemp = 0;
            float hTemp = 0;
            float lTemp = 0;
            
            String dir = "";
            float spd = 0;
            
            String name = "";
            String date = "";
            String descr = "";
            
            while(rs.next()){
                hum = rs.getFloat("humidity");
                pres = rs.getFloat("pressure");
                vis = rs.getFloat("visibility");
                Atmosphere a = new Atmosphere(hum, pres, vis);
                
                region = rs.getString("region");
                Location l = new Location(location, country, region);
                
                cTemp = rs.getFloat("currentTemperature");
                hTemp = rs.getFloat("highTemperature");
                lTemp = rs.getFloat("lowTemperature");
                Temperature t = new Temperature(cTemp, hTemp, lTemp);
                
                dir = rs.getString("direction");
                spd = rs.getFloat("speed");
                Wind w = new Wind(dir, spd);
                
                name = rs.getString("name");
                date = rs.getString("date");
                descr = rs.getString("description");
                
                d = new Day(name, date, descr, l, a, w, t);
                list.add(d);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Day select(int id) {
        Day d = null;
        String sqlAt = "SELECT a.* "
                + "FROM forecast.atmosphere a, forecast.day d "
                + "WHERE d.idDay =" + id + " and d.idAtmosphere = a.idAtmosphere";
        ResultSet rs = executeQ(sqlAt, connect.getConnection());
        try {
            float hum = 0;
            float pres = 0;
            float vis = 0;
            while (rs.next()) {
                hum = rs.getFloat("humidity");
                pres = rs.getFloat("pressure");
                vis = rs.getFloat("visibility");
            }
            Atmosphere a = new Atmosphere(hum, pres, vis);

            String sqlLoc = "SELECT l.* "
                    + "FROM forecast.location l, forecast.day d "
                    + "WHERE d.idDay =" + id + " and d.idLocation = l.idLocation";
            rs = executeQ(sqlLoc, connect.getConnection());
            String city = "";
            String country = "";
            String region = "";
            while (rs.next()) {
                city = rs.getString("city");
                country = rs.getString("country");
                region = rs.getString("region");
                
            }
            Location l = new Location(city, country, region);

            String sqlTemp = "SELECT t.* "
                    + "FROM forecast.temperature t, forecast.day d "
                    + "WHERE d.idDay =" + id + " and d.idTemperature = t.idTemperature";
            rs = executeQ(sqlTemp, connect.getConnection());
            float cTemp = 0;
            float hTemp = 0;
            float lTemp = 0;
            while (rs.next()) {
                cTemp = rs.getFloat("currentTemperature");
                hTemp = rs.getFloat("highTemperature");
                lTemp = rs.getFloat("lowTemperature");
            }
            Temperature t = new Temperature(cTemp, hTemp, lTemp);

            String sqlWind = "SELECT w.* "
                    + "FROM forecast.wind w, forecast.day d "
                    + "WHERE d.idDay =" + id + " and d.idWind = w.idWind";
            rs = executeQ(sqlWind, connect.getConnection());
            String dir = "";
            float spd = 0;
            while (rs.next()) {
                dir = rs.getString("direction");
                spd = rs.getFloat("speed");
            }
            Wind w = new Wind(dir, spd);

            String sql = "SELECT d.* \n"
                    + "FROM forecast.day d\n"
                    + "WHERE d.idDay = " + id;
            rs = executeQ(sql, connect.getConnection());
            String name = "";
            String date = "";
            String descr = "";
            while (rs.next()) {
                name = rs.getString("name");
                date = rs.getString("date");
                descr = rs.getString("description");
            }
            d = new Day(name, date, descr, l, a, w, t);
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public Day delete(int id) {
        Day d = null;
        String sql = "DELETE d,a,l,t,w\n"
                + "FROM forecast.day d INNER JOIN forecast.atmosphere a INNER JOIN forecast.location l INNER JOIN forecast.temperature t INNER JOIN forecast.wind w\n"
                + "WHERE d.idDay = " + id + " and d.idAtmosphere = a.idAtmosphere and d.idLocation = l.idLocation and d.idWind = w.idWind and d.idTemperature = t.idTemperature";
        d = select(id);
        executeUp(sql, connect.getConnection());
        return d;
    }

    public int count() {
        int r = 0;
        String sql = "SELECT COUNT(d.idDay) as Cantidad FROM forecast.day d";
        ResultSet rs = executeQ(sql, connect.getConnection());
        try {
            while (rs.next()) {
                r = rs.getInt("Cantidad");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }

    public int[] idsComponentsDay(String dateDay, String location, String country) {
        int[] vec = new int[4];
        String sql = "select d.*\n"
                + "from forecast.day d, forecast.location l \n"
                + "where d.date = '" + dateDay + "' and l.idLocation=d.idLocation and l.city='" + location + "' and l.country='" + country + "'";
        ResultSet rs = executeQ(sql, connect.getConnection());
        try {
            while (rs.next()) {
                vec[0] = rs.getInt("idLocation");
                vec[1] = rs.getInt("idAtmosphere");
                vec[2] = rs.getInt("idTemperature");
                vec[3] = rs.getInt("idWind");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vec;
    }

    public int selectByDateIdAndLocation(String dateDay, String location, String country) {
        String sql = "SELECT d.*, l.* \n"
                + "FROM forecast.day d, forecast.location l \n"
                + "WHERE d.date = '" + dateDay + "' and l.idLocation=d.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
        int id = 0;
        ResultSet rs = executeQ(sql, connect.getConnection());
        try {
            while (rs.next()) {
                id = rs.getInt("idDay");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public Day selectByDateAndLocation(String dateDay, String location, String country) {
        Day d = null;

        String sql = "SELECT d.*, l.* \n"
                + "FROM forecast.day d, forecast.location l\n"
                + "WHERE d.date = '" + dateDay + "' and l.idLocation=d.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
        ResultSet rs = executeQ(sql, connect.getConnection());
        try {
            if (!rs.next()) {
                return d = null;
            } else {
                try {
                    String name = "";
                    String date = "";
                    String descr = "";
                    do {
                        name = rs.getString("name");
                        date = rs.getString("date");
                        descr = rs.getString("description");
                    } while (rs.next());

                    String sqlLoc = "SELECT l.* "
                            + "FROM forecast.location l, forecast.day d "
                            + "WHERE d.date ='" + dateDay + "' and d.idLocation = l.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
                    rs = executeQ(sqlLoc, connect.getConnection());
                    String region = "";
                    while (rs.next()) {
                        region = rs.getString("region");
                    }
                    Location l = new Location(location, country, region);

                    String sqlTemp = "SELECT t.*\n"
                            + "FROM forecast.temperature t, forecast.day d, forecast.location l\n"
                            + "WHERE d.date ='" + dateDay + "' and t.idTemperature=d.idTemperature and d.idLocation = l.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
                    rs = executeQ(sqlTemp, connect.getConnection());
                    float cTemp = 0;
                    float hTemp = 0;
                    float lTemp = 0;
                    while (rs.next()) {
                        cTemp = rs.getFloat("currentTemperature");
                        hTemp = rs.getFloat("highTemperature");
                        lTemp = rs.getFloat("lowTemperature");
                    }
                    Temperature t = new Temperature(cTemp, hTemp, lTemp);

                    String sqlWind = "SELECT w.* \n"
                            + "FROM forecast.wind w, forecast.day d, forecast.location l \n"
                            + "WHERE d.date ='" + dateDay + "' and w.idWind = d.idWind and l.idLocation=d.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
                    rs = executeQ(sqlWind, connect.getConnection());
                    String dir = "";
                    float spd = 0;
                    while (rs.next()) {
                        dir = rs.getString("direction");
                        spd = rs.getFloat("speed");
                    }
                    Wind w = new Wind(dir, spd);

                    String sqlAt = "SELECT a.* \n"
                            + "FROM forecast.atmosphere a, forecast.day d, forecast.location l \n"
                            + "WHERE d.date ='" + dateDay + "' and a.idAtmosphere=d.idAtmosphere and l.idLocation=d.idLocation and l.city = '" + location + "' and l.country='" + country + "'";
                    rs = executeQ(sqlAt, connect.getConnection());
                    float hum = 0;
                    float pres = 0;
                    float vis = 0;
                    while (rs.next()) {
                        hum = rs.getFloat("humidity");
                        pres = rs.getFloat("pressure");
                        vis = rs.getFloat("visibility");
                    }
                    Atmosphere a = new Atmosphere(hum, pres, vis);

                    d = new Day(name, date, descr, l, a, w, t);

                } catch (SQLException ex) {
                    Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public Day lastUpdateForLocation(String location, String country) throws SQLException {
        Day d = null;
        String dateInputPattern = "dd MMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateInputPattern);

        Date dateM = null;
        Date dateDB = null;

        String date = "";

        String sql = "select d.*,l.* from forecast.day d, forecast.location l \n"
                + "where l.idLocation = d.idLocation and l.city='" + location + "' and l.country='" + country + "'";
        ResultSet rs = executeQ(sql, connect.getConnection());
        rs.last();
        int rows = rs.getRow();
        if (rows == 1) {
            rs.beforeFirst();
            try {
                dateM = sdf.parse("01 jun 2000");
                do {
                    if (rs.next()) {
                        date = rs.getString("date");
                        dateDB = sdf.parse(date);
                        if (dateDB.after(dateM) == true) {
                            dateM = dateDB;
                        }
                    }
                } while (rs.next());
            } catch (SQLException | ParseException ex) {
                Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            if (rows == 0) {
                return d;
            } else {
                rs.beforeFirst();
                try {
                    dateM = sdf.parse("01 jun 2000");
                    while (rs.next()) {
                        if (rs.next()) {
                            date = rs.getString("date");
                            dateDB = sdf.parse(date);
                            if (dateDB.after(dateM) == true) {
                                dateM = dateDB;
                            }
                        }
                    }
                } catch (SQLException | ParseException ex) {
                    Logger.getLogger(DayDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        d = selectByDateAndLocation(sdf.format(dateM), location, country);
        return d;
    }
}
