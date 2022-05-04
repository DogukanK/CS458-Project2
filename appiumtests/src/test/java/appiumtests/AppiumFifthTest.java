package appiumtests;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumFifthTest {

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
			testVisibility();
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		System.out.println("done.");
	}

	public static void testVisibility() throws Exception {
		
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
		
		MobileElement sideEffectsField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSideEffect"));
		
		MobileElement sinovacBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonSinovac"));
		MobileElement noneBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonNone"));
		
		MobileElement submitBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/buttonRegister"));
		
		//CASE 1: BIONTECH
		sideEffectsField.sendKeys("Nausea");
		sleepFor(200);
		submitBtn.click();
		
		sleepFor(1500);
		
		//CASE 2: SINOVAC
		sideEffectsField.clear();
		sleepFor(200);
		sinovacBtn.click();
		sleepFor(200);
		sideEffectsField.sendKeys("Insomnia");
		sleepFor(200);
		submitBtn.click();
		
		sleepFor(1500);
		
		//CASE 3: NONE
		noneBtn.click();
		sleepFor(200);
		submitBtn.click();
		
		sleepFor(1500);
		
		sinovacBtn.click(); //Observe that field is still filled as before
		
		sleepFor(1000);
		
		appDriver.quit();
	}
}
