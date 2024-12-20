package steps;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateNewAcctMulipleDataParam {

	public RemoteWebDriver driver;
	public EdgeOptions options;
	public JavascriptExecutor js;
	public String accName;

@Given("Launch the Browser and Load the URL")
public void launch_the_browser_and_load_the_url() {
    // Write code here that turns the phrase above into concrete actions
	options = new EdgeOptions();
	options.addArguments("--incognito");
	options.addArguments("--disable-notifications");

	driver = new EdgeDriver((EdgeOptions) options);
	driver.get("https://login.salesforce.com");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    //throw new io.cucumber.java.PendingException();
}
@Given("Login into application")
public void login_into_application() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.id("username")).sendKeys("dilip@testleaf.com");
	driver.findElement(By.id("password")).sendKeys("leaf@2024");
	driver.findElement(By.id("Login")).click();
    //throw new io.cucumber.java.PendingException();
}
@When("Click viewAll and select Actions")
public void click_view_all_and_select_actions() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.xpath("//button[contains(@title,'App Launcher')]/div")).click();
	driver.findElement(By.xpath("//button[contains(@aria-label,'View All Applications')]")).click();
    //throw new io.cucumber.java.PendingException();
}
@When("Click New button")
public void click_new_button() {
    // Write code here that turns the phrase above into concrete actions
	// Locate the element you want to scroll to Legal Entities
			WebElement targetElement = driver.findElement(By.xpath("//p[text()='Accounts']"));

			// Use JavaScriptExecutor to scroll the element into view
			js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
			targetElement.click();
			
			//create new legal entity
			driver.findElement(By.xpath("//div[@title='New']")).click();
    //throw new io.cucumber.java.PendingException();
}
@When("Enter the Account Name as {string}")
public void enter_the_account_name(String AcctName) {
    accName=AcctName;
	driver.findElement(By.xpath("//label[text()='Account Name']/following::input")).sendKeys(accName);
    //throw new io.cucumber.java.PendingException();
}
@When("Select Ownership as Public")
public void select_ownership_as_public() {
    // Write code here that turns the phrase above into concrete actions
	//location dropdown
	WebElement ownerDropdown = driver.findElement(By.xpath("//label[text()='Ownership']/following::button"));
	js.executeScript("arguments[0].scrollIntoView(true);", ownerDropdown);
	ownerDropdown.click();
	driver.findElement(By.xpath("//span[contains(text(),'Public')]")).click();
    //throw new io.cucumber.java.PendingException();
}
@Then("Click on the Save button")
public void click_on_the_save_button() {
    // Write code here that turns the phrase above into concrete actions
	driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
    //throw new io.cucumber.java.PendingException();
}
@Then("check Account Name")
public void check_account_name() {
    // Write code here that turns the phrase above into concrete actions
	String AccountName = driver.findElement(By.xpath("//slot[@name='primaryField']/lightning-formatted-text")).getText();
	if(AccountName.equalsIgnoreCase(accName))
		System.out.println("account name VERIFIED");
	else
		System.out.println("account name incorrect");
		
    //throw new io.cucumber.java.PendingException();
}



}
