package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	static WebDriver driver;
	static String browser;
	static String url;
	
	public static void readConfig() {

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver init(){
		
		readConfig();

		switch (browser.toLowerCase()) {
		case "chrome": 
				driver=WebDriverManager.chromedriver().create();
				break;
		case "firefox":
					driver=WebDriverManager.firefoxdriver().create();
					break;
		default:
						System.out.println("browser type "+browser+ "is invalid");
		}
			
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void tearDown() {
		
		driver.quit();
		driver=null;
	}

	
	
}
