/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import MySql.MySqlConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class MySqlConnectTest {
    
    /**
     * Test of getConnection method, of class MySqlConnect.
     */
    @Test
    public void testGetConnection() throws SQLException {
        Connection connect = MySqlConnect.getConnection();
        Statement st = connect.createStatement();
        ResultSet rs = st.executeQuery("select 1 from dual");
        if (rs==null) {
            fail();
        }
    }
    
}
