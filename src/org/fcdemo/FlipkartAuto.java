package org.fcdemo;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FlipkartAuto {

public static void main(String[] args) throws InterruptedException {
	
		System.setProperty("webdriver.chrome.driver","F:\\Applications\\Automation\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		   
	    driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']")).sendKeys("8610271604");
		driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")).sendKeys("Selva0320@");
		   
	    WebElement w1=driver.findElement(By.xpath("//div[@class='_36HLxm col col-3-5']"));
		WebElement w2=driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));
		   
	    Actions act=new Actions(driver);
	    act.moveToElement(w1).build().perform();
	    Thread.sleep(500);
	    act.click(w2).build().perform();
       Thread.sleep(2000); // let login happen 
       
       driver.findElement(By.name("q")).sendKeys("HP laptop"+Keys.ENTER);
       Thread.sleep(1500);
       
       //Open laptop product page
       WebElement laptop = driver.findElement(By.xpath("//div[contains(text(),'HP Pavilion Ryzen 5 Hexa Core 5600H')]"));
       act.click(laptop).build().perform();
       
       //navigate to product page
       ArrayList tabs = new ArrayList (driver.getWindowHandles());
       driver.switchTo().window((String) tabs.get(1)); 
       
       //Add to cart operation for laptop
       WebElement addToCart = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
       act.click(addToCart).build().perform();
       Thread.sleep(500);
       driver.close();
       
       //move to main window
       driver.switchTo().window((String) tabs.get(0)); 
       Thread.sleep(500);
       
       //Reset search box and search for realme mobile
       WebElement search = driver.findElement(By.name("q"));
       String ctrlA = Keys.chord(Keys.CONTROL, "a");
       act.click(search).pause(100)
       	.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
       	.sendKeys(Keys.BACK_SPACE).sendKeys("Realme"+Keys.ENTER).build().perform();
       Thread.sleep(1500);
       
       //open mobile product page
       WebElement mobile = driver.findElement(By.xpath("//div[contains(text(),'realme 8s 5G')]"));
       String mobName = "realme 8s 5G";
       act.click(mobile).build().perform();
       
       //Navigate to mobile product page
       tabs = new ArrayList (driver.getWindowHandles());
       driver.switchTo().window((String) tabs.get(1)); // move to product page
       
       //add mobile to cart
       addToCart = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']"));
       act.click(addToCart).build().perform();
       Thread.sleep(1500);
       
       //Remove the laptop operation
       laptop = driver.findElement(By.xpath("//a[contains(text(),'HP Pavilion Ryzen 5 Hexa Core 5600H')]"));
       WebElement removeButton = laptop.findElement(By.xpath("//a/ancestor::div[4]")).
       		findElement(By.xpath("//div[contains(text(),'Remove')]"));
       act.moveToElement(removeButton).click().build().perform();
       
       //Remove popup confirmation box
       /*
       driver.switchTo().activeElement();
       System.out.println(driver.switchTo().activeElement().getText());
       
       WebElement confirmDelete = driver.
  		findElement(By.xpath("//div[@class='_1DATsP']"));
       System.out.println(confirmDelete.getText());
       Thread.sleep(1500);
       */
       
       //Verify the last added item is present in cart and close the cart page
       mobile = driver.findElement(By.xpath("//a[contains(text(),'realme 8s 5G')]"));
       String mobileInCart = mobile.getText();
       if(mobileInCart.contains(mobName)) {
    	   System.out.println("Added mobile is present in cart.");
       }
       else {
    	   System.out.println("Something went wrong. Mobile is missing");
       }
       driver.close();
       
       // move to main page
       tabs = new ArrayList (driver.getWindowHandles());
       driver.switchTo().window((String) tabs.get(0)); 
       
       // open the hover menu on My account
       WebElement actMenu = driver.findElement(By.xpath("//div[@class='_28p97w']"));
       act.moveToElement(actMenu).perform();
       
       // click logout on hover menu
       WebElement logout = driver.findElement(By.xpath("//div[contains(text(),'Logout')]"));
       //act.moveToElement(logout).click().perform();
      
	}
}