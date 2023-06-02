package hariKRishna.Autoit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutoIt {
	
	@Test
	public void getIt() throws InterruptedException, IOException {
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		driver.findElement(By.id("pickfiles")).click();
		Thread.sleep(2000);
		Runtime.getRuntime().exec("C:\\Users\\Hari\\eclipse-workspace 2\\AutoItBrowserStack\\Auto.exe");
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//div[@class='option__select__item__title'])[1]"))));
		driver.findElement(By.xpath("(//div[@class='option__select__item__title'])[1]")).click();
		driver.findElement(By.id("processTaskTextBtn")).click();
		driver.findElement(By.id("download")).click();
		Thread.sleep(2000);
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/reports/Screenshots/"+AutoIt.class.getSimpleName()+".png"));
		driver.close();
		
	}

}
