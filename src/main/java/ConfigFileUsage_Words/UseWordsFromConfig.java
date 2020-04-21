package ConfigFileUsage_Words;

import ConfigFileUsage.GetPropertyValues;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.lanit.framework.webdriver.WebDriverManager;
import javax.swing.text.html.CSS;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class UseWordsFromConfig {
    /**
     * author: Olga S
     * Here I tried to imagine how a program for comparing synonyms can function
     * In some cases it could be useful in translation, but it should do more, of course..
     */

    public static void main(String[] args) throws IOException, InterruptedException {
        GetWordsFromConfig properties = new GetWordsFromConfig();
        WebDriver webDriver;
        //get word1 from the file config.properties
        String word1 = properties.getWord("word1");
        //get word2 from the file config.properties
        String word2= properties.getWord("word2");
        System.out.println("Googling the words/phrases \"" + word1 + "\" and \"" + word2 + "\":");
        //open browser
        webDriver = WebDriverManager.getDriver();
        //open website
        webDriver.get("http://google.com");
        webDriver.findElement(By.cssSelector("[title=\"Поиск\"]")).click();
        //locate Search field and input the first word
        webDriver.findElement(By.name("q")).sendKeys(word1);
        //Enter
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //take number of search results (first millions, then thousands, then hundreds)
        String word1_result1 = webDriver.findElement(By.id("result-stats")).getText().split(" ")[2];
        String word1_result2= webDriver.findElement(By.id("result-stats")).getText().split(" ")[3];
        String word1_result3 = webDriver.findElement(By.id("result-stats")).getText().split(" ")[4];
        //find the button Очистить and click it
        webDriver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[2]/div/div[3]/div[1]")).click();
        //click Search field again and input the second word/phrase
        webDriver.findElement(By.name("q")).sendKeys(word2);
        //Enter
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        //take number of search results (first millions, then thousands, then hundreds)
        String word2_result1 = webDriver.findElement(By.id("result-stats")).getText().split(" ")[2];
        String word2_result2= webDriver.findElement(By.id("result-stats")).getText().split(" ")[3];
        String word2_result3 = webDriver.findElement(By.id("result-stats")).getText().split(" ")[4];

        System.out.println("Number of results for the word/phrase \"" + word1 + "\" is: " + word1_result1 + " " + word1_result2 + " " + word1_result3);
        System.out.println("Number of results for the word/phrase \"" + word2 + "\" is: " + word2_result1 + " " + word2_result2 + " " + word2_result3);
        //turn String values of results pieces into one Int value
        int word1Results =  Integer.parseInt(word1_result1+word1_result2+word1_result3);
        int word2Results =  Integer.parseInt(word2_result1+word2_result2+word2_result3);

        Date time = new Date(System.currentTimeMillis());

        if(word1Results > word2Results) {
            System.out.println("At the moment (" + time + ") the word/phrase \"" + word1 + "\" seems to be more popular.\nMaybe You should consider using it in your translation.");
        }
        else {
            System.out.println("At the moment (" + time + ") the word/phrase\"" + word2 + "\" seems to be more popular.\nMaybe You should consider using it in your translation.");
        }
        WebDriverManager.quit();
    }
}

