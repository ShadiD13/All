package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Day1 {
	
	@AfterTest
	public void lastexecution()
	{
		System.out.println("I will execute last");
	}

	@Test
	public void Demo()
	{
		System.out.println("Hello");
	}
	
	@AfterSuite
	public void Afsuite()
	{
		System.out.println("I am the last 1");
	}
	
	@Test
	public void Demo1()
	{
		System.out.println("Bye");
	}
	

}
