/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DayDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.Day;
import mysql.LastId;
import mysql.MySqlConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author federico
 */
@RestController
public class WeatherController {

    @Autowired
    DayDAO dayDao;

    @RequestMapping(value = "/day/", method = RequestMethod.GET, headers = "Accept=application/json")
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

    @RequestMapping(value = "/day/{idday}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ArrayList<Day> dayName(@PathVariable("idday") String idday) {
        ArrayList<Day> name = new ArrayList<Day>();
        Day d = dayDao.select(Integer.parseInt(idday));
        name.add(d);
        return name;
    }
}
