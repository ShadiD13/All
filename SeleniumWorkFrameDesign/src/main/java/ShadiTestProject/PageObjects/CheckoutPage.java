package ShadiTestProject.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium. WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShadiTestProject.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']")
	 WebElement country;
	
	@FindBy(css=".ta-item:nth-of-type(1)")
	 WebElement selectedCountry;
	
	@FindBy(css=".action__submit")
	 WebElement submit;
	
	By waitEle=By.cssSelector(".ta-item");
	

	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void ShippingInformation(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		//country.sendKeys("jor");
		waitForElementToAppear(waitEle);
		selectedCountry.click();
		
	}
	
	public ConfirmationPage Proceed()
	{
		//driver.findElement(By.cssSelector(".action__submit")).click();
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
}
	