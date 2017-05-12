/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import H2.H2DataBase;
import java.sql.Connection;
import java.sql.SQLException;
import org.h2.tools.DeleteDbFiles;

/**
 *
 * @author federico
 */
public class AuxMain {
    public static void main(String args[]) throws SQLException{
        
        H2DataBase.insert();
    }
}
