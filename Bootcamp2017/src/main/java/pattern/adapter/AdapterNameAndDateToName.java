/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author federico
 */
public class AdapterNameAndDateToName implements Name{
    @Autowired
    private NameAndDate nad;
    
    @Override
    public String getName(){
        String res = nad.getLine();
        String name = "";
        String b[] = res.split(" ");
        name=b[0];
        return name;
    }
    
    @Override
    public String getDate(){
        String res = nad.getLine();
        String date = "";
        String b[] = res.split(" ");
        for (int i = 1; i < 4; i++) {
            if(i==1){
                date+=b[i];
            }else{
                date+=" "+b[i];
            }
        }
        return date;
    }
}
