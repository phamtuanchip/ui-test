package org.exoplatform.selenium.platform.ecms.regression;

import static org.exoplatform.selenium.TestBase.actions;
import static org.exoplatform.selenium.TestBase.baseUrl;
import static org.exoplatform.selenium.TestBase.click;
import static org.exoplatform.selenium.TestBase.driver;
import static org.exoplatform.selenium.TestBase.initSeleniumTest;
import static org.exoplatform.selenium.TestBase.type;
import static org.exoplatform.selenium.TestLogger.info;
import static org.exoplatform.selenium.platform.ecms.ContentTemplate.uploadFile;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToNodeByPath;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToOverView;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.goToSiteExplorer;
import static org.exoplatform.selenium.platform.ecms.EcmsBase.loginEcms;
import static org.exoplatform.selenium.platform.ecms.SiteExplorer.chooseDrive;

import org.exoplatform.selenium.platform.ecms.SiteExplorer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class REG_PLF309_ECMS_008 {
	
	/*-- Data for these test cases --*/
  final public String DATA_USER = "john";
  final public String DATA_PASS = "gtn";
  
  
	 @BeforeMethod
	  public void beforeMethods(){
	    initSeleniumTest();
	    driver.get(baseUrl);
	    actions = new Actions(driver);
	    info("Login ECMS with " + DATA_USER);
	    loginEcms(DATA_USER, DATA_PASS);
	  }

	  @AfterMethod
	  public void afterMethods(){
	    info("Logout ECMS");
	    driver.quit();
	    actions = null;
	  }
	  
	  /**
		 * Step 1:
		 * - Go to Content Explorer
		 * - Upload new PFD file
		 * Step 2: 
		 * - Select uploaded file above
		 * - Click on Manage Publication in action bar
		 * - Click on Publish
		 * Step 3:
		 * - Go to homepage of acme site
		 * - Put uploaded file's name in the search box
		 * - Press enter
		 * Step 4:
		 * - In search result list, open PDF file to view content
		 * - Click on Print 
		 * - At the bottom of the page, you will find another "print", click on it and choose a page number, for example the first page
		 */
	  @Test
		public void CheckPrintingPDF() {
	  	
	  	By DRIVER_SITES_MANAGEMENT = By.xpath("//a[@class='DriveLabel' and @title = 'Sites Management']");
	  	String ACME_DOCUMENT_FOLDER = "acme/documents";
	  	String fileName = "REG_PLF309_ECMS_008_PDF.pdf";
	  	By ELEMENT_UPLOADED = By.xpath("//div[contains(@title='fileName']");
	  	String ELEMENT_INPUT_SEARCH = "//input[@name='keyword']";
	  	
	  	info("Starting REG_PLF309_ECMS_008");
	    // goto Site Explorer
			info("Go to Site Explorer");
			goToSiteExplorer();
			
			// Choose Management Sites
			chooseDrive(DRIVER_SITES_MANAGEMENT);
			
			 // Go to document of acme file
			info("Go to acme document folder");
			goToNodeByPath(ACME_DOCUMENT_FOLDER);
			
		  // Upload PDF file
			info("Upload PDF file");
			uploadFile(fileName,"TestData/"+fileName);
			
			//Expected step 1: The pdf file uploaded success 
			Assert.assertTrue(driver.findElement(ELEMENT_UPLOADED).isDisplayed());
			
			//// Open uploaded file
			click(ELEMENT_UPLOADED);
			
			//Published document
			SiteExplorer.publishDocument();
			
			//Go to Acme site
			info("Go to acme site");
			goToOverView();
			//Put uploaded file's name in the search box
			type(ELEMENT_INPUT_SEARCH,fileName, false);
			
			//Click Enter
			info("Click enter");
			driver.findElement(By.xpath("ELEMENT_INPUT_SEARCH")).sendKeys(Keys.ENTER);
			
			//Expected: The uploaded file displays
			Assert.assertTrue(driver.findElement(ELEMENT_UPLOADED).isDisplayed());
			
	  }
}
