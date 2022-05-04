package appiumtests;

//import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

public class AppiumFourthTest {

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
			testBackAndHome();
		} catch (Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		System.out.println("done.");
	}

	public static void testBackAndHome() throws Exception {
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName","Android SDK built for x86");
		caps.setCapability("udid","emulator-5554");
		caps.setCapability("platformName","Android");
		caps.setCapability("platformVersion","10");
		
		caps.setCapability("appPackage", "com.example.covid_19survey");
		caps.setCapability("appActivity", "com.example.covid_19survey.MainActivity");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		appDriver = new AndroidDriver<MobileElement>(url, caps);
		
		System.out.println("Application started successfully.");
		
		MobileElement nameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextUsername"));
		MobileElement surnameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSurname"));
		MobileElement cityField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextCity"));
		MobileElement sideEffectsField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSideEffect"));
		MobileElement PCRpositiveAndCovidSymptomsField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSymptoms"));
		
		MobileElement femaleBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonFemale"));
		MobileElement xBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonX"));
		
		TouchAction action = new TouchAction(appDriver);
		
		//CASE 1: BACK KEY IS USED
		nameField.sendKeys("Sarp");
		sleepFor(200);
		surnameField.sendKeys("Kaya");
		sleepFor(200);
		cityField.sendKeys("Ankara");
		sleepFor(200);
		femaleBtn.click();
		sleepFor(200);
		sideEffectsField.sendKeys("Fever, Headache");
		sleepFor(200);
		PCRpositiveAndCovidSymptomsField.sendKeys("No PCR tests were positive");
		sleepFor(1000);
		
		((AndroidDriver) appDriver).pressKey(new KeyEvent(AndroidKey.BACK));
		sleepFor(1000);
		((AndroidDriver) appDriver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		sleepFor(500);
		new TouchAction(((AndroidDriver) appDriver)).tap(PointOption.point(540,960)).release().perform(); 
		
		sleepFor(1500);
		
		nameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextUsername"));
		surnameField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSurname"));
		cityField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextCity"));
		PCRpositiveAndCovidSymptomsField = appDriver.findElement(By.id("com.example.covid_19survey:id/editTextSymptoms"));
		
		femaleBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonFemale"));
		xBtn = appDriver.findElement(By.id("com.example.covid_19survey:id/radioButtonX"));
		
		sleepFor(1500);
		
		//CASE 2: HOME KEY IS USED
		nameField.sendKeys("Kerem");
		sleepFor(200);
		surnameField.sendKeys("Berçin");
		sleepFor(200);
		cityField.sendKeys("Kocaeli");
		sleepFor(200);
		xBtn.click();
		sleepFor(200);
		PCRpositiveAndCovidSymptomsField.sendKeys("1 positive PCR, no symptoms");
		sleepFor(1000);
		
		((AndroidDriver) appDriver).pressKey(new KeyEvent(AndroidKey.HOME));
		sleepFor(1000);
		((AndroidDriver) appDriver).pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		sleepFor(500);
		new TouchAction(((AndroidDriver) appDriver)).tap(PointOption.point(540,960)).release().perform();
		
		sleepFor(1500);
		
		appDriver.quit();
	}

}
