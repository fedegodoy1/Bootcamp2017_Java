/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MySqlConnectTest {
    
    public MySqlConnectTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws SQLException {
        H2DataBase.insert();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConnection method, of class MySqlConnect.
     */
    @Test
    public void testGetConnection(){
//        System.out.println("getConnection");
//        
//        Connection result = MySqlConnect.getConnection();
//        
//        try {
//            assertTrue(!result.isClosed());
//        } catch (SQLException ex) {
//            fail();
//        }
    }
    
    public void testGetConnectionFail(){
        
    }
    
}
