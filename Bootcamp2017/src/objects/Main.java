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
        list.addFirst( new Day("Jueves","27/04","Despejado","24°","10°","") );
        list.addFirst( new Day("Martes","25/04","Nuboso","23°","12°","") );
        list.addFirst( new Day("Lunes","24/04","Lluvioso","22°","14°","16°") );
        
        list.add(2, new Day("Miercoles","26/04","Nuboso","25°","11°","") );
        
        System.out.println("\nEstado del clima: "+list.toString());
        
        String a ="tuvieja";
        String b ="tuvieja";
        if(a.equals(b))
        {
            System.out.println("Coinciden");
        }
        else{
            System.out.println("no coinciden");
        }
        Day sustituido = list.set(0, new Day("Lunes","24/04","Despejado","26°","15°","19°"));
        System.out.println("Estado del clima actualizado: "+list.toString());
        
        Day removed = list.remove(3);
        System.out.println("Dia removido: "+removed.toString());
        
        System.out.println("Estado del clima actualizado: "+list.toString());
        
        Day searched = list.search("Lunes");
        if(searched == null){
            System.out.println("No hay coincidencia");
        }
        else{
            System.out.println("Dia encontrado!");
        }
    }
}
