package org.stepdefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleGit extends Baseclass {
	@Given("User has to launch the chrome browser and maximize window")
	public void user_has_to_launch_the_chrome_browser_and_maximize_window() {
		launchBrowser();
		windowMaximize();
	    
	}

	@When("User has to give the url of the flipkart application")
	public void user_has_to_give_the_url_of_the_flipkart_application() {
		launchUrl("https://affiliate.flipkart.com/login");
	}

	@When("User has to pass the data {string} in email field")
	public void user_has_to_pass_the_data_in_email_field(String mail) {
		WebElement email= driver.findElement(By.id("inputEmail"));
		email.clear();
		email.sendKeys(mail);
	    
	}

	@When("User has to pass the data {string} in password field")
	public void user_has_to_pass_the_data_in_password_field(String pwd) {
		WebElement password= driver.findElement(By.name("inputPassword"));
		password.clear();
		password.sendKeys(pwd);  
	    
	}

	@When("User has to click login button")
	public void user_has_to_click_login_button() {
		WebElement login= driver.findElement(By.id("submitLogin"));
		login.click();  
	   
	}

	@Then("User has to close the browser")
	public void user_has_to_close_the_browser() {
		closeEntireBrowser();	
	}


}



