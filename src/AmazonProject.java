import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
		Search.sendKeys("iphone12");
		WebElement Search_Click = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		Search_Click.click();
		List<WebElement> text = driver.findElements(By.xpath("//span[@class ='a-size-medium a-color-base a-text-normal']"));
		 
		List<WebElement> price = driver.findElements(By.xpath("//span[@class ='a-price-whole']"));
		
		HashMap<String,String> values= new HashMap<String,String>();
		
		System.out.println(text.size());
		for (int i=0;i<=price.size()-1;i++) {
			if (text.get(i).getText().contains("Apple iPhone 12")) {
			
				values.put(text.get(i).getText(), price.get(i).getText());
			}
		}
		
		for(Map.Entry<String, String> entry1 :values.entrySet()) {
			
			System.out.println(entry1.getKey() + "        "+entry1.getValue());
		} 
		
		driver.close();
		
	}

}
