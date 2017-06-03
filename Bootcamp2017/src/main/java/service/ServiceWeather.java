package service;

import dao.AtmosphereDAO;
import dao.DayDAO;
import dao.LocationDAO;
import dao.TemperatureDAO;
import dao.WindDAO;
import domain.Day;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pattern.proxy.Validations;

/**
 *
 * @author federico
 */
@Component
public class ServiceWeather {

    @Autowired
    DayDAO dayDao;
    @Autowired
    AtmosphereDAO atDAO;
    @Autowired
    LocationDAO locDAO;
    @Autowired
    TemperatureDAO tempDAO;
    @Autowired
    WindDAO windDAO;

    @Autowired
    YahooObject adapterYahooResponseToDay;

    public Day requestForecastCurrentDay(String location, String country) {
        boolean ok = Validations.checkInet();//false;
        Day d = null;
        if (ok) {
            d = adapterYahooResponseToDay.requestForecastCurrentDay(location, country);
            if (d.getName().equals("Location and country incorrects")) {
                return d;
            } else {
                insertDay(d);
            }
        } else {
            d = searchLastUpdate(location, country);
        }
        return d;
    }

    public Day searchLastUpdate(String location, String country) {
        Day d = null;
        try {
            d = dayDao.lastUpdateForLocation(location, country);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public void insertDay(Day d) {
        Day dayDB = dayDao.selectByDateAndLocation(d.getDate(), d.getLocation().getCity(), d.getLocation().getCountry());
        int id = 0;
        if (dayDB != null) {
            id = dayDao.selectByDateIdAndLocation(dayDB.getDate(), d.getLocation().getCity(), d.getLocation().getCountry());
            dayDao.update(id, d);
        } else {
            dayDao.insert(d);
        }
    }

    public ArrayList<Day> requestAllDays(String country, String location) {
        ArrayList<Day> days = new ArrayList();
        boolean ok = Validations.checkInet();
        Day d = null;
        if (ok) {
            days = adapterYahooResponseToDay.requestForecastAllExtended(location, country);
            if (days.get(0).getName().equals("Location and country incorrects")) {
                return days;
            } else {
                for (int i = 0; i < days.size(); i++) {
                    d = days.get(i);
                    insertDay(d);
                }
            }
        } 
        else {
            days = dayDao.selectAllDaysForALocation(location, country);

        }
        return days;
    }

    public ArrayList<Day> requestAddDay(Day d) {
        ArrayList<Day> r = new ArrayList<Day>();
        dayDao.insert(d);
        r.add(d);
        return r;
    }

    public ArrayList<Day> requestUpdateDay(Day d) {
        ArrayList<Day> r = new ArrayList<Day>();
        int vec[] = dayDao.idsComponentsDay(d.getDate(), d.getLocation().getCity(), d.getLocation().getCountry()); //0:idLoc 1:idAt 2:idTemp 3:idWind
        locDAO.update(vec[0], d.getLocation());
        atDAO.update(vec[1], d.getAtmosphere());
        tempDAO.update(vec[2], d.getTemp());
        windDAO.update(vec[3], d.getWind());
        dayDao.update(dayDao.selectByDateIdAndLocation(d.getDate(), d.getLocation().getCity(), d.getLocation().getCountry()), d);
        r.add(d);
        return r;
    }
}
