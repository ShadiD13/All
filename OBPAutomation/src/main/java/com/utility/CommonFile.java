package com.utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

import org.apache.http.ssl.SSLContexts;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium. WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import filloreader.Xl_Reader;


public class CommonFile extends Utility{

	public static WebDriver driver;
	static Xl_Reader xldata = new Xl_Reader(AppConfig.Logindetails_TestData);
	JavascriptExecutor jsExecutor;
	String copydata;
	
	public static String browserName=xldata.getCellData("Login", "Browser", 2);
	
	  WebElement element;
	 Actions actions;
	 String screenName;
	 boolean isChildScreenOpened = false;
	 WebDriverWait waitDriver;

	
	public static  void launch() throws InterruptedException {
		if(driver==null) {
			if(browserName.equalsIgnoreCase("chrome")) {
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
				System.setProperty("webdriver.chrome.driver", AppConfig.chromepath);   
				ChromeOptions opt= new ChromeOptions();
				opt.addArguments("user-data-dir=C:\\Users\\Noor\\AppData\\Local\\Google\\Chrome");
				opt.addArguments("--ignore-certificate-error");
				driver = new ChromeDriver(opt);
			}

			else if(browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",AppConfig.firefoxpath);
				driver = new FirefoxDriver();
			}

			else if(browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", AppConfig.internetexppath);
				driver = new FirefoxDriver();
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String url = xldata.getCellData("Login", "URL", 2);
		driver.get(url);



		//WebDriverWait wait =new WebDriverWait(driver,5);
		
//		try {
//			driver.findElement(By.xpath("//button[@id='details-button']")).click();
//			driver.findElement(By.xpath("//a[@id='proceed-link']")).click();
//		}
//		catch(Exception e) {
//			
//		}
//		
//		try {
//			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifr_AlertWin']")));
//			driver.findElement(By.xpath("//input[@id='BTN_OK']")).click();
//			driver.switchTo().defaultContent();
//		}
//		catch(Exception e) {
//			
//		}
		
//		driver.switchTo().defaultContent();
//		String winId=driver.getWindowHandle();
//		Set<String> winIds=driver.getWindowHandles();
//	
//		ArrayList<String> al=new ArrayList<String>(winIds);
//		driver.switchTo().window(al.get(1));
		
				
		String un= xldata.getCellData("Login", "Username", 2);
		driver.findElement(By.xpath("//input[@id='LOGINUSERID']")).sendKeys(un);
		String pwd = xldata.getCellData("Login", "Password", 2);
		driver.findElement(By.xpath("//input[@id='user_pwd']")).sendKeys(pwd);
		
		/*String ent = xldata.getCellData("Login", "Entity", 2);
		Select drpEntity = new Select(driver.findElement(By.id("IDENT")));
		drpEntity.selectByVisibleText(ent);*/
		
		driver.findElement(By.id("fc_sbmit")).submit();
		Thread.sleep(7000);
		driver.switchTo().frame(driver.findElement(By.id("ifr_AlertWin")));
		driver.findElement(By.id("BTN_OK")).click();
	}
	
	
	public void launchScreen(String screenId) throws Exception {
		//WebDriverWait wait =new WebDriverWait(driver,5);
		driver.switchTo().defaultContent();
		Thread.sleep(1000);
		driver.findElement(By.id("fastpath")).clear();
		driver.findElement(By.id("fastpath")).sendKeys(screenId);
		driver.findElement(By.id("btnGo")).click();
	}
	
	
	
	public void switchtoEntity(String getvalue) throws Exception {
		
		if(getvalue != null && !getvalue.isEmpty()) {
		Actions actions = new Actions(driver);
        driver.switchTo().defaultContent();
         WebElement menuOption = driver.findElement(By.xpath("//li[@id='Branch_Menu']"));
        actions.moveToElement(menuOption).perform();
        driver.findElement(By.xpath("//li[@id='select_branch']")).click();
        driver.switchTo().frame(driver.findElement(By.id("ifrSubScreen")));
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).clear();
       // driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).sendKeys("001");
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).sendKeys(getvalue);
        driver.findElement(By.xpath("//button[@class='BTNtext']")).click();
        driver.findElement(By.xpath("//tr[@class='TBLoneTR'][1]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.id("ifr_AlertWin")));
        driver.findElement(By.id("BTN_OK")).click();
     }
	}
	
	public void switchBranch240() throws Exception {
		Actions actions = new Actions(driver);
        driver.switchTo().defaultContent();
         WebElement menuOption = driver.findElement(By.xpath("//li[@id='Branch_Menu']"));
        actions.moveToElement(menuOption).perform();
        driver.findElement(By.xpath("//li[@id='select_branch']")).click();
        driver.switchTo().frame(driver.findElement(By.id("ifrSubScreen")));
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).clear();
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).sendKeys("240");
        driver.findElement(By.xpath("//button[@class='BTNtext']")).click();
        driver.findElement(By.xpath("//tr[@class='TBLoneTR'][1]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.id("ifr_AlertWin")));
        driver.findElement(By.id("BTN_OK")).click();
	}
	
	public void homeBranch() throws Exception
	{
		Actions actions = new Actions(driver);
        driver.switchTo().defaultContent();
         WebElement menuOption = driver.findElement(By.xpath("//li[@id='Branch_Menu']"));
        actions.moveToElement(menuOption).perform();
        driver.findElement(By.xpath("//li[@id='select_branch']")).click();
        driver.switchTo().frame(driver.findElement(By.id("ifrSubScreen")));
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).clear();
        driver.findElement(By.xpath("//label[contains(text(),'Branch Code')]//following::input[1]")).sendKeys("399");
        driver.findElement(By.xpath("//button[@class='BTNtext']")).click();
        driver.findElement(By.xpath("//tr[@class='TBLoneTR'][1]")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(2000);
        driver.switchTo().frame(driver.findElement(By.id("ifr_AlertWin")));
        driver.findElement(By.id("BTN_OK")).click();
	}
	
	
	public void swichtframe() {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		}
	
	public void defaultFrame()
	{
		driver.switchTo().defaultContent();
	}
	
     public void branchaccept(String getvalue,String locator) throws Exception {
		
		if(getvalue != null && !getvalue.isEmpty()) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		//driver.findElement(By.xpath("//input[@id='TxnBranch']")).sendKeys("001");
		driver.findElement(By.xpath(locator)).click();
		driver.switchTo().defaultContent();
		switchToActiveFrame();
	 }
	}
	
	public void click(String locator) throws Exception {
		WebDriverWait wait =new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locator)))).click();
		} catch (ElementClickInterceptedException ex) {
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(locator)))).click();
		}
	}
	


	public static  WebElement waitForElementToBeClickable(WebDriver driver, WebElement  WebElement,int seconds) {
		WebDriverWait wait =new WebDriverWait(driver,seconds);
		 WebElement element=wait.until(ExpectedConditions.elementToBeClickable( WebElement));
		return element;
	}



	public void AuthoriseLogin(String locator,String locators1) throws Exception{
/*		 WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		Thread.sleep(2000);
		 WebElement element1 = driver.findElement(By.xpath(locators1));
		element1.click();
		Thread.sleep(5000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[@class='oj-button-button oj-component-initnode']//div//span[text()='Login']")));*/
		String un= xldata.getCellData("Login", "Username", 3);
		driver.findElement(By.xpath("//input[@id='LOGINUSERID']")).sendKeys(un);
		String pwd = xldata.getCellData("Login", "Password", 3);
		driver.findElement(By.xpath("//input[@id='user_pwd']")).sendKeys(pwd);
		driver.findElement(By.id("fc_sbmit")).submit();
		Thread.sleep(7000);
		driver.switchTo().frame(driver.findElement(By.id("ifr_AlertWin")));
		driver.findElement(By.id("BTN_OK")).click();

	}

	public static  void close()
	{
		driver.close();
		driver=null;
	}

	public void popupinputxpath(String getvalue, String locator) throws IOException, ParseException, Exception
	{

		if(getvalue != null && !getvalue.isEmpty()) {
			 WebElement elem=driver.findElement(By.xpath(locator));
			Thread.sleep(2000);
			elem.clear();
			elem.sendKeys(getvalue);
			passFailScreenshot(driver,"seleniumAutomation");
			test.pass("Entered and selected value is " +getvalue);

		}
	}
	
	public void fieldExistsOrNot(String locator) throws Exception {
		
		 WebElement textbox = driver.findElement(By.xpath(locator));
		textbox.click();
		textbox.sendKeys(Keys.F1);
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		passFailScreenshot(driver,"seleniumAutomation");
		test.pass("The field exist");
		clickBtnOk();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));

		int size = driver.findElements(By.tagName("iframe")).size();
		if(size==2) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		}	

	}
	
	

	
		/*public void fieldExistsOrNot(String locator) throws Exception {
		
			
		driver.findElement(By.xpath(locator)).click();
		 WebElement textbox = driver.findElement(By.xpath(locator));
		//textbox.click();
		textbox.sendKeys(Keys.F1);

		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		passFailScreenshot(driver,"seleniumAutomation");
		test.pass("The field exist");
		clickBtnOk();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));

		int size = driver.findElements(By.tagName("iframe")).size();
		if(size==2) {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		}	

	}*/
	
	
	public void fileupload(String getvalue) throws Exception  {
		
		Robot robot= new Robot();
     	robot.setAutoDelay(4000);
		StringSelection stringSelection = new StringSelection(getvalue);

		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);

		robot.keyPress(KeyEvent.VK_CONTROL);			
		robot.keyPress(KeyEvent.VK_V);
	

		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
     	}
	
	
	
	public void selectFlag(String getvalue,String locator,String locator1) throws Exception {

		if(getvalue != null && !getvalue.isEmpty()) {
			try {
				 if(getvalue.equalsIgnoreCase("Yes")) {
					 
				 WebElement element = driver.findElement(By.xpath(locator));
				element.click();
				} 
				 
				else if(getvalue.equalsIgnoreCase("No")) {
		
				 WebElement element1= driver.findElement(By.xpath(locator1));
				element1.click();
				}
			
			}catch(Exception e) {}
		}
	}


	public void datepicker(String getvalue, String locator) throws IOException, ParseException
	{

		if(getvalue != null && !getvalue.isEmpty()) {
			 WebElement elem=driver.findElement(By.xpath(locator));
			elem.sendKeys(getvalue);
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Selecting the date field is " +getvalue);

		}
	}


	public String gettextdata(String locator) throws IOException
	{
		 WebElement elem=driver.findElement(By.xpath(locator));
		return elem.getAttribute("value");
	}


	public void waiting() throws InterruptedException {
		Thread.sleep(5000);
	}

	
	public void copytext(String locator){
		copydata=driver.findElement(By.xpath(locator)).getText();
	}
	
	public void pastetext(String locator){
		driver.findElement(By.xpath(locator)).sendKeys(copydata);
	}


	public void validation(String name) {
		driver.getCurrentUrl().endsWith(name);
	}

	public void ScrollToElement(String locator) throws InterruptedException {
		 WebElement moveToElement=driver.findElement(By.xpath(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moveToElement);
		Thread.sleep(5000);
	}



	public void Tabout() throws InterruptedException {
		Actions act = new Actions(driver);

		act.sendKeys(Keys.TAB).build().perform();
	}


	public void NegativeValidate(String locator,String locator2) throws IOException
	{
		List< WebElement> eledata=driver.findElements(By.xpath(locator));
		passFailScreenshot(driver,"SeleniumAutomation");
		if(!eledata.isEmpty()) {
			for( WebElement eleda :eledata) {
				System.out.println(eleda);
			}
			driver.findElement(By.xpath(locator2)).click();
			test.fail("Execution fail");
			extent.flush();
		}
	}


	public String PositiveValidate(String locator,String locator2) throws IOException
	{
		String message = "";
		try {
			message = driver.findElement(By.xpath((locator))).getAttribute("title");
			if (message != null && !message.equalsIgnoreCase("")) {
				List< WebElement> eledata=driver.findElements(By.xpath(locator));
				passFailScreenshot(driver,"SeleniumAutomation");

				if(!eledata.isEmpty()) {
					for( WebElement eleda :eledata) {
						System.out.println(eleda);
						driver.findElement(By.xpath(locator2)).click();
						test.pass("Execution Pass");
						break;

					}

				}

			}

		} catch (Exception ex) {
		}
		return message;
	}


	public void logindetailsname(String getvalue,String locator) {
		 WebElement elem=driver.findElement(By.name(locator));
		elem.sendKeys(getvalue);

	}


	public void clickbuttonjs(String locator) throws IOException, Exception{
         WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        Thread.sleep(3000);
        passFailScreenshot(driver,"SeleniumAutomation");
        executor.executeScript("arguments[0].click();", element);
        test.pass("Element click succesfully");
       }
	
	
	public void mulitclick(String locator) throws IOException, Exception{
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
       List< WebElement> checkbox = driver.findElements(By.xpath(locator));
       for( WebElement element :checkbox) {
    	   if(!element.isSelected()) 
    		   executor.executeScript("arguments[0].click();", element);
    	   passFailScreenshot(driver,"SeleniumAutomation");
           test.pass("Element click succesfully");
       	}  
       }
	
	
	public void clear(String locator)	throws IOException, Exception{
         WebElement element = driver.findElement(By.xpath(locator));
        passFailScreenshot(driver,"SeleniumAutomation");
        element.clear();
        test.pass("Element click succesfully");
       }
	
	public void enableordisable(String locator)	throws IOException, Exception{
         WebElement element = driver.findElement(By.xpath(locator));
        Thread.sleep(3000);
        passFailScreenshot(driver,"SeleniumAutomation");
        element.click();
        element.isEnabled();
        test.pass("Element click succesfully");
       }
	
	
	
	public void closeOverrideandinfoPopUp() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifr_AlertWin']")));
        passFailScreenshot(driver,"SeleniumAutomation");
        test.pass("Element click succesfully");
		driver.findElement(By.id("BTN_ACCEPT")).click();
		
		//driver.switchTo().activeElement();
		switchToFrameByTitle(screenName);
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifr_AlertWin']")));
        passFailScreenshot(driver,"SeleniumAutomation");
        test.pass("Element click succesfully");
		driver.findElement(By.id("BTN_OK")).click();
		switchToFrameByTitle(screenName);
		driver.switchTo().parentFrame();

	}
	
			
	
	public void clickbuttondoubleclick(String locator) throws IOException, Exception{
		Actions actions = new Actions(driver);
		 WebElement elementLocator = driver.findElement(By.xpath(locator));
		Thread.sleep(3000);
		passFailScreenshot(driver,"SeleniumAutomation");
		actions.contextClick(elementLocator).perform();
		test.pass("Element click succesfully");
		}


	public void clickbuttonjs1(String locator) throws IOException, Exception{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		Thread.sleep(3000);
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(locator)));
	}
	
	
	public void Mainframes() {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));
	}
	
	public void Mainframes1() {
    driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[starts-with(@id,'ifr_LaunchWin')])[7]")));
	}
	
	public static void Signoffpopup() throws Exception {
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifr_AlertWin']")));
	}
	
	 public void Authorize(){
	       
	        try {
	      
	        driver.findElement(By.linkText("Authorize")).click();
	        driver.switchTo().frame(driver.findElement(By.cssSelector("[id*='ifrSubScreen']")));
	        driver.findElement(By.xpath("//input[@id='BTN_OK' and @value='Accept']")).click();
	        passFailScreenshot(driver,"SeleniumAutomation");
	        test.pass("Element click succesfully");
	        closeOverrideandinfoPopUp();
	        Thread.sleep(3000);
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));
	        passFailScreenshot(driver,"SeleniumAutomation");
	        driver.findElement(By.cssSelector("#BTN_EXIT_IMG")).click();
	        test.pass("Element click succesfully");
	        driver.switchTo().defaultContent();
	        }catch(Exception e) {}  
	    }
	 
	 
	 public void Authorize1(){
	       
	        try {
	        	
	        	
	        driver.findElement(By.linkText("Authorize")).click();
	        driver.switchTo().frame(driver.findElement(By.cssSelector("[id*='ifrSubScreen']")));
	        driver.findElement(By.xpath("//button[@id='BLK_BRN_PX_OUT_TXN__BTN_AUTH']")).click();
	        passFailScreenshot(driver,"SeleniumAutomation");
	        test.pass("Element click succesfully");
	        
	        Thread.sleep(3000);
	        
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifr_AlertWin']")));
	        passFailScreenshot(driver,"SeleniumAutomation");
	        test.pass("Element click succesfully");
			driver.findElement(By.id("BTN_OK")).click();
			
			Thread.sleep(3000);
			
			switchToFrameByTitle(screenName);
			driver.switchTo().parentFrame();
	        Thread.sleep(3000);
	        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));
	      //  driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[starts-with(@id,'ifr_LaunchWin')])[7]")));
	        passFailScreenshot(driver,"SeleniumAutomation");
	        driver.findElement(By.cssSelector("#BTN_EXIT_IMG")).click();
	        
	        Thread.sleep(3000);
	        test.pass("Element click succesfully");
	        driver.switchTo().defaultContent();
  
	        
	        }catch(Exception e) {
	        	
	        	
	        }  
	    }
	
	
	
	
	public void openChildScreenByTitleExit(String subScreenName) throws Exception {
		try {
			Thread.sleep(2000);
			driver.findElement(By.linkText(subScreenName)).click();
			Thread.sleep(3000);
			isChildScreenOpened = true;
			//switchToActiveFrame();
			driver.switchTo().defaultContent(); //this is used for All message for enter qurey open
			
			try {
				//closeInfoPopUp();
				 WebElement element = driver.findElement(By.cssSelector("iframe[title*='All']"));
				driver.switchTo().frame(element);
				driver.findElement(By.cssSelector("#BTN_EXIT_IMG")).click();
				//int size = driver.findElements(By.tagName("iframe")).size();
				//switchToActiveFrame();
				driver.switchTo().parentFrame();
				int size1 = driver.findElements(By.tagName("iframe")).size();
				System.out.println(size1);
				driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='IFlauncher']//iframe[1]")));
				//int size2 = driver.findElements(By.tagName("iframe")).size();
				
				 WebElement element123 = driver.findElement(By.cssSelector("#BTN_EXIT_IMG"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element123);
				
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
		}
		} catch (Exception ex) {
			// expand sub screen section
			clickButtonById("BtnSubSysNav");
			driver.findElement(By.linkText(subScreenName)).click();
			isChildScreenOpened = true;
			switchToActiveFrame();
		}
		
	}
	
	public void accountingEntries() throws InterruptedException, IOException
	{
		driver.findElement(By.xpath(".//a[text()='Accounting Entries']")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Accounting Entries')]")));
		passFailScreenshot(driver,"SeleniumAutomation");
		 WebElement scroll=driver.findElement(By.xpath(".//input[@id='BLK_ACCOUNT__TRN_DTIRC1']"));
		scroll.click();
		int i=0;
		while(i<40)
		{
			scroll.sendKeys(Keys.ARROW_RIGHT);
			++i;
			//passFailScreenshot(driver,"SeleniumAutomation");
		}
		Thread.sleep(500);
		driver.findElement(By.xpath(".//a[text()='Accounting Details']")).click();
	   // test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Accounting Entries Detail')]")));
		//passFailScreenshot(driver,"SeleniumAutomation");
		driver.findElement(By.xpath(".//input[@id='BLK_ACCOUNTING_DET_MASTER__REFERENCE_NO']//following::input[@id='BTN_EXIT_IMG']")).click();
		test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Accounting Entries')]")));
		 WebElement exit=driver.findElement(By.cssSelector("#BTN_EXIT_IMG"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", exit);
		passFailScreenshot(driver,"SeleniumAutomation");
	    //test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
	}
	
	public void allMessages() throws InterruptedException, IOException
	{
		driver.findElement(By.xpath("//a[text()='All Messages']")).click();
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title,'All Messages')]")));
		passFailScreenshot(driver,"SeleniumAutomation");
		 WebElement scroll=driver.findElement(By.xpath(".//input[@id='BLK_MESG_DETAILS__DCN']"));
		scroll.click();
		int i=0;
		while(i<40)
		{
			scroll.sendKeys(Keys.ARROW_RIGHT);
			++i;
			passFailScreenshot(driver,"SeleniumAutomation");
		}
		Thread.sleep(500);
		driver.findElement(By.xpath(".//button[@id='BLK_MESSAGES__BTN_MESG']")).click();
	    test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title,'Message Details')]")));
		passFailScreenshot(driver,"SeleniumAutomation");
		driver.findElement(By.xpath(".//input[@id='BLK_MESSAGE_MAIN__REFNO']//following::input[@id='BTN_EXIT_IMG']")).click();
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title,'All Messages')]")));
		 WebElement exit=driver.findElement(By.cssSelector("#BTN_EXIT_IMG"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", exit);
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
		
	}
	
	public void viewQueueLogs() throws InterruptedException, IOException
	{
		Thread.sleep(5000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title, 'View Queue Action Log')]")));
		passFailScreenshot(driver,"SeleniumAutomation");
		 WebElement scroll=driver.findElement(By.xpath(".//input[@id='BLK_DETAIL__TXN_REF_NOI']"));
		scroll.click();
		int i=0;
		while(i<50)
		{
			scroll.sendKeys(Keys.ARROW_RIGHT);
			++i;
			passFailScreenshot(driver,"SeleniumAutomation");
		}
		Thread.sleep(500);
		driver.findElement(By.xpath(".//a[text()='View Request Message']")).click();
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title,'Request Message')]")));
		Thread.sleep(2000);
		passFailScreenshot(driver,"SeleniumAutomation");
		driver.findElement(By.xpath(".//input[@id='BLK_QUEUE_REQUEST_LOG__QUEUE_REF_NOI']//following::input[@id='BTN_EXIT_IMG']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title, 'View Queue Action Log')]")));
		driver.findElement(By.xpath(".//a[text()='View Response Message']")).click();
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		Thread.sleep(1000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title,'Response Message')]")));
		Thread.sleep(2000);
		passFailScreenshot(driver,"SeleniumAutomation");
		driver.findElement(By.xpath(".//input[@id='BLK_QUEUE_RESPONSE_LOG__QUEUE_REF_NOI']//following::input[@id='BTN_EXIT_IMG']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@title, 'View Queue Action Log')]")));
		 WebElement exit=driver.findElement(By.cssSelector("#BTN_EXIT_IMG"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", exit);
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		driver.switchTo().defaultContent();
	}
		public void closeframes() throws Exception {
			try {
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Trade Finance Accounting Entries')]")));
			driver.findElement(By.xpath("//span[text()='Accounting Entries']//following::input[@id='BTN_EXIT_IMG']")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@title,'Trade Finance Events')]")));
			driver.findElement(By.xpath("//a[text()='Accounting Entries']//following::input[@id='BTN_EXIT_IMG']")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//div[@id='IFlauncher']//iframe[1]")));
			driver.findElement(By.xpath("//input[@id='BTN_EXIT_IMG']")).click();
			driver.switchTo().defaultContent();
			}
		catch(Exception e) {}
		}
	
	
	public void openChildScreenByTitle(String subScreenName) throws Exception {
		try {
			driver.findElement(By.linkText(subScreenName)).click();
			Thread.sleep(3000);
			isChildScreenOpened = true;
			switchToActiveFrame();
		} catch (Exception ex) {
			// expand sub screen section
			clickButtonById("BtnSubSysNav");
			driver.findElement(By.linkText(subScreenName)).click();
			isChildScreenOpened = true;
			switchToActiveFrame();
		}
		/*try {
			//closeInfoPopUp();
			 WebElement element = driver.findElement(By.cssSelector("iframe[title*='All']"));
			driver.switchTo().frame(element);
			driver.findElement(By.cssSelector("#BTN_EXIT_IMG")).click();
			driver.switchTo().parentFrame();
			
		} catch (Exception ex) {
		}*/
		
		//this can be enable later
	}
	
	
	
	public void openChildScreenByTitle_new(String subScreenName) throws Exception {
		try {
			driver.findElement(By.linkText(subScreenName)).click();
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element click succesfully");
			Thread.sleep(3000);
			isChildScreenOpened = true;
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("(//iframe[starts-with(@id,'ifr_LaunchWin')])[2]")));
			//switchToActiveFrame();
		} catch (Exception ex) {
			// expand sub screen section
			clickButtonById("BtnSubSysNav");
			driver.findElement(By.linkText(subScreenName)).click();
			isChildScreenOpened = true;
			switchToActiveFrame();
		}
		/*try {
			//closeInfoPopUp();
			 WebElement element = driver.findElement(By.cssSelector("iframe[title*='All']"));
			driver.switchTo().frame(element);
			driver.findElement(By.cssSelector("#BTN_EXIT_IMG")).click();
			driver.switchTo().parentFrame();
			
		} catch (Exception ex) {
		}*/
		
		//this can be enable later
	}
	
	
	
	
	public void closeexitpopup() {
		
		try {
			switchToFrameByTitle("Information Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
			driver.switchTo().parentFrame();
			driver.switchTo().activeElement();
			driver.switchTo().frame("//iframe[starts-with(@id,'ifr_LaunchWin')]");
			driver.findElement(By.xpath("//input[@id='BTN_EXIT_IMG']")).click();
		}
			catch(Exception e) {}
		
	}
	
	
	public void clickButtonById(String elementId) {
		if (validInput(elementId)) {
			 WebElement element = driver.findElement(By.id(elementId));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			//actions.moveToElement(element).build().perform();
			executor.executeScript("arguments[0].click();", element);
		}
	}
	
	
	
	public  void closeInfoPopUp() throws Exception {
		try {
			switchToFrameByTitle("Information Message");
			clickBtnOk();
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element click succesfully");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (ElementClickInterceptedException ex) {
			switchToFrameByTitle("Information Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			switchToFrameByTitle("Information Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			switchToFrameByTitle("Information Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}
	
	
	public  void closeConfirmation() throws Exception {
		try {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (ElementClickInterceptedException ex) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}


	public void clickbutton(String locator) throws IOException  {
		 WebElement element = driver.findElement(By.id(locator));
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		element.click();
		passFailScreenshot(driver,"SeleniumAutomation");
		test.pass("Element click succesfully");
	}
	
	
	public boolean validInput(String value) {
		if (value != null && !value.equalsIgnoreCase("")) {
			return true;
		}
		return false;
	}
	
	 boolean checkElementNValue(String elementId, String value) {
		if ((value != null && !value.equalsIgnoreCase("")) && (elementId != null && !elementId.equalsIgnoreCase(""))) {
			return true;
		}
		return false;
	}
	
	
	public void focusToElement( WebElement element) {
		actions.moveToElement(element).perform();
	}
	
	
	
	public void populateTextByXpath(String getvalue, String locator) throws Exception{
		if(getvalue != null && !getvalue.isEmpty()) {
		if (checkElementNValue(getvalue, locator)) {
			element = driver.findElement(By.xpath(locator));
			if (validInput(element.getAttribute("value"))) {
				element.click();
				element.clear();
				element.sendKeys(getvalue);
				passFailScreenshot(driver,"SeleniumAutomation");
				test.pass("Value Captured succesfully");
				element.sendKeys(Keys.TAB);
			} else {
				element.click();
				element.clear();
				element.sendKeys(getvalue);
				passFailScreenshot(driver,"SeleniumAutomation");
				test.pass("Value Captured succesfully");
				element.sendKeys(Keys.TAB);
			}
		}
	  }
	}
	
	
	public void populateTextByXpath_new(String getvalue, String locator) throws Exception{
		if(getvalue != null && !getvalue.isEmpty()) {
				element.click();
				element.sendKeys(getvalue);
				passFailScreenshot(driver,"SeleniumAutomation");
				test.pass("Element click succesfully");
				element.sendKeys(Keys.TAB);
			}
		}
	
	

	public void LOVFieldValue(String getvalue,String locator1,String locator2,String locator3,String locator4) throws Exception {
		
	if(getvalue != null && !getvalue.isEmpty()) {
		driver.findElement(By.xpath(locator1)).click();
		driver.switchTo().frame(driver.findElement(By.id("ifrSubScreen")));
		driver.findElement(By.xpath(locator2)).clear();
		driver.findElement(By.xpath(locator2)).sendKeys(getvalue);
		driver.findElement(By.xpath(locator3)).click();
		driver.findElement(By.xpath(locator4)).click();
		driver.switchTo().defaultContent();
		switchToFrameByTitle(screenName);
		}
	}
	
	
	public void SubscreenLOVFieldValue(String getvalue,String locator1,String locator2,String locator3,String locator4) throws Exception {

		if(getvalue != null && !getvalue.isEmpty()) {
		driver.findElement(By.xpath(locator1)).click();
		driver.switchTo().frame(driver.findElement(By.id("ifrSubScreen")));
		driver.findElement(By.xpath(locator2)).clear();
		driver.findElement(By.xpath(locator2)).sendKeys(getvalue);
		driver.findElement(By.xpath(locator3)).click();
		driver.findElement(By.xpath(locator4)).click();
		driver.switchTo().defaultContent();
		switchToFrameByTitle(screenName);
		switchToActiveFrame();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='ifrSubScreen']")));
		//driver.findElement(By.xpath("//input[@id='BLK_LINKAGE_DETAILS__LINKEDAMTI']")).sendKeys("1000");
		}
		}
	
	
	
	public void selectDropdownByText(String getvalue, String locator) throws Exception {
		if(getvalue != null && !getvalue.isEmpty()) {
		if (validInput(getvalue)) {
			element = driver.findElement(By.xpath(locator));
			Select select = new Select(element);
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element Selected succesfully");
			select.selectByVisibleText(getvalue);
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element Selected succesfully");
		}
	  }
	}	
	
	
	public void selectFlag(String value, String locator) throws Exception {
		 WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor executor = (JavascriptExecutor)driver;

		if (checkElementNValue(value, locator)) {
			if ((value.equalsIgnoreCase("Yes") || value.equalsIgnoreCase("Y")) && !element.isSelected()) {
				executor.executeScript("arguments[0].click();", element);
			} else if ((value.equalsIgnoreCase("No") || value.equalsIgnoreCase("N")) && element.isSelected()) {
				executor.executeScript("arguments[0].click();", element);
			}
		}
	}
	
	
	public void selectRadioBtn(String keyValue, String locator) {
		if (checkElementNValue(keyValue, locator)) {
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			List< WebElement> elementList = driver.findElements(By.xpath(locator));
			for ( WebElement element : elementList) {
				if (element.getAttribute("label_value").equalsIgnoreCase(keyValue) && !element.isSelected()) {
					executor.executeScript("arguments[0].click();", element);
				}
			}
		}
	}
	
	public void branchFrame()
	{
		 WebElement brnfm=driver.findElement(By.xpath(".//iframe[contains(@title,'Transaction Branch Code')]"));
		driver.switchTo().frame(brnfm);
		driver.findElement(By.xpath(".//button[@id='BTN_OK']")).click();
		driver.switchTo().defaultContent();
	}
	public void switchToActiveFrame() throws Exception {
		driver.switchTo().frame(driver.switchTo().activeElement());
	}
	
	public void switchToFrameByTitle(String frameTitle) throws Exception {
		WebDriverWait wait =new WebDriverWait(driver,5);
		 WebElement element = driver.switchTo().activeElement();
		String activeFrame = element.getAttribute("title");
		if (!activeFrame.equalsIgnoreCase(""))
			activeFrame = activeFrame.substring(0, activeFrame.length() - 1);
		if ((frameTitle != null && activeFrame != null) && (frameTitle.equalsIgnoreCase(activeFrame)))
			switchToActiveFrame();
		else {
			if (frameTitle.equalsIgnoreCase("Information Message")
					|| frameTitle.equalsIgnoreCase("Confirmation Message")
					|| frameTitle.equalsIgnoreCase("Error Message")
					|| frameTitle.equalsIgnoreCase("Override Message")) {
				frameTitle = "//*[@title='" + frameTitle + "']";
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath(frameTitle))));
			} else if (!frameTitle.equalsIgnoreCase(screenName)) {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@title='" + frameTitle + "&nbsp;']"))));
			}
		}
	}
	public void switchFrame(String locator)
	{
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath(locator)));
	}
	
	
	public String errpopupclose() {
		String message = "";
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().parentFrame();
			switchToFrameByTitle(screenName);
			if (isChildScreenOpened) {
				driver.switchTo().frame("ifrSubScreen");
			}
			switchToFrameByTitle("Error Message");
			message = driver.findElement(By.xpath(("//*[@id=\"ERRTBL\"]/tbody"))).getText();
			if (message != null && !message.equalsIgnoreCase("")) {
				passFailScreenshot(CommonFile.driver,"seleniumautomation");
				test.fail("Execution failed");
				clickBtnOk();
			}
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
			clickBtnCancel();
			Thread.sleep(2000);
			closeConfirmaPopUp();
			Thread.sleep(2000);
			clickBtnCancel();
			
		} catch (Exception ex) {

		}
		return message;
	}
	
	
public void clickBtnCancel() {
	waitDriver = new WebDriverWait(driver, 5);
	waitDriver.until(ExpectedConditions.presenceOfElementLocated(By.id("BTN_EXIT_IMG")));
		try {
			driver.findElement(By.id("BTN_EXIT_IMG")).click();
		} catch (ElementClickInterceptedException ex1) {
			driver.findElement(By.id("BTN_EXIT_IMG")).click();
		} catch (NoSuchElementException ex2) {
			driver.findElement(By.id("BTN_EXIT_IMG")).click();
		} catch (Exception ex) {
			driver.findElement(By.id("BTN_EXIT_IMG")).click();
		}
	}



	public void Exitscreen() {
		waitDriver = new WebDriverWait(driver, 5);
			try {
				driver.findElement(By.id("BTN_EXIT_IMG")).click();
			} catch (ElementClickInterceptedException ex1) {
				driver.findElement(By.id("BTN_EXIT_IMG")).click();
			} catch (NoSuchElementException ex2) {
				driver.findElement(By.id("BTN_EXIT_IMG")).click();
			} catch (Exception ex) {
				//driver.findElement(By.id("BTN_EXIT_IMG")).click();
				 WebElement exit=driver.findElement(By.cssSelector("#BTN_EXIT_IMG"));
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", exit);
			}
		}
	
	
	public void setScreenDetails(String screenName) {
	this.screenName = screenName;
	}

	
	
	public String checkUIFormatError() throws Exception {
		String message = "";
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().parentFrame();
			switchToFrameByTitle(screenName);
			if (isChildScreenOpened) {
				driver.switchTo().frame("ifrSubScreen");
			}
			switchToFrameByTitle("Information Message");
			
			message = driver.findElement(By.xpath(("//*[@id=\"ERRTBL\"]/tbody"))).getText();
						
			if (validInput(message)) {
				try {
					if (message != null && !message.equalsIgnoreCase("")) {
					//if(msg.contains("Record Successfully Saved")) {
						passFailScreenshot(CommonFile.driver,"Seleniumautomation");
						test.pass("Execution pass");
						clickBtnOk();

					}
				}catch(Exception e) {
					
					passFailScreenshot(CommonFile.driver,"seleniumautomation");
					test.fail("Execution fail");
					clickBtnOk();
				}
				
				
				
			}
			
			//Newly added this else part
			/*if (validInput(msg)) {
				try {
				if(message.contains("Transaction is submitted for processing")) {
					passFailScreenshot(CommonFile.driver,"seleniumautomation");
					test.pass("Execution pass");
					clickBtnOk();

				}
			}catch(Exception e) {
				
				passFailScreenshot(CommonFile.driver,"seleniumautomation");
				test.fail("Execution fail");
				clickBtnOk();
			 }
			} */
			
			
			if (isChildScreenOpened) {
				closeChildScreen();
			}
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
			
			clickBtnCancel();
			Thread.sleep(2000);
			closeConfirmaPopUp();
			clickBtnCancel();
		} catch (Exception ex) {
			driver.switchTo().defaultContent();
			driver.switchTo().parentFrame();
			switchToFrameByTitle(screenName);
			if (isChildScreenOpened) {
				driver.switchTo().frame("ifrSubScreen");
			}
			List< WebElement> iframes = driver.findElements(By.tagName("iframe"));
			for ( WebElement iframe : iframes) {
				System.out.println("frame title " + iframe.getAttribute("title"));
				String text = iframe.getAttribute("title");
				if (text.contains("List of Values")) {
					passFailScreenshot(CommonFile.driver,"Seleniumautomation");
					test.pass("Execution Pass");
					waitDriver.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(iframe));
					waitDriver.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("WNDbuttons"))))
							.click();
					if (isChildScreenOpened) {
						driver.switchTo().defaultContent();
						driver.switchTo().parentFrame();
						switchToFrameByTitle(screenName);
						driver.switchTo().frame("ifrSubScreen");
						closeChildScreen();
						driver.switchTo().defaultContent();
						switchToFrameByTitle(screenName);
						clickBtnCancel();
						closeConfirmaPopUp();
						clickBtnCancel();
						return "No Records Found for the Specified Criteria " + text;
					}
					message = "No Records Found for the Specified Criteria " + text + " entered";
					driver.switchTo().defaultContent();
					switchToFrameByTitle(screenName);
					clickBtnCancel();
					closeConfirmaPopUp();
					clickBtnCancel();
					
					
				}
			}
		}
		try {
			driver.switchTo().defaultContent();
			driver.switchTo().parentFrame();
			switchToFrameByTitle(screenName);
			if (isChildScreenOpened) {
				driver.switchTo().frame("ifrSubScreen");
			}
			switchToFrameByTitle("Error Message");
			message = driver.findElement(By.xpath(("//*[@id=\"ERRTBL\"]/tbody"))).getText();
			if (message != null && !message.equalsIgnoreCase("")) {
				passFailScreenshot(CommonFile.driver,"seleniumautomation");
				test.pass("Execution passed");
				clickBtnOk();
			}
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
			clickBtnCancel();
			closeConfirmaPopUp();
			clickBtnCancel();
		} catch (Exception ex) {

		}
		return message;
	}

	public void closeChildScreen() throws Exception {
		
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		clickBtnOk();
		switchToFrameByTitle(screenName);
		//driver.switchTo().defaultContent();//this can be deleted later
		isChildScreenOpened = false;
	}
	
	public void closeChildSubscreen(String frameTitle) throws Exception {
		WebDriverWait wait =new WebDriverWait(driver,5);
		passFailScreenshot(driver,"SeleniumAutomation");
	    test.pass("Element click succesfully");
		clickBtnOk();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'ifr_LaunchWin')]")));
		 /*if (!frameTitle.equalsIgnoreCase(screenName)) {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(driver.findElement(By.xpath("//*[@title='" + frameTitle + "&nbsp;']"))));
		}*/
		isChildScreenOpened = false;
	}
	
	
	public void clickBtnOk() throws Exception {
		element = driver.findElement(By.id("BTN_OK"));
		element.click();
	}
	
	
	
	public String getAllErrorMsgAndClose(String fileName) throws Exception {
		String message = "";
		try {
			switchToFrameByTitle("Error Message");
			message = driver.findElement(By.xpath(("//*[@id=\"ERRTBL\"]/tbody"))).getText();
			if (message != null && !message.equalsIgnoreCase("")) {
				clickBtnOk();
			}
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
			clickBtnCancel();
			closeConfirmaPopUp();
			clickBtnCancel();
		} catch (Exception ex) {
			
		}
		return message;
	}
	
	
	public void closeRemarkPopUp() throws Exception {
		try {
			element = driver.findElement(By.xpath("//*[@src='Remarks.jsp?iframeLaunch=true']"));
			driver.switchTo().frame(element);
			driver.findElement(By.xpath("//h1[text()='Remarks']//following::input[1]")).click();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (ElementClickInterceptedException ex) {
			element = driver.findElement(By.xpath("//*[@src='Remarks.jsp?iframeLaunch=true']"));
			driver.switchTo().frame(element);
			driver.findElement(By.id("BTN_OK"));
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			element = driver.findElement(By.xpath("//*[@src='Remarks.jsp?iframeLaunch=true']"));
			driver.switchTo().frame(element);
			driver.findElement(By.id("BTN_OK"));
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			element = driver.findElement(By.xpath("//*[@src='Remarks.jsp?iframeLaunch=true']"));
			driver.switchTo().frame(element);
			driver.findElement(By.id("BTN_OK"));
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}
	
	
	public void closeConfirmaPopUp() throws Exception {
		try {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (ElementClickInterceptedException ex) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			switchToFrameByTitle("Confirmation Message");
			clickBtnOk();
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}
	
	
	public void closeOverridePopUp() throws Exception {
		try {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (ElementClickInterceptedException ex) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}
	
	
	
	public void closeOverridePopUp111() throws Exception {
		try {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			switchToFrameByTitle(screenName);
			driver.switchTo().parentFrame();
		} catch (ElementClickInterceptedException ex) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (NoSuchElementException ex1) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		} catch (Exception ex2) {
			switchToFrameByTitle("Override Message");
			clickbutton("BTN_ACCEPT");
			driver.switchTo().defaultContent();
			switchToFrameByTitle(screenName);
		}
	}
	
		
	public void MovetoElementAndClick(String locator) throws InterruptedException{
		 WebElement element = driver.findElement(By.xpath(locator));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		Thread.sleep(2000);
		actions.click();

	}
	
	
	public void fieldExistsOrNot1(String locator) throws Exception {
		try {
			boolean isEnable=driver.findElement(By.xpath(locator)).isEnabled();
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element present");
		}
		catch(Exception e) {
			passFailScreenshot(driver,"SeleniumAutomation");
			test.fail("Element not present");
		}
		}
	
	public void clickYesorNo(String getvalue,String locator){
		if(getvalue != null && !getvalue.isEmpty()) {
			if(getvalue.equalsIgnoreCase("Yes") || getvalue.equalsIgnoreCase("No")) {
				 WebElement element = driver.findElement(By.xpath(locator));
				element.click();
			}
		}
	}
	
	public void clickButtonByXpath(String xpath) {
		actions = new Actions(driver);
		if (validInput(xpath)) {
			element = driver.findElement(By.xpath(xpath));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			actions.moveToElement(element).build().perform();
			executor.executeScript("arguments[0].click();", element);
		}
	}
	
	
	public void destroy() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		clickButtonByXpath("//li[contains(text(),'Sign Off')]");
		Thread.sleep(2000);
		Signoffpopup();
		clickBtnOk();
	}


	public String clickreturn(String locator){
		 WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		return locator;

	}


	public void mouseover(String locator) throws IOException{
		Actions action=new Actions(driver);
		 WebElement element = driver.findElement(By.xpath(locator));
		action.moveToElement(element).build().perform();
		passFailScreenshot(driver,"SeleniumAutomation");
	}
	
	public void scrollDown(String locator) throws IOException, InterruptedException {
		 WebElement scroll=driver.findElement(By.xpath(locator));
		scroll.click();
		int i=0;
		while(i<30)
		{
			scroll.sendKeys(Keys.ARROW_DOWN);
			++i;
			passFailScreenshot(driver,"SeleniumAutomation");
		}
		Thread.sleep(500);
	}
	
	public void scrollRight(String locator) throws IOException, InterruptedException {
		 WebElement scroll=driver.findElement(By.xpath(locator));
		scroll.click();
		int i=0;
		while(i<30)
		{
			scroll.sendKeys(Keys.ARROW_RIGHT);
			++i;
			passFailScreenshot(driver,"SeleniumAutomation");
		}
		Thread.sleep(500);
	}

	public void dropdown(String getvalue,String locator){
		if(getvalue != null && !getvalue.isEmpty()) {
			 WebElement element = driver.findElement(By.xpath(locator));
			Select select=new Select(element);
			select.selectByVisibleText(getvalue);
		}
	}

	public void selectRadioBtn(String locator) {
		 WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}



	public void selectDropdownValue(String ddl_ele, String lstddl_ele, String locator) throws Exception {
		 WebElement element = driver.findElement(By.xpath(ddl_ele));
		element.click();
		Thread.sleep(2000);
		 WebElement element1 = driver.findElement(By.xpath(lstddl_ele));
		element1.findElement(By.xpath(" " + locator)).click();
	}


	public void selectDropDownValue(String getvalue,String locator,String locator1) throws Exception{

		if(getvalue != null && !getvalue.isEmpty()) {
			 WebElement elem=driver.findElement(By.xpath(locator));
			elem.click();
			Thread.sleep(2000);
			 WebElement element1 = driver.findElement(By.xpath(locator1));
			element1.findElement(By.xpath(" " + getvalue)).click();
			passFailScreenshot(driver,"SeleniumAutomation");
			test.pass("Element selected succesfully");

		}

	}
	
	
	public void newwindowtab() throws Exception {
		
		for(int i=0;i<=1;i++) {
			Robot rob=new Robot();
			rob.keyPress(KeyEvent.VK_CONTROL);
			rob.keyPress(KeyEvent.VK_T);
			rob.keyRelease(KeyEvent.VK_CONTROL);
			rob.keyRelease(KeyEvent.VK_T);
			ArrayList<String> tab1= new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window((String) tab1.get(i));
		}
		String url = xldata.getCellData("Login2", "URL", 2);
		driver.get(url);
		String un= xldata.getCellData("Login2", "Username", 2);
		driver.findElement(By.xpath("//input[@id='login_username|input']")).sendKeys(un);
		String pwd = xldata.getCellData("Login2", "Password", 2);
		driver.findElement(By.xpath("//input[@id='login_password|input']")).sendKeys(pwd);
		driver.findElement(By.xpath("//oj-button[@id='login-button']//div[@class='oj-button-label']")).click();
		
		 }

	public void dependencyAccount(String getvalue,String locator) throws Exception{

		if(getvalue != null && !getvalue.isEmpty()) {
			 WebElement elem=driver.findElement(By.xpath(locator));
			elem.click();
		}
	}


	public void ScrollToElement( WebElement moveToElement) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", moveToElement);
		Thread.sleep(500);
	}



}

