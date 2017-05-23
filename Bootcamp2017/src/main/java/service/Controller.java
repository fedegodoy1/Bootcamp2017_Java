/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author federico
 */
@RestController("WeatherAPP")
public class Controller {
    @RequestMapping(value="/dayName", method = RequestMethod.GET)
    public String dayName(){
        return null;
    }
}
