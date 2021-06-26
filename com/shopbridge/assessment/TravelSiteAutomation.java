package com.shopbridge.assessment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TravelSiteAutomation {
	/**
	 * This method will execute the automation test case from end to end.
	 */
	public void execute() {
		WebDriver driver = null;
		try {
			driver = intializeWebDriver();
			executeTestSteps(driver);
		} catch (Exception e) {
			System.out.println("Error while executing the test case ");
			e.printStackTrace();
		} finally {
			// Close the Browser.
			if (driver != null)
				driver.close();

		}
	}

	/**
	 * 
	 * @param driver instance
	 */
	private void executeTestSteps(WebDriver driver) {
		// Locate dropdown element on web page by By.id.
		WebElement dropdown = driver.findElement(By.id("language"));

		// Click the dropdown
		dropdown.click();

		// get options from dropdown
		String text = dropdown.getText();

		// Validation of dropdown values
		dropdown.getText().compareToIgnoreCase("English");
		dropdown.getText().compareToIgnoreCase("Dutch");

		// validation of the dropdown values by if statement
		System.out.println("Assertion: To Check the dropdown contains English and dutch.");
		if (text.contains("English") && text.contains("Dutch"))
			System.out.println("SUCCESS: Dropdown Contains both options English and Dutch");

		// Clicking on the options available
		dropdown.findElement(By.partialLinkText("Dutch")).click();
		dropdown.click();
		dropdown.findElement(By.partialLinkText("English")).click();

		/// input fullname
		WebElement fullname = driver.findElement(By.name("name"));
		fullname.sendKeys("testname");

		// input organization name
		WebElement orgname = driver.findElement(By.id("orgName"));
		orgname.sendKeys("testorg");

		// Input email
		WebElement email = driver.findElement(By.name("email"));
		email.sendKeys("testmail@testmail.com");

		// Checking the check box that I agree to terms and conditions
		WebElement checkbox = driver.findElement(By.xpath("//span[@class='black-color ng-binding']"));
		checkbox.click();

		// Clicking on the button Get Started
		WebElement button = driver.findElement(By.xpath("//div[@class='form-group custom-form-group']"));
		button.click();

		// Waiting for the alert
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/// Validate that you received an email
		System.out.println("ASSERTION: To Validate that user recieved email");
		WebElement message = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-custom']"));
		String alertmsg = message.getText();
		System.out.println("Alert message is:" + alertmsg);
		boolean msgexists = message.isDisplayed();
		if (msgexists)
			System.out.println("SUCCESS: Validated alert message");

		// Waiting for the tester to observe
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	/**
	 * 
	 * @return a Chrome driver instance
	 */
	private WebDriver intializeWebDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\LENOVO\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Launch website
		driver.navigate().to("http://jt-dev.azurewebsites.net/#/SignUp");

		// Maximize the browser
		driver.manage().window().maximize();

		return driver;
	}

}
