package org.stepdefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class HookClass extends Baseclass {
	
	
	@Before(order= 1)
	private void precondition2()
	{
		launchBrowser();
	}
	
	@Before(order = 2)
	private void precondition1()
	{
		windowMaximize();
	}
	

	@After(order = 1 , value = "@second")
	private void postcondition2() 
	{
		System.out.println("Hook class executed");
		
	}

	
	@After(order = 2 , value = "@third")
	private void postcondition1() 
	{
		closeEntireBrowser();
		
	}

}
