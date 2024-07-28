package test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Day3 {
	
	
    @Test
    public void WebLoginCarLoan()
    {
    	//Selenium
    	System.out.println("WebLoginCar");
    }
	
    @BeforeMethod
    public void beforeevery()
    {
    	System.out.println("I will execute before every test method in Day 3 class");
    }
    
	@Test
	public void MobileLogincarLoan()
	{
		//Appium
		System.out.println("MobileLoginCar");
	}
	
	@BeforeSuite
	public void Bfsuite()
	{
		System.out.println("I am no 1");
	}
	
	@Test(enabled=false)
	public void MobileSignIncarLoan()
	{
		//Appium
		System.out.println("MobileSigninCar");
	}
	
	@Test
	public void MobileSignOutcarLoan()
	{
		//Appium
		System.out.println("MobileSignOutCar");
	}
	
	@Test(dependsOnMethods= {"WebLoginCarLoan","MobileSignOutcarLoan"})
	public void APICarLoan()
	{
		System.out.println("APILoginCar");	
	}

}
