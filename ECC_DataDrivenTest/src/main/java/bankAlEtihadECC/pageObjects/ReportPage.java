package bankAlEtihadECC.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import bankAlEtihadECC.abstractComponents.AbstractComponents;

public class ReportPage extends AbstractComponents {

	WebDriver driver;

	public ReportPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "btnCollaps")
	private WebElement collaps;
	@FindBy(css = "td#txt_id_1040000_10")
	private WebElement reports;
	@FindBy(id = "1040010")
	private WebElement masetrQuery;
	@FindBy(id = "inOutFlag")
	private WebElement direction;
	@FindBy(id = "bfdAccountNumberId")
	private WebElement bfdAccount;
	@FindBy(id = "seqFromId")
	private WebElement batchSeqFrom;
	@FindBy(id = "seqToId")
	private WebElement batchSeqTo;
	@FindBy(id = "generate")
	private WebElement generateReport;
	@FindBy(id = "scanDateFromIdImage")
	private WebElement scanDateFrom;
	@FindBy(css = ".day.selected.today")
	private WebElement today;

	public void reports() {
		
		driver.switchTo().defaultContent();
		menuFrameToBeAvailable();
		collaps.click();
		reports.click();
	}
	
	public void master(String seq) throws InterruptedException {
		
		driver.switchTo().frame("mainTree");
		masetrQuery.click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("MainFrame");
		Select chequeDirection = new Select(direction);
		chequeDirection.selectByValue("O");
		Thread.sleep(100);
		batchSeqFrom.sendKeys(seq+"001");
		batchSeqTo.sendKeys(seq+"100");
		scanDateFrom.click();
		today.click();
		generateReport.click();
		Thread.sleep(100);
		driver.switchTo().frame("ResultIFrame");
		
		
	}

}
