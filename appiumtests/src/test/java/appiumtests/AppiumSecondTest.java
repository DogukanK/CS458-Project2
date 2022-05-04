package appiumtests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumSecondTest {

	static AppiumDriver<MobileElement> appDriver;
	
	private static void sleepFor(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			System.out.println("Got interrupted.");
		}
	}
	
	public static void main(String[] args) {
		try {
			testValidOrInvalid();
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		System.out.println("done.");
	}

	public static void testValidOrInvalid() throws Exception {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName","Android SDK built for x86");
		caps.setCapability("udid","emulator-5554");
		caps.setCapability("platformName","Android");
		caps.setCapability("platformVersion","10");
		
		caps.setCapability("appPackage", "com.example.covid_19survey");
		caps.setCapability("appActivity", "com.example.covid_19survey.MainActivity");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		appDriver = new AppiumDriver<MobileElement>(url, caps);
		
		System.out.println("Application started successfully.");
		
		MobileElement nameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextUsername"));
		MobileElement surnameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSurname"));
		MobileElement cityField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextCity"));
		MobileElement selectDateBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/selectDate"));
		
		MobileElement submitBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/buttonRegister"));
		
		//CASE 1: FIRST NAME IS INVALID
		nameField.sendKeys("sarp_kaya123");
		sleepFor(200);
		submitBtn.click();
		
		sleepFor(1500);
		
		//CASE 2: SURNAME IS INVALID
		nameField.clear();
		sleepFor(200);
		nameField.sendKeys("Sarp");
		sleepFor(200);
		surnameField.sendKeys("Kaya_Sarp4912");
		sleepFor(200);
		submitBtn.click();
		
		sleepFor(1500);
		
		//CASE 3: BIRTHDAY IS INVALID
		surnameField.clear();
		sleepFor(200);
		surnameField.sendKeys("Kaya");
		sleepFor(200);
		
		selectDateBtn.click();
		sleepFor(2000);
		MobileElement nextMonthBtn = appDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Next month\"]"));
		for (int i = 0; i < 10; i++) {
			nextMonthBtn.click();
			sleepFor(200);
		}
		MobileElement dayBtn = appDriver.findElement(By.xpath("//android.view.View[@content-desc=\"16 January 2023\"]"));
		dayBtn.click();
		sleepFor(200);
		MobileElement okBtn = appDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]"));
		okBtn.click();
		sleepFor(200);
		
		submitBtn.click();		
		sleepFor(1500);
		
		// CASE 4: CITY IS INVALID
		selectDateBtn.click();
		
		sleepFor(2000);
		
		MobileElement prevMonthBtn = appDriver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Previous month\"]"));
		for (int i = 0; i < 10; i++) {
			prevMonthBtn.click();
			sleepFor(200);
		}
		dayBtn = appDriver.findElement(By.xpath("//android.view.View[@content-desc=\"13 May 2021\"]"));
		dayBtn.click();
		sleepFor(200);
		okBtn = appDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.Button[2]"));
		okBtn.click();
		sleepFor(200);
		
		cityField.sendKeys("An/kara06");
		sleepFor(200);
		submitBtn.click();

		sleepFor(1500);
		
		// CASE 5: ALL INPUTS ARE VALID
		cityField.clear();
		sleepFor(200);
		cityField.sendKeys("Ankara");

		submitBtn.click();

		sleepFor(1500);
		
		
		appDriver.quit();
	}
}
