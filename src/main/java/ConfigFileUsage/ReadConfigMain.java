package ConfigFileUsage;

import java.io.IOException;

/**
 * example taken from https://crunchify.com/java-properties-file-how-to-read-config-properties-values-in-java/
 */

public class ReadConfigMain {

    public static void main(String[] args) throws IOException {
        GetPropertyValues properties = new GetPropertyValues();
        properties.getPropValues();
    }
}