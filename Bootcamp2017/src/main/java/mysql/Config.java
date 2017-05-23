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
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
/**
 *
 * @author federico
 */
@Configuration
@PropertySource("file:C:/Users/federico/OneDrive/Documents/Bootcamp Globant/Bootcamp2017_Java/Bootcamp2017/target/classes/dbConfig.properties")
public class Config {
    @Autowired
    Environment env;
    
    @Bean(name="dbConfig")
    public MySqlConnect getDbConfig(){
        MySqlConnect dbConf = new MySqlConnect();
        dbConf.setDATABASE_DRIVER(env.getProperty("DATABASE_DRIVER"));
        dbConf.setDATABASE_URL(env.getProperty("DATABASE_URL"));
        dbConf.setPASSWORD(env.getProperty("PASSWORD"));
        dbConf.setUSERNAME("root");
        return dbConf;
    }
}
