import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SqlDataSearchAmazon {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "root");

		Statement stm = con.createStatement();
		ResultSet result = stm.executeQuery("select * from eproduct");
		
		while(result.next()) {
			
			String SearchString = result.getString("name");
			System.out.println("the string is  " + SearchString);
			
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			
			WebDriver driver = new ChromeDriver();
			
			driver.get("https://www.amazon.in/");
			//Maximize window
			driver.manage().window().maximize();
					
			//wait for browser to load - implicit
			driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
			
			WebElement Search = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
			Search.click();
			Search.sendKeys(SearchString);
			WebElement Search_Click = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
			Search_Click.click();
			List<WebElement> text=driver.findElements(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']"));
	        List<WebElement> price=driver.findElements(By.xpath("//span[@class='a-price-whole']"));    
	                
	        System.out.println("Search Results "+ text.size());
	        
	        for(int count=0;count<text.size();count++) {	          
	               
	            	System.out.println("Name :" + text.get(count).getText() + " Price:"+ price.get(count).getText() );	        
	        }
				       			
			driver.close();			
		}
		
		con.close();
	}

}
