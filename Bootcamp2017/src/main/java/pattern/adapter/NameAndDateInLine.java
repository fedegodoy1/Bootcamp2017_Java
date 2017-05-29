/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.adapter;

/**
 *
 * @author federico
 */
public class NameAndDateInLine implements NameAndDate{
    private String line;

    public void setLine(String line) {
        this.line = line;
    }
    
    @Override
    public String getLine(){
        return line;
    }
}
