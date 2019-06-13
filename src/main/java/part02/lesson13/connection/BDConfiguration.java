package part02.lesson13.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Information for connection to bd
 * @author folkland
 */
public class BDConfiguration {

    private String dburl;
    private String username;
    private String password;

    public BDConfiguration() {
        String filePath = "config/bdconnection.property";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath)) {
            Properties properties = new Properties();
            if (inputStream == null) {
                throw new FileNotFoundException(filePath + " property file not found");
            }
            properties.load(inputStream);
            dburl = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDburl() {
        return dburl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
