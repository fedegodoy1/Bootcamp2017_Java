/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author federico
 */
public class Config {
    
    public Properties load(){
        
    Properties prop = new Properties();
    InputStream input = null;

	try {
            input = new FileInputStream("C:/Users/federico/OneDrive/Documents/Bootcamp Globant/Bootcamp2017_Java/Bootcamp2017/target/classes/my_dbConfig.properties.txt");

            // load a properties file
            prop.load(input);
            
            // get the property value and print it out
            System.out.println(prop.getProperty("DATABASE_DRIVER"));
            System.out.println(prop.getProperty("USERNAME"));
            System.out.println(prop.getProperty("PASSWORD"));

	} catch (IOException ex) {
		ex.printStackTrace();
	} finally {
		if (input != null) {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
        return prop;
    }
}
