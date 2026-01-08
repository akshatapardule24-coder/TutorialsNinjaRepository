package Base;

import java.io.*;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

import com.tutorialninja.qa.utilities.Utilities;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
	    prop = new Properties();
		File propFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninga\\qa\\config\\config.properties");
		dataProp= new Properties();
		File dataPropFile =new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\com\\qa/testdata\\testdata.properties");
		try {
		FileInputStream fis2 = new FileInputStream(dataPropFile);
		dataProp.load(fis2);
		}catch(Throwable e){
			e.printStackTrace();
		}
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
  public WebDriver intializeBrowserAndPassURL(String bowserName) {

			 if(bowserName.equalsIgnoreCase("chrome")) {
				 driver = new ChromeDriver();
			 }else if(bowserName.equalsIgnoreCase("firefox")) {
				 driver = new FirefoxDriver();
			 }else if(bowserName.equalsIgnoreCase("Edge")) {
				 driver = new EdgeDriver();
			 }

		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.implicit_wait_time));
		  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.page_load_time));
		  driver.get(prop.getProperty("url"));
		  driver.navigate().refresh();
		  return driver;
  }
}
