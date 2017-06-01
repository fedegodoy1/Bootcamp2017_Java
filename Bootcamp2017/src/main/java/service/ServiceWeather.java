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
//    @Autowired
//    private WeatherTransformer wt;
//    @Autowired
//    private AdapterMilesToKilometers a;
//    @Resource(name = "clientYahooWeather")
////    @Autowired
////    @Qualifier(value = "ProxyWeather")
//    ClientYahooWeather clientYahooWeather;
    @Autowired
    YahooObject adapterYahooResponseToDay;

    public Day requestForecastCurrentDay(String location, String country) {
        boolean ok =Validations.checkInet();
        Day d = null;
        if (ok) {
            d = adapterYahooResponseToDay.requestForecastCurrentDay(location, country);
            if (d.getName().equals("Location and country incorrects")) {
                return d;
            } 
            else {
                insertDay(d);
            }
        } 
        else {
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
        Day dayDB = dayDao.selectByDateAndLocation(d.getDate(),d.getLocation().getCity(),d.getLocation().getCountry());
        int id = 0;
        if (dayDB != null) {
            id = dayDao.selectByDateIdAndLocation(dayDB.getDate(),d.getLocation().getCity(),d.getLocation().getCountry());
            dayDao.update(id, d);
        } else {
            dayDao.insert(d);
        }
    }

    public ArrayList<Day> requestAllDays() {
        ArrayList<Day> days = new ArrayList();
        Day d = null;
        int cantDias = dayDao.count();
        for (int i = 0; i < cantDias; i++) {
            d = dayDao.select(i);
            days.add(d);
        }
        return days;
    }

    public ArrayList<Day> requestDay(String idday) {
        ArrayList<Day> name = new ArrayList<Day>();
        Day d = dayDao.select(Integer.parseInt(idday));
        name.add(d);
        return name;
    }

    public ArrayList<Day> requestAddDay(Day d) {
        ArrayList<Day> r = new ArrayList<Day>();
        atDAO.insert(d.getAtmosphere());
        tempDAO.insert(d.getTemp());
        locDAO.insert(d.getLocation());
        windDAO.insert(d.getWind());
        dayDao.insert(d);
        r.add(d);
        return r;
    }

    public ArrayList<Day> requestUpdateDay(Day d) {
        ArrayList<Day> r = new ArrayList<Day>();
        int vec[] = dayDao.idsComponentsDay(d.getDate(),d.getLocation().getCity(),d.getLocation().getCountry()); //0:idLoc 1:idAt 2:idTemp 3:idWind
        locDAO.update(vec[0], d.getLocation());
        atDAO.update(vec[1], d.getAtmosphere());
        tempDAO.update(vec[2], d.getTemp());
        windDAO.update(vec[3], d.getWind());
        dayDao.update(dayDao.selectByDateIdAndLocation(d.getDate(),d.getLocation().getCity(),d.getLocation().getCountry()), d);
        r.add(d);
        return r;
    }
}
