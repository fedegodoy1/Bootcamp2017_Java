/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Day;
import java.util.ArrayList;

/**
 *
 * @author federico
 */
public interface YahooObject {
    Day requestForecastCurrentDay(String location, String country);
    ArrayList<Day> requestForecastAllExtended(String location, String country);
}
