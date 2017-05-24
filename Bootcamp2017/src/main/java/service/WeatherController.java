/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.AtmosphereDAO;
import dao.BaseWeatherDAO;
import dao.DayDAO;
import dao.LocationDAO;
import dao.TemperatureDAO;
import dao.WindDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import main.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author federico
 */
@RestController("MyApp")
public class WeatherController {

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

    @RequestMapping(value = "/days/", method = RequestMethod.GET)
    public ArrayList<Day> allDays() {
        ArrayList<Day> days = new ArrayList<Day>();
        Day d = null;
        int cantDias = dayDao.count();
        for (int i = 0; i < cantDias; i++) {
            d = dayDao.select(i);
            days.add(d);
        }
        return days;
    }

    @RequestMapping(value = "/day/{idday}", method = RequestMethod.GET)
    public ArrayList<Day> day(@PathVariable("idday") String idday) {
        ArrayList<Day> name = new ArrayList<Day>();
        Day d = dayDao.select(Integer.parseInt(idday));
        name.add(d);
        return name;
    }
    
    @RequestMapping(value="/insertday", method = RequestMethod.POST)
    public ArrayList<Day> addDay(@RequestBody Day d){
        ArrayList<Day> r = new ArrayList<Day>();
        atDAO.insert(d.getAtmosphere());
        tempDAO.insert(d.getTemp());
        locDAO.insert(d.getLocation());
        windDAO.insert(d.getWind());
        dayDao.insert(d);
        r.add(d);
        return r;
    }
    
    @RequestMapping(value="/updateday", method = RequestMethod.PUT)
    public ArrayList<Day> updateDay(@RequestBody Day d){
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
