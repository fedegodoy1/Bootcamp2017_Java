/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package H2;

import Main.Atmosphere;
import MySql.MySqlConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author federico
 */
public class H2DataBaseTest {
    
    public H2DataBaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class H2DataBase.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Atmosphere a = H2DataBase.insert();
        Atmosphere aSql = null;
        String sqlMySql="SELECT a.* \n" +
                      "FROM forecast.atmosphere a \n" +
                      "WHERE a.idAtmosphere = 1";
        Connection connectMySql = MySqlConnect.getConnection();
        Statement stmtMySql = connectMySql.createStatement();
        ResultSet rsSql = stmtMySql.executeQuery(sqlMySql);
        float hum = 0;
        float pre = 0;
        float vis = 0;
        System.out.println("Dias almacenados en MySql: ");
        while(rsSql.next()){
            hum = rsSql.getFloat("humidity");
            pre = rsSql.getFloat("pressure");
            vis = rsSql.getFloat("visibility");
        }
        
        aSql = new Atmosphere(hum,pre,vis);
        if (!aSql.equals(a)) {
            fail("The test case is a prototype.");
        }
        
    }
    
}
