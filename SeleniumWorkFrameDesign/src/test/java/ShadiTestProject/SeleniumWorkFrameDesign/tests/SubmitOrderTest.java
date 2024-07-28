package ShadiTestProject.SeleniumWorkFrameDesign.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium. WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import ShadiTestProject.PageObjects.CartPage;
import ShadiTestProject.PageObjects.CheckoutPage;
import ShadiTestProject.PageObjects.ConfirmationPage;
import ShadiTestProject.PageObjects.ProductCatalogue;
import ShadiTestProject.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("shadi@test.com", "Shadi_1986713");
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		List< WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		productCatalogue.goToCartPage();
		CartPage cartPage = new CartPage(driver);
		boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);		cartPage.goTocheckOut();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		checkoutPage.ShippingInformation("jor");
		ConfirmationPage confirmationPage = checkoutPage.Proceed();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

}
