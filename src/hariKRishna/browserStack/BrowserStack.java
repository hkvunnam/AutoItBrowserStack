package hariKRishna.browserStack;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BrowserStack {
	
	public WebDriver driver;
	
	@BeforeMethod
	public void browser() throws MalformedURLException {
		MutableCapabilities caps = new MutableCapabilities();
		driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"),caps);
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
	
	@Test
	public void multipleWindowsAndScreenShot() throws IOException {
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		driver.switchTo().newWindow(WindowType.TAB).get("https://rahulshettyacademy.com");
		Set<String> winId = driver.getWindowHandles();
		Iterator<String> it = winId.iterator();
		String parent = it.next();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,2100)");
		String text = driver.findElement(By.xpath("(//h2/a[1])[1]")).getText();
		driver.switchTo().window(parent);
		driver.findElement(By.cssSelector("input[name='name']")).sendKeys(text);
		WebElement w = driver.findElement(By.cssSelector("input[name='name']"));
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("C:\\Users\\Hari\\OneDrive\\Desktop\\Files\\screenshot.png"));
		FileUtils.copyFile(w.getScreenshotAs(OutputType.FILE), new File("C:\\Users\\Hari\\OneDrive\\Desktop\\Files\\screenshot!.png"));
		driver.close();
	}

	@Test
	public void alerts() throws InterruptedException {
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.findElement(By.id("name")).sendKeys("Hari");
		driver.findElement(By.id("alertbtn")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(500);
		driver.findElement(By.id("name")).sendKeys("Hari");
		driver.findElement(By.id("confirmbtn")).click();
		Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Hari, Are you sure you want to confirm?");
		driver.switchTo().alert().dismiss();
		Actions act = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
		act.moveToElement(driver.findElement(By.id("mousehover"))).build().perform();
		act.moveToElement(driver.findElement(By.linkText("Top"))).click().build().perform();
		js.executeScript("window.scrollBy(0,1000)");
		act.scrollToElement(driver.findElement(By.id("mousehover"))).build().perform();
		act.click(driver.findElement(By.linkText("Reload"))).build().perform();
	}
	
}
