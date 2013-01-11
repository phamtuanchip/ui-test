package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF305_ECMS_005 extends EcmsBase {
	/*-- Data for these test cases --*/
	public String DATA_USER = "john";
	public String DATA_PASS = "gtn";
	
	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	By UPLOAD_BUTTON = By.linkText("Upload");
	By NAME_FIELD = By.id("name");
	By UPLOAD_IFRAME = By.xpath("//iframe[contains(@id,'uploadFrame')]");
	By ELEMENT_UPLOAD_ID = By.id("file");
	By ADD_ITEM_BUTTON = By.xpath("//img[@title='Add Item']");
	By CLOSE_WINDOW_BUTTON = By.xpath("//a[@title='Close Window']");
	By CANCEL_BUTTON = By.linkText("Cancel");
	String FILE_TO_UPLOAD = "TestData/test.txt";	
	String NAME_FIELD_VALUE = "TEST005";	
	
	@BeforeMethod
	public void beforeMethods(){
		info("\n\nBEGIN Test case REG_PLF305_ECMS_005 ");
		info("Check form to Select Category. \n\n");
		initSeleniumTest();
		driver.get(baseUrl);
		actions = new Actions(driver);
		info("Login ECMS with " + DATA_USER);
		loginEcms(DATA_USER, DATA_PASS);
	}
	
	@AfterMethod
	public void afterMethods(){
		info("Logout ECMS");
		info("\n\nEND Test case REG_PLF305_ECMS_005 \n\n");
		driver.quit();
		actions = null;
	}
	
	/*-- Case No 005 / ID REG_PLF305_ECMS_005 
	 *-- Check form to Select Category
	 *	- Go to Sites Explorer
		- Go to acme/document
		- Upload a file
		-- Choose a file to upload
		-- Click Add Items button in Categories List
		-- Verify that there is no "Child not found!" text displayed.		
	 * --*/
	@Test
	public void test05_CheckFormToSelectCategory(){
		// Go to Sites Explorer
		info("\n=== Go Sites Explorer ===");
		goToSiteExplorer();
		chooseDrive(DRIVER_SITES_MANAGEMENT);
		waitForElementPresent(By.xpath("//div[contains(text(),'Sites Management')]"));
		
		// Go to acme/document
		info("\n === Go to acme/document ===");
		goToNodeByPath("acme/documents");
				
		// === Upload a file ====
		info("\n === Click on Upload button ===");
		waitForElementPresent(UPLOAD_BUTTON);
		mouseOver(UPLOAD_BUTTON,false);
		click(UPLOAD_BUTTON);
		
		// Fill in Name
		info("\n === Fill in name of file ===");
		waitForElementPresent(NAME_FIELD);
		type(NAME_FIELD, NAME_FIELD_VALUE, false);
		
		// Upload a text file
		info("\n === Upload a text file ===");
		driver.switchTo().frame(waitForAndGetElement(UPLOAD_IFRAME));
		type(ELEMENT_UPLOAD_ID, getAbsoluteFilePath(FILE_TO_UPLOAD), false);
		switchToParentWindow();
		
		// Choose category in Category List
		info("\n === Choose category in Category List ===");
		info("\n ===== Click on Add item button =====");
		waitForElementPresent(ADD_ITEM_BUTTON);
		click(ADD_ITEM_BUTTON);		
		
		// Verify that there is no "Child not found!"
		info("\n ===== Verify that there is no \"Child not found!\" =====");
		waitForElementPresent(By.xpath("//th[contains(text(),'Name')]"));
		waitForElementNotPresent(By.xpath("//div[contains(text(),'Child not found!')]"));
		info("\n ===== Child nodes appear !!! =====");
		
		// Close popup window
		info("\n === Close popup window ===");
		click(CLOSE_WINDOW_BUTTON);		
	}
}
