import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class PS {

	public void doThis()
	{
		System.out.println("I am her");
	}
	
	@BeforeTest
	public void beforeRun()
	{
		System.out.println("Run me first");
	}
	
	@AfterTest
	public void afterTest()
	{
		System.out.println("Run me last");
	}


	
}
