/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author federico
 */
public class Main {
    public static void main(String args[]){
        Forecast list = new Forecast();
        //String name, String date, String description, Location location, Atmosphere atmosphere, Wind wind, Temperature temp
        Location l1=new Location("Córdoba","Argentina","Latam");
        
        float humidity = 23;
        float presure = 7;
        float visibility = 15;
        Atmosphere a1 = new Atmosphere(humidity,presure,visibility);
        
        String direction = "SE";
        float speed = 25;
        Wind w1 = new Wind(direction, speed);
        
        float cTemp = 15;
        float hTemp = 16;
        float lTemp = 10;
        Temperature t1 = new Temperature(cTemp,hTemp,lTemp);
        Day day1 = new Day("Martes","09/05","Sunny",l1,a1,w1,t1);
        
        //--------------------------
        
        Location l2=new Location("Córdoba","Argentina","Latam");
        
        float humidity2 = 55;
        float presure2 = 3;
        float visibility2 = 13;
        Atmosphere a2 = new Atmosphere(humidity2,presure2,visibility2);
        
        String direction2 = "N";
        float speed2 = 26;
        Wind w2 = new Wind(direction2, speed2);
        
        float cTemp2 = 25;
        float hTemp2 = 27;
        float lTemp2 = 20;
        Temperature t2 = new Temperature(cTemp2,hTemp2,lTemp2);
        Day day2= new Day("Miercoles","10/05","Cloudy",l2,a2,w2,t2);
        
        list.addFirst( day2 );
        list.addFirst( day1 );
        
//        list.add(1, new Day("Miercoles","03/05","Nuboso",l2,a2,w2,t2) );
        System.out.println("\nEstado del clima: "+list.toString());
//        
//        float humidity3 = 10;
//        float presure3 = 3;
//        float visibility3 = 25;
//        Atmosphere a3 = new Atmosphere(humidity3,presure3,visibility3);
//        
//        String direction3 = "N";
//        float speed3 = 5;
//        Wind w3 = new Wind(direction3, speed3);
        
//        float cTemp3 = 15;
//        float hTemp3 = 20;
//        float lTemp3 = 10;
//        Temperature t3 = new Temperature(cTemp3,hTemp3,lTemp3);
//        Day sustituido = new Day("Martes","02/05","Sunny",l2,a3,w3,t3);
//        list.set(0, sustituido);
//        
//        System.out.println("Estado del clima actualizado: "+list.toString());
        
//        Day removed = list.remove(2);
//        System.out.println("------------------------------");
//        System.out.println("Dia removido: "+removed.toString());
//        System.out.println("------------------------------");
//        System.out.println("Estado del clima actualizado: "+list.toString());
        
        System.out.println("------------------------------");
        System.out.println("Dia a buscar Martes");
        Day searched = list.search("Martes");
        if(searched == null){
            System.out.println("- No hay coincidencia");
        }
        else{
            System.out.println("- Dia encontrado!");
        }
        
        System.out.println("Dia a buscar Lunes");
        Day searched2 = list.search("Domingo");
        if(searched2 == null){
            System.out.println("- No hay coincidencia");
        }
        else{
            System.out.println("- Dia encontrado!");
        }
        
        MySqlConnect mysql = new MySqlConnect();
//        System.out.println("--------------------------");
//        System.out.println("Connecting database...");
//        try (Connection connection = mysql.connect()) {
//            System.out.println("Database connected!");
//            
//        } catch (SQLException e) {
//            throw new IllegalStateException("Cannot connect the database!", e);
//        }
        
        for (int i = 0; i < list.size(); i++) {
            Day insert = (Day) list.get(i);
            
            String sqlatmosphere = "INSERT INTO "
                    + "forecast.atmosphere(atmosphere.idAtmosphere,atmosphere.humidity,atmosphere.pressure,atmosphere.visibility) "
                    + "values ("+ buscarUltimoId(mysql,"atmosphere") +","+ insert.getAtmosphere().getHumididy() +","+ insert.getAtmosphere().getPressure() + ", "+ insert.getAtmosphere().getVisibility()+")";
            try {
                
                PreparedStatement statement = mysql.connect().prepareStatement(sqlatmosphere);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String sqlLocation = "insert into forecast.location(location.idLocation, location.city, location.country,location.region)" 
                    +"values("+ buscarUltimoId(mysql,"location") +",'"+ insert.getLocation().getCity() +"', '"+ insert.getLocation().getCountry() +"','"+ insert.getLocation().getRegion() +"')";
            try {
                PreparedStatement statement = mysql.connect().prepareStatement(sqlLocation);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String sqlWind ="insert into forecast.wind(wind.idWind, wind.direction, wind.speed) "
                    + "values("+ buscarUltimoId(mysql,"wind") +",'"+ insert.getWind().getDirection()+"',"+ insert.getWind().getSpeed()+")";
            try {
                PreparedStatement statement = mysql.connect().prepareStatement(sqlWind);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String sqlTemp = "insert into forecast.temperature(temperature.idTemperature, temperature.currentTemperature, temperature.highTemperature, temperature.lowTemperature) "
                    + "values("+ buscarUltimoId(mysql,"temperature") +","+ insert.getTemp().getCurrentTemperature() +","+insert.getTemp().getHighTemperature() +","+ insert.getTemp().getLowTemperature()+")";
            try {
                PreparedStatement statement = mysql.connect().prepareStatement(sqlTemp);
                statement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String sqlDay = "insert into forecast.day(day.idDay, day.name, day.date, day.description,day.idAtmosphere,day.idLocation,day.idTemperature,day.idWind) "
                    + "values ("+ buscarUltimoId(mysql,"day") +",'"+insert.getName()+"','"+insert.getDate()+"','"+ insert.getDescription()+"',"+ i +","+ i +","+ i +","+ i +")";
            try {
                PreparedStatement statement = mysql.connect().prepareStatement(sqlDay);
                statement.executeUpdate();
                System.out.println("Dia "+insert.getName()+" almacenado");
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Dias almacenados en base de datos: ");
        try {
            Statement stObtener = mysql.connect().createStatement();
            ResultSet rs = stObtener.executeQuery("SELECT d.name,d.date,d.description,a.humidity,l.city,t.currentTemperature \n" +
                                                  "FROM forecast.day d, forecast.atmosphere a, forecast.location l, forecast.temperature t\n" +
                                                  "WHERE d.idLocation = l.idLocation and d.idTemperature = t.idTemperature and d.idAtmosphere=a.idAtmosphere");
            while(rs.next()){
                String name = rs.getString("name");
                String date = rs.getString("date");
                float humid = rs.getFloat("humidity");
                String city = rs.getString("city");
                float temp = rs.getFloat("currentTemperature");
                
                System.out.println("Dia "+name+" "+date+", humidity: "+humid+", city: "+city+", current temperature: "+temp);
            }
            rs.close();
            stObtener.close();
            mysql.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static String buscarUltimoId(MySqlConnect conexion,String tipo){
        String ultimo = "";
        Statement stObtener;
        
        switch(tipo){
            case "atmosphere":
                try {
                    stObtener = conexion.connect().createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(atmospehre.idAtmosphere) as Cantidad " +
                                                          "FROM forecast.atmosphere");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case "location":
                try {
                    stObtener = conexion.connect().createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(location.idLocation) as Cantidad " +
                                                          "FROM forecast.location");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "temperature":
                try {
                    stObtener = conexion.connect().createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(temperature.idTemperature) as Cantidad " +
                                                          "FROM forecast.temperature");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "wind":
                try {
                    stObtener = conexion.connect().createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(wind.idWind) as Cantidad " +
                                                          "FROM forecast.wind");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "day":
                try {
                    stObtener = conexion.connect().createStatement();
                    ResultSet rs = stObtener.executeQuery("SELECT COUNT(day.idDay) as Cantidad " +
                                                          "FROM forecast.day");
                    while(rs.next()){
                        ultimo = ""+rs.getString("Cantidad");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        
        return ultimo;
    }
}

