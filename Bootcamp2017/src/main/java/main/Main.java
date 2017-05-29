package main;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author federico
 */
@Component
public class Main {

    public static void main(String args[]) {
//        String confFile = "file:src/main/webapp/WEB-INF/applicationContext.xml";
//        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(confFile);
//        Main p = context.getBean(Main.class);
//        p.start(args);
    }
    
//    @Autowired
//    private AtmosphereDAO atdao;
//    @Autowired
//    private DayDAO daydao;
//    @Autowired
//    private LocationDAO locdao;
//    @Autowired
//    private TemperatureDAO tempdao;
//    @Autowired
//    private WindDAO windao;
    
    private void start(String[] args){
//        Forecast list = new Forecast();
//        
//        Location l1 = new LocationBuilder().withCity("Córdoba").withCountry("Argentina").withRegion("Latam").build();
//        Atmosphere a1 = new AtmosphereBuilder().withHumidity(53).withPressure(1).withVisibility(10).build();
//        Wind w1 = new WindBuilder().withDirection("N").withSpeed(5).build();
//        Temperature t1 = new TemperatureBuilder().withCTemp(10).withHTemp(13).withLTemp(5).build();
//        Day d1 = new DayBuilder().withName("Martes").withDate("16/05").withDescription("Cloudy").witLocation(l1).withTemperature(t1).withAtmosphere(a1).withWind(w1).build();
//
//        //--------------------------
////        Location l2 = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
////        Atmosphere a2 = new AtmosphereBuilder().withHumidity(55).withPressure(3).withVisibility(13).build();
////        Wind w2 = new WindBuilder().withDirection("N").withSpeed(26).build();
////        Temperature t2 = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
////        Day d2 = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l2).withAtmosphere(a2).withTemperature(t2).withWind(w2).build();
////        
////        list.addFirst( d2 );
//        list.addFirst(d1);
//        System.out.println("-------------------------------------------");
//        System.out.println("\nEstado del clima: " + list.toString());
//
//        for (int i = 0; i < list.size(); i++) {
//            Day insert = (Day) list.get(i);
//
//            atdao.insert(insert.getAtmosphere());
//
//            locdao.insert(insert.getLocation());
//
//            tempdao.insert(insert.getTemp());
//
//            windao.insert(insert.getWind());
//
//            daydao.insert(insert);
//
//        }
//        System.out.println("-------------------------------------------");
//        System.out.println("\nDias almacenados en base de datos: ");
//        WeatherDAO dao = new DayDAO();
//        Connection con = MySqlConnect.getConnection();
//        Day d;
//        String cadena = "";
//        for (int i = 0; i < Integer.parseInt(LastId.buscarUltimoId(con, "day")); i++) {
//            d = (Day) dao.select(i);
//            cadena += d.toString() + "\n";
//        }
//        System.out.println(cadena);
    }
}
