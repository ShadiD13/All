package test;

import org.testng.annotations.Test;

import org.testng.annotations.Test;

public class Day4 {
	
	
    @Test
    public void WebLoginHomeLoan()
    {
    	//Selenium
    	System.out.println("WebLoginHome");
    }
	
	@Test(groups= {"smoke"})
	public void MobileLoginHomeLoan()
	{
		//Appium
		System.out.println("MobileLoginHome");
	}
	
	@Test
	public void LoginAPIHomeLoan()
	{
		System.out.println("APILoginHome");	
	}

}
