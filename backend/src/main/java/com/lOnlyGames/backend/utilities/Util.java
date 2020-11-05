package com.lOnlyGames.backend.utilities;

import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {



    /*
    This class is used to fetch data from the application.properties file.
     */
    public static Properties fetchProperties(){
        Properties properties = new Properties();
        try {
            File file = ResourceUtils.getFile("classpath:application.properties");
            InputStream in = new FileInputStream(file);
            properties.load(in);
        } catch (IOException e) {
              e.printStackTrace();
        }

        //Return everything in the app.properties file.
        return properties;
    }

}
