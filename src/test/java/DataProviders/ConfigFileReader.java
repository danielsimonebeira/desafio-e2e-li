package DataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    private final String propertyFilePath = "configs/Configuration.properties";

    public  ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuratio.properties não encontrado em " + propertyFilePath);
        }
    }

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) {
            return driverPath;

        } else {
            throw new RuntimeException("driverPath não encontrado no arquivo Configuration.properties.");

        }
    }

    public String getApplicationUrl(String url) {
        if (url.equals("urle2e") && url != null) {
            return url = properties.getProperty("urle2e");
        } else {
            throw new RuntimeException("Url não encontrada no arquivo Configuration.properties.");
        }

    }

    public String getAplcationLogin(String login) {
        if (login.equals("loginEmail") && login != null) {
            return login = properties.getProperty("loginEmail");
        } else if (login.equals("loginSenha") && login != null) {
            return login = properties.getProperty("loginSenha");
        } else {
            throw new RuntimeException(" proriedade de login não encontrado no arquivo Configuration.properties.");
        }
    }

}
