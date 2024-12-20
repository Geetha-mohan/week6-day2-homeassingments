package steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EditAccountStaticParameters {

	public RemoteWebDriver driver;
	public EdgeOptions options;
	public JavascriptExecutor js;
	public WebDriverWait wait;
	
	public String phoneNo;

	@Given("Launch browser and load URL")
	public void launch_browser_and_load_url() {
		options = new EdgeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");

		driver = new EdgeDriver((EdgeOptions) options);
		driver.get("https://login.salesforce.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}
	@Given("Login into the application")
	public void login_into_the_application() {
		driver.findElement(By.id("username")).sendKeys("vidyar@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Sales@123");
		driver.findElement(By.id("Login")).click();
	}
	@When("Click viewAll from App Launcher and select Actions")
	public void click_view_all_from_app_launcher_and_select_actions() {
		driver.findElement(By.xpath("//button[contains(@title,'App Launcher')]/div")).click();
		driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
		WebElement targetElement = driver.findElement(By.xpath("//p[text()='Accounts']"));
		// Use JavaScriptExecutor to scroll the element into view
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
		targetElement.click();

	}
	@When("Select the unique account name and click Edit")
	public void select_the_unique_account_name_and_click_edit() throws InterruptedException {
		WebElement SearchBox = driver.findElement(By.xpath("//label[contains(text(),'Search this list')]//following::input"));
		SearchBox.sendKeys("Geetha"+Keys.RETURN);
		//wait for page gets refreshed
		//wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='uiVirtualDataTable indicator']//following::table//tr[1]/td[6]")))
		
		/* Explicit wait to resolve stale element exception
		 *WebElement editDropdown =driver.findElement(By.xpath( "//div[@class='uiVirtualDataTable indicator']//following::table//tr[1]/td[6]"));
		 * wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.elementToBeClickable(editDropdown));
		 * Thread.sleep(5000);
		 *  editDropdown.click();*/
		
		 /* js executer is another way to resolve stale element exception 
		 * js =(JavascriptExecutor) driver; //js.executeScript("arguments[0].click();",editDropdown); */
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class='forceVirtualActionMarker forceVirtualAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@title='Edit' and contains(@data-target-selection-name,'StandardButton.Account.Edit')]")).click();
	}
	@When("Set all the dropdown options")
	public void set_all_the_dropdown_options() throws InterruptedException {
		// Set Type to Technology Partner
		WebElement type = driver.findElement(By.xpath("//label[text()='Type']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",type);
		type.click();
		Thread.sleep(3000);
		Actions typeBox = new Actions(driver);
		WebElement tech = driver.findElement(By.xpath("//span[@class='slds-truncate'][normalize-space()='Technology Partner']"));
		typeBox.moveToElement(tech).click().perform();
		
		// Set Industry to Healthcare
		WebElement industry = driver.findElement(By.xpath("//label[text()='Industry']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",industry);
		industry.click();
		Thread.sleep(2000);
		Actions industryBox = new Actions(driver);
		industryBox.moveToElement(driver.findElement(By.xpath("//span[text()='Healthcare']"))).click().perform();
		
		// Set Customer Priority to Low
		WebElement priority = driver.findElement(By.xpath("//label[text()='Customer Priority']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",priority);
		priority.click();
		Thread.sleep(2000);
		Actions priorityBox = new Actions(driver);
		industryBox.moveToElement(driver.findElement(By.xpath("//span[text()='Low']"))).click().perform();
		
		// Set SLA to Silver
		WebElement SLA = driver.findElement(By.xpath("//label[text()='SLA']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",SLA);
		SLA.click();
		Thread.sleep(2000);
		Actions SLABox = new Actions(driver);
		industryBox.moveToElement(driver.findElement(By.xpath("//span[text()='Silver']"))).click().perform();
		
		// Set Active to NO
		WebElement Active = driver.findElement(By.xpath("//label[text()='Active']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",Active);
		Active.click();
		Thread.sleep(2000);
		Actions ActiveBox = new Actions(driver);
		industryBox.moveToElement(driver.findElement(By.xpath("//span[text()='No']"))).click().perform();
		
		// Set Upsell Opportunity to No 
		WebElement upSellOpportunity = driver.findElement(By.xpath("//label[text()='Upsell Opportunity']//following::button"));
		js.executeScript("arguments[0].scrollIntoView(true);",upSellOpportunity);
		upSellOpportunity.click();
		Thread.sleep(2000);
		Actions upSellOpportunityBox = new Actions(driver);
		industryBox.moveToElement(driver.findElement(By.xpath("//span[text()='No']"))).click().perform();
		

	}
	@When("Enter the Billing Address as {string}")
	public void enter_the_billing_address_as(String billAddress) {
		driver.findElement(By.xpath("//label[text()='Billing Street']/following::textarea")).sendKeys(billAddress);

	}
	@When("Enter the Shipping Address as {string}")
	public void enter_the_shipping_address_as(String shipAddress) {
		driver.findElement(By.xpath("//label[text()='Shipping Street']/following::textarea")).sendKeys(shipAddress);
		
	}
	@When("Enter the unique phoneNumber as {string}")
	public void enter_the_unique_phone_number_as(String phone) {
		driver.findElement(By.xpath("//label[text()='Phone']/following::input")).sendKeys(phone);
		phoneNo = phone;

	}
	@Then("Click Save")
	public void click_save() {
		driver.findElement(By.xpath("//button[text()='Save']")).click();

	}
	@Then("Verify the phoneNumber")
	public void verify_the_phone_number() throws InterruptedException {
		Thread.sleep(4000);
		String phNo = driver.findElement(By.xpath("//span[contains(@class,'forceOutputPhone')]")).getText();
		Assert.assertEquals(phNo, phoneNo, "phone number is not matched");
		driver.close();
	}
}
