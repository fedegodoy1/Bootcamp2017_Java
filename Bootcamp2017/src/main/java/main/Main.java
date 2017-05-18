package main;



import builder.*;
import dao.*;
import mysql.MySqlConnect;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import mysql.LastId;

/**
 *
 * @author federico
 */
public class Main {
    public static void main(String args[]){
        Forecast list = new Forecast();
        
        Location l1 = new LocationBuilder().withCity("Córdoba").withCountry("Argentina").withRegion("Latam").build();
        Atmosphere a1 = new AtmosphereBuilder().withHumidity(23).withPressure(7).withVisibility(15).build();
        Wind w1 = new WindBuilder().withDirection("S").withSpeed(15).build();
        Temperature t1 = new TemperatureBuilder().withCTemp(11).withHTemp(13).withLTemp(5).build();
        Day d1 = new DayBuilder().withName("Domingo").withDate("14/05").withDescription("Sunny").witLocation(l1).withTemperature(t1).withAtmosphere(a1).withWind(w1).build();
        
        //--------------------------
        Location l2 = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
        Atmosphere a2 = new AtmosphereBuilder().withHumidity(55).withPressure(3).withVisibility(13).build();
        Wind w2 = new WindBuilder().withDirection("N").withSpeed(26).build();
        Temperature t2 = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
        Day d2 = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l2).withAtmosphere(a2).withTemperature(t2).withWind(w2).build();
        
        list.addFirst( d2 );
        list.addFirst( d1 );
        
        System.out.println("\nEstado del clima: "+list.toString());
                
        for (int i = 0; i < list.size(); i++) {
            Day insert = (Day) list.get(i);
            
            WeatherDAO atdao = new AtmosphereDAO();
            atdao.insert(insert.getAtmosphere());
            
            WeatherDAO locdao = new LocationDAO();
            locdao.insert(insert.getLocation());
            
            WeatherDAO temdao = new TemperatureDAO();
            temdao.insert(insert.getTemp());
            
            WeatherDAO wdao = new WindDAO();
            wdao.insert(insert.getWind());
            
            WeatherDAO daydao = new DayDAO();
            daydao.insert(insert);
            
        }
        
        System.out.println("Dias almacenados en base de datos: ");
        WeatherDAO dao = new DayDAO();
        Connection con = MySqlConnect.getConnection();
        Day d;
        String cadena = "";
        for (int i = 0; i < Integer.parseInt(LastId.buscarUltimoId(con,"day")); i++) {
            d = (Day)dao.select(i);
            cadena+=d.toString()+"\n";
        }
        System.out.println(cadena);
    }
}

