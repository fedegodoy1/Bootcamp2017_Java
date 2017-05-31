package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AtmosphereDAO;
import dao.DayDAO;
import dao.LocationDAO;
import dao.TemperatureDAO;
import dao.WindDAO;
import domain.Day;
import domain.yahoo.DayYahoo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pattern.adapter.AdapterMilesToKilometers;
import pattern.transformer.WeatherTransformer;

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

    
    public Day searchLastUpdate(){
        Day d = null;
        d = dayDao.lastUpdate();
        return d;
    }
    
    public void insertDay(Day d){
        Day dayDB = dayDao.selectByDate(d.getDate());
        int id=0;
        if(dayDB!=null){
            id = dayDao.selectByDateId(dayDB.getDate());
            dayDao.update(id, d);
        }
        else{
            dayDao.insert(d);
        }
    }
    
    public ArrayList<Day> requestAllDays(){
        ArrayList<Day> days = new ArrayList();
        Day d = null;
        int cantDias = dayDao.count();
        for (int i = 0; i < cantDias; i++) {
            d = dayDao.select(i);
            days.add(d);
        }
        return days;
    }
    
    public ArrayList<Day> requestDay(String idday){
        ArrayList<Day> name = new ArrayList<Day>();
        Day d = dayDao.select(Integer.parseInt(idday));
        name.add(d);
        return name;
    }
    
    public ArrayList<Day> requestAddDay(Day d){
        ArrayList<Day> r = new ArrayList<Day>();
        atDAO.insert(d.getAtmosphere());
        tempDAO.insert(d.getTemp());
        locDAO.insert(d.getLocation());
        windDAO.insert(d.getWind());
        dayDao.insert(d);
        r.add(d);
        return r;
    }
    
    public ArrayList<Day> requestUpdateDay(Day d){
        ArrayList<Day> r = new ArrayList<Day>();
        int vec[] = dayDao.idsComponentsDay(d.getDate()); //0:idLoc 1:idAt 2:idTemp 3:idWind
        locDAO.update(vec[0], d.getLocation());
        atDAO.update(vec[1], d.getAtmosphere());
        tempDAO.update(vec[2], d.getTemp());
        windDAO.update(vec[3], d.getWind());
        dayDao.update(dayDao.selectByDateId(d.getDate()), d);
        r.add(d);
        return r;
    }
}
