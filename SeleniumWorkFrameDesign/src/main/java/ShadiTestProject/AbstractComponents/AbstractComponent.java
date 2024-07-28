package ShadiTestProject.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium. WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css="[routerlink*='cart']")
	 WebElement cartHeader;
	
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}


	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void goToCartPage()
	{
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		cartHeader.click();
	}
	

	
	public void waitForElementToDisappear( WebElement findBy) throws InterruptedException
	{
		Thread.sleep(1000);
		//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(findBy));
	}


}
