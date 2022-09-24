package com.spring.selenium.config;

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Component
public class fileProperties {

    public Properties prop;

    public fileProperties() {

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/application.properties");
            FileInputStream ip2 = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/data.properties");
            prop.load(ip);
            prop.load(ip2);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }


    }
}
