package com.Click;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Clickdotin {

	public static void main(String[] args) throws Exception {
		System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		final WebDriver driver = new FirefoxDriver();
		driver.get("http://www.click.in");	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Test for checking Title of the page
		String webTitle = driver.getTitle();
		System.out.println("LinkedIn's title is --> " +webTitle);
		String ActualTitle = "Free Classified Ads India | Free Ads Posting Classifieds India | ads India - Click.in";
		Assert.assertEquals(webTitle, ActualTitle);
		
		// Test for controlling the popups
		driver.findElement(By.xpath("//a[@class='clickin-city-popup-close-btn']")).click();
		driver.findElement(By.xpath("//b[contains(.,'Post your Ad')]")).click();
		driver.findElement(By.xpath("//a[@class='post_cat_150']")).click();
		driver.findElement(By.xpath("//a[@href='http://www.click.in/classifieds/48/post.html']")).click();
		
		// Test for selecting list element from the dropdown
		driver.findElement(By.id("s2id_city_list")).click();
		driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("Kolkata");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// Test for uploading image
		driver.findElement(By.className("postFormImageFieldBox file_input")).click();
		Thread.sleep(1000);
		StringSelection SS = new StringSelection("C:\\super-cars.png");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(SS, null);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(1000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.setAutoDelay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// Further steps to fillup the form
		driver.findElement(By.id("select2-drop-mask")).click();
		driver.findElement(By.xpath("//input[@aria-owns='select2-results-9']")).sendKeys("Maruti");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		driver.findElement(By.id("select2-chosen-134")).click();
		driver.findElement(By.id("s2id_autogen134_search")).sendKeys("Kizashi");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		driver.findElement(By.id("select2-chosen-160")).click();
		driver.findElement(By.id("s2id_autogen160_search")).sendKeys("CVT");
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		driver.findElement(By.id("select2-chosen-1")).click();
		driver.findElement(By.id("select2-result-label-202")).click();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		WebElement Fueltype = driver.findElement(By.id("s2id_fld_23_1"));
		Select FType = new Select(Fueltype);
		FType.selectByVisibleText("Diesel");
		
		WebElement ManufacturedYear = driver.findElement(By.id("s2id_fld_385_1"));
		Select ManYear = new Select(ManufacturedYear);
		ManYear.selectByVisibleText("2013");
		driver.findElement(By.xpath("//input[@id='fld_22_1']")).sendKeys("15000");
		driver.findElement(By.xpath("//input[@id='fld_5_1']")).sendKeys("500000");
		
		WebElement InsuranceIncluded = driver.findElement(By.id("s2id_402_1"));
		Select Insurance = new Select(InsuranceIncluded);
		Insurance.selectByVisibleText("No");
		
		driver.findElement(By.xpath("//input[@id='fld_1_1']")).sendKeys("Maruti Kazashi, Model - CVT, 2013");
		driver.findElement(By.xpath("//textarea[@id='fld_3_1']")).sendKeys("Maruti Kazashi, Model - CVT, Year-2013 in good condition. Registration Number : 786007XXXX, Kolkata-700001, India");
		driver.findElement(By.xpath("//input[@id='email_1']")).sendKeys("test@click.org");
		driver.findElement(By.xpath("//input[@id='fld_405_1_1']")).click();
		
		driver.findElement(By.id("s2id_fld_6_1")).click();
		driver.findElement(By.id("select2-result-label-754")).click();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		driver.findElement(By.xpath("//input[@name='post']")).click();
		
		// Test if an mandatory element [e.g. Logo] is present on the webpage.
		boolean Logo = driver.findElement(By.xpath("//img[@alt='Free Classified Ads India - Click.in']")).isDisplayed();
		System.out.println("Result is: " +Logo);
				
		// Test if there is any broken links present in the webpage.
		class BrokenLinks {
			
		public void brokenLink()
		{
			List<WebElement> links = driver.findElements(By.tagName("a"));
			System.out.println("Total links present in the page are :" +links.size());
			for(int i=0; i<links.size(); i++)
			{
				WebElement ele = links.get(i);
				String URL = ele.getAttribute("href");
				verifyActiveLinks(URL);
			}
							
		public void verifyActiveLinks(String LinkURL)
		{
		try{
			URL url = new URL(LinkURL);
			HttpURLConnection httpURLConnect = (HttpURLConnection)url.openConnection();
			httpURLConnect.setConnectTimeout(3000);
			httpURLConnect.connect();
			if(httpURLConnect.getResponseCode() == 200)
			{
				System.out.println(LinkURL+ " - " +httpURLConnect.getResponseMessage());
			}
			if(httpURLConnect.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND)
			{
				System.out.println(LinkURL+ " - " +httpURLConnect.getResponseMessage()+ " - " +HttpURLConnection.HTTP_NOT_FOUND);
			}			
		}
				catch (Exception e) {
					}
				}
			}
		}
	}
}
