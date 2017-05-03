/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author federico
 */
public class Main {
    public static void main(String args[]){
        Forecast list = new Forecast();
        //String name, String date, String description, Location location, Atmosphere atmosphere, Wind wind, Temperature temp
        Location l1=new Location("Córdoba","Argentina","Latam");
        
        float humidity = 45;
        float presure = 4;
        float visibility = 15;
        Atmosphere a1 = new Atmosphere(humidity,presure,visibility);
        
        String direction = "NE";
        float speed = 12;
        Wind w1 = new Wind(direction, speed);
        
        float cTemp = 20;
        float hTemp = 23;
        float lTemp = 10;
        Temperature t1 = new Temperature(cTemp,hTemp,lTemp);
        Day day1 = new Day("Martes","02/05","Cloudy",l1,a1,w1,t1);
        
        //--------------------------
        
        Location l2=new Location("Córdoba","Argentina","Latam");
        
        float humidity2 = 60;
        float presure2 = 4;
        float visibility2 = 10;
        Atmosphere a2 = new Atmosphere(humidity2,presure2,visibility2);
        
        String direction2 = "S";
        float speed2 = 16;
        Wind w2 = new Wind(direction2, speed2);
        
        float cTemp2 = 7;
        float hTemp2 = 15;
        float lTemp2 = 4;
        Temperature t2 = new Temperature(cTemp2,hTemp2,lTemp2);
        Day day2= new Day("Jueves","04/05","Sunny",l2,a2,w2,t2);
        
        list.addFirst( day2 );
        list.addFirst( day1 );
        
        list.add(1, new Day("Miercoles","03/05","Nuboso",l2,a2,w2,t2) );
        System.out.println("\nEstado del clima: "+list.toString());
        
        float humidity3 = 10;
        float presure3 = 3;
        float visibility3 = 25;
        Atmosphere a3 = new Atmosphere(humidity3,presure3,visibility3);
        
        String direction3 = "N";
        float speed3 = 5;
        Wind w3 = new Wind(direction3, speed3);
        
        float cTemp3 = 15;
        float hTemp3 = 20;
        float lTemp3 = 10;
        Temperature t3 = new Temperature(cTemp3,hTemp3,lTemp3);
        Day sustituido = new Day("Martes","02/05","Sunny",l2,a3,w3,t3);
        list.set(0, sustituido);
        
        System.out.println("Estado del clima actualizado: "+list.toString());
        
        Day removed = list.remove(2);
        System.out.println("------------------------------");
        System.out.println("Dia removido: "+removed.toString());
        System.out.println("------------------------------");
        System.out.println("Estado del clima actualizado: "+list.toString());
        
        System.out.println("------------------------------");
        System.out.println("Dia a buscar Martes");
        Day searched = list.search("Martes");
        if(searched == null){
            System.out.println("No hay coincidencia");
        }
        else{
            System.out.println("Dia encontrado!");
        }
    }
}

