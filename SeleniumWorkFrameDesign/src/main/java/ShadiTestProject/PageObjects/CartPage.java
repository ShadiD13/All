package ShadiTestProject.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium. WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ShadiTestProject.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	
	@FindBy(css=".cartSection h3")
	List< WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	 WebElement checkoutEle;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyProductDisplay(String productName)
	{
		boolean match = cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goTocheckOut()
	{
		checkoutEle.click();
		return new CheckoutPage(driver);
	}
}
