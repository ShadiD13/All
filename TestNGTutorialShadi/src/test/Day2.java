package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Day2 {
	
	@Test
	public void ploan()
	{
		System.out.println("Good");
	}
	
	@BeforeTest
	public void prerequest()
	{
		System.out.println("I execute first");
	}

}
