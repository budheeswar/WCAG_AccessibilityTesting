package com.udb.allytest;

import static org.testng.Assert.assertTrue;

import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.deque.axe.AXE;
import com.deque.axe.AXE.Builder;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccessibilityTesting1 {
	WebDriver driver;
	public static final URL scriptURL = AccessibilityTesting1.class.getResource("/axe.min.js");

	@Test
	public void testAccesibilityIssuesForChrome() {
		Builder builder = new AXE.Builder(driver, scriptURL);
		JSONObject responseJSON = builder.analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			System.out.println("NO ERRORS");

		} else {
			AXE.writeResults("test-output/allyresults/googleChromeViolationsCheck", responseJSON);
			System.out.println("REPORTS \n" + AXE.report(violations).toString());
		}

	}
	@Test
	public void testAccessibilityForTitle() {
		Builder builder = new AXE.Builder(driver, scriptURL);
		//for title
		JSONObject responseJSON = builder.include("title").analyze();

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			System.out.println("NO ERRORS");

		} else {
			
			AXE.writeResults("test-output/allyresults/TitleViolationsCheck", responseJSON);
			System.out.println("REPORTS \n" + AXE.report(violations).toString());
		}
	}
	@Test
	public void testAccessibilityForWebElement() {
		Builder builder = new AXE.Builder(driver, scriptURL);
		//for title
		JSONObject responseJSON = builder.analyze(driver.findElement(By.xpath("//*[@id=\"search\"]/span/button/i")));

		JSONArray violations = responseJSON.getJSONArray("violations");

		if (violations.length() == 0) {
			System.out.println("NO ERRORS");

		} else {
			
			AXE.writeResults("test-output/allyresults/ElementViolationCheck", responseJSON);
			System.out.println("REPORTS \n" + AXE.report(violations).toString());
		}
	}
	

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://google.com");
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();

	}

}
