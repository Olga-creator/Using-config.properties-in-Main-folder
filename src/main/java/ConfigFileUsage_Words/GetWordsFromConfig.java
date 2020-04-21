package ConfigFileUsage_Words;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetWordsFromConfig {
    String myResult = "";
    InputStream input;

    public String getWord(String word) throws IOException {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            input = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (input != null) {
                prop.load(input);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
//            Date time = new Date(System.currentTimeMillis());
            // get the property value and return it
            String myResult = prop.getProperty(word);
            input.close();
        return myResult;
    }
}
